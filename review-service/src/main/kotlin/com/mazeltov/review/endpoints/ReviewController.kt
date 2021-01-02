package com.mazeltov.review.endpoints

import com.mazeltov.common.dto.*
import com.mazeltov.common.spring.*
import com.mazeltov.review.service.*
import org.slf4j.*
import org.springframework.beans.factory.annotation.*
import org.springframework.stereotype.*
import org.springframework.web.bind.annotation.*

@RestController
class ReviewController {

    @Autowired
    private lateinit var reviewService: ReviewService

    @InjectLogger
    private lateinit var logger: Logger

    @GetMapping("\${api.review-service.rout}")
    fun getAllReviews(@PathVariable(value = "product") productId: Long): List<ReviewDto> {
        logger.info("Getting all reviews")
        return reviewService.getAllReviews(productId)
    }

    @GetMapping("\${api.review-service.current.rout}")
    fun getReviewById(@PathVariable(value = "product") productId: Long,
                      @PathVariable(value = "id") reviewId: Long): ReviewDto? {
        logger.info("Getting review by id")
        return reviewService.getReviewById(productId, reviewId)
    }

//    @GetMapping("\${api.review-service.rout}")
//    fun getReviewsBetween(@PathVariable(value = "product") productId: Long,
//                          @RequestParam(value = "start") start: Int,
//                          @RequestParam(value = "finish") finish: Int): List<ReviewDto> {
//        logger.info("Getting review between $start and $finish")
//        return reviewService.getReviewsBetween(productId, start, finish)
//    }

    @PostMapping("\${api.review-service.rout}")
    fun addReview(@PathVariable(value = "product") productId: Long, @RequestBody review: ReviewDto): ReviewDto {
        logger.info("Adding review")
        return reviewService.addReview(productId, review)
    }

    @PatchMapping("\${api.review-service.current.rout}")
    fun editReview(@PathVariable(value = "product") productId: Long, @RequestBody review: ReviewDto): ReviewDto {
        logger.info("Editing review")
        return reviewService.editReview(productId, review)
    }

    @DeleteMapping("\${api.review-service.current.rout}")
    fun deleteReview(@PathVariable(value = "product") productId: Long,
                     @PathVariable(value = "id") reviewId: Long) {
        logger.info("Deleting review")
        reviewService.deleteReview(productId, reviewId)
    }
}
