package com.mazeltov.review.endpoints

import com.mazeltov.common.dto.*
import com.mazeltov.common.response.ResponseBody
import com.mazeltov.common.spring.*
import com.mazeltov.review.service.*
import org.slf4j.*
import org.springframework.beans.factory.annotation.*
import org.springframework.http.*
import org.springframework.web.bind.annotation.*

@RestController
class ReviewController {

    @Autowired
    private lateinit var reviewService: ReviewService

    @InjectLogger
    private lateinit var logger: Logger

    @GetMapping("\${api.review-service.rout}")
    fun getAllReviews(@PathVariable(value = "productId") productId: Long): List<ReviewDto> {
        logger.info("Getting all reviews")
        return reviewService.getAllReviews(productId)
    }

    @GetMapping("\${api.review-service.current.rout}")
    fun getReviewById(
        @PathVariable(value = "productId") productId: Long,
        @PathVariable(value = "id") reviewId: Long
    ): ResponseEntity<Any> {
        logger.info("Getting review by id")
        return reviewService.getReviewById(productId, reviewId)?.let {
            ResponseEntity<Any>(it, HttpStatus.OK)
        } ?: ResponseEntity<Any>(ResponseBody.RESOURCE_NOT_FOUND("Review"), HttpStatus.BAD_REQUEST)
    }

    @GetMapping("\${api.review-service.between.rout}")
    fun getReviewsBetween(
        @PathVariable(value = "productId") productId: Long,
        @RequestParam(value = "start") start: Int,
        @RequestParam(value = "finish") finish: Int
    ): List<ReviewDto> {
        logger.info("Getting review between $start and $finish")
        return reviewService.getReviewsBetween(productId, start, finish)
    }

    @PostMapping("\${api.review-service.rout}")
    fun addReview(
        @PathVariable(value = "productId") productId: Long,
        @RequestBody review: ReviewDto
    ): ResponseEntity<Any> {
        logger.info("Adding review")
        return kotlin.runCatching { reviewService.addReview(productId, review) }.getOrElse {
            return it.message?.wrapToResponseEntity(HttpStatus.BAD_REQUEST)
                ?: "Error".wrapToResponseEntity(HttpStatus.BAD_REQUEST)
        }.wrapToResponseEntity()
    }

    @PatchMapping("\${api.review-service.current.rout}")
    fun editReview(
        @PathVariable(value = "productId") productId: Long,
        @RequestBody review: ReviewDto
    ): ResponseEntity<Any> {
        logger.info("Editing review")
        return runCatching {
            reviewService.editReview(productId, review)
        }.getOrElse {
            return it.message?.wrapToResponseEntity(HttpStatus.BAD_REQUEST)
                ?: "Error".wrapToResponseEntity(HttpStatus.BAD_REQUEST)
        }.wrapToResponseEntity()

    }

    @DeleteMapping("\${api.review-service.current.rout}")
    fun deleteReview(
        @PathVariable(value = "productId") productId: Long,
        @PathVariable(value = "id") reviewId: Long
    ): ResponseEntity<Any> {
        logger.info("Deleting review")
        return kotlin.runCatching {
            reviewService.deleteReview(productId, reviewId).let { "review $reviewId deleted" }
        }.getOrElse {
            return it.message?.wrapToResponseEntity(HttpStatus.BAD_REQUEST)
                ?: "Error".wrapToResponseEntity(HttpStatus.BAD_REQUEST)
        }.wrapToResponseEntity()
    }
}

internal fun Any.wrapToResponseEntity(httpStatus: HttpStatus = HttpStatus.OK) = ResponseEntity<Any>(this, httpStatus)
