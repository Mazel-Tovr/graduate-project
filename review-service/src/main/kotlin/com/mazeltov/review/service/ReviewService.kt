package com.mazeltov.review.service

import com.mazeltov.common.dto.*
import com.mazeltov.common.exception.*
import com.mazeltov.review.models.*
import com.mazeltov.review.repositories.*
import org.springframework.beans.factory.annotation.*
import org.springframework.data.domain.*
import org.springframework.stereotype.*

@Service
class ReviewService {

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
                            user = getOrCreateUser(review.userId),
                            content = review.content
                    )).toDto()


    fun editReview(productId: Long, review: ReviewDto): ReviewDto =
            reviewOperations.findById(review.id).let {
                if (!it.isPresent) throw ReviewDoesNotExistException("This review $review doesn't exist")
                reviewOperations.save(it.get().apply {
                    user = getOrCreateUser(review.userId)
                    product = getOrCreateProduct(review.productId)
                    date = review.date
                    content = review.content
                })
            }.toDto()


    fun deleteReview(productId: Long, reviewId: Long) {
        reviewOperations.findByProductIdAndId(productId, reviewId)?.let { reviewOperations.delete(it) }
    }

    fun deleteUserAndAllReviews(userId: Long) =
            userOperations.findByUserId(userId)?.let { userOperations.delete(it) }

    fun deleteProductAndAllReviews(productId: Long) =
            productOperations.findByProductId(productId)?.let { productOperations.delete(it) }


    private fun getOrCreateProduct(productId: Long): Product =
            productOperations.findByProductId(productId) ?: productOperations.save(Product(productId = productId))

    private fun getOrCreateUser(userId: Long): User =
            userOperations.findByUserId(userId) ?: userOperations.save(User(userId = userId))

}

fun Review.toDto(): ReviewDto = ReviewDto(id, product.productId, user.userId, date, content)

fun List<Review>.toDto() = map { it.toDto() }
