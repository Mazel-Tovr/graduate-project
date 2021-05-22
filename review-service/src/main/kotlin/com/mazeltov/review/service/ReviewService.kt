package com.mazeltov.review.service

import com.mazeltov.common.*
import com.mazeltov.common.dto.*
import com.mazeltov.common.exception.*
import com.mazeltov.common.security.*
import com.mazeltov.review.models.*
import com.mazeltov.review.repositories.*
import org.springframework.beans.factory.annotation.*
import org.springframework.data.domain.*
import org.springframework.http.*
import org.springframework.stereotype.*

@Service
class ReviewService {

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    @Value("\${jwt.header}")
    private lateinit var header: String

    @Autowired
    private lateinit var reviewOperations: ReviewOperations

    @Autowired
    private lateinit var productOperations: ProductOperations

    @Autowired
    private lateinit var userOperations: UserOperations


    fun getAllReviews(productId: Long): List<ReviewDto> {
        return reviewOperations.findAllByProductId(productId).toDto()
    }


    fun getReviewById(productId: Long, reviewId: Long): ReviewDto? {
        return reviewOperations.findByProductIdAndId(productId, reviewId)?.toDto()
    }


    fun getReviewsBetween(productId: Long, start: Int, finish: Int): List<ReviewDto> =
        reviewOperations.findAllByProductId(productId, PageRequest.of(start, finish)).toDto()


    fun addReview(productId: Long, review: ReviewDto): ReviewDto =
        reviewOperations.save(
            Review(
                product = getOrCreateProduct(productId),
                user = getOrCreateUser(review.userName),
                content = review.content
            )).toDto()


    fun editReview(productId: Long, review: ReviewDto) =
        reviewOperations.findById(review.id).let {
            checkHeaders(it.get().user.userName) {
                if (!it.isPresent) throw ReviewDoesNotExistException("This review $review doesn't exist")
                reviewOperations.save(it.get().apply {
                    user = getOrCreateUser(review.userName)
                    product = getOrCreateProduct(review.productId)
                    date = review.date
                    content = review.content
                }).toDto()
            }
        }


    fun deleteReview(
        productId: Long,
        reviewId: Long
    ): ResponseEntity<*> = reviewOperations.findByProductIdAndId(productId, reviewId)?.let {
        checkHeaders(it.user.userName) {
            reviewOperations.delete(it)
        }
    } ?: throw ReviewDoesNotExistException("Review not found")


    fun deleteUserAndAllReviews(userName: String) =
        userOperations.findByUserName(userName)?.let { userOperations.delete(it) }

    fun deleteProductAndAllReviews(productId: Long) =
        productOperations.findByProductId(productId)?.let { productOperations.delete(it) }


    private fun getOrCreateProduct(productId: Long): Product =
        productOperations.findByProductId(productId) ?: productOperations.save(Product(productId = productId))

    private fun getOrCreateUser(userName: String): User =
        userOperations.findByUserName(userName) ?: userOperations.save(User(userName = userName))

    private inline fun checkHeaders(userName: String, block: () -> Any): ResponseEntity<*> {
        return if (getUserIdFromRequest(header, secret) == userName ||
            getUserRoleFromRequest(header, secret) == UserRole.ADMIN
        ) {
            block().toResponseEntity()
        } else {
            "This review doesn't belong to you".toResponseEntity(HttpStatus.FORBIDDEN)
        }
    }

}

fun Review.toDto(): ReviewDto = ReviewDto(id, product.productId, user.userName, date, content)

fun List<Review>.toDto() = map { it.toDto() }
