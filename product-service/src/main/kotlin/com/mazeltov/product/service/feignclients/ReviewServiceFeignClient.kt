package com.mazeltov.product.service.feignclients

import com.mazeltov.product.service.config.sping.*
import com.mazeltov.product.service.models.dto.*
import org.slf4j.*
import org.springframework.cloud.openfeign.*
import org.springframework.stereotype.*
import org.springframework.web.bind.annotation.*

@FeignClient(
        name = "\${review-service.name}",
        fallback = ReviewServiceFeignClient.ReviewServiceDefaultRealisation::class
)
interface ReviewServiceFeignClient {

    @GetMapping("\${api.review-service.rout}")
    fun getAllReviews(@PathVariable product: Long): List<ReviewDto>

    @GetMapping("\${api.review-service.current.rout}")
    fun getReviewById(@PathVariable(value = "product") productId: Long,
                      @PathVariable(value = "id") reviewId: Long): ReviewDto

    @GetMapping("\${api.review-service.rout}")
    fun getReviewsBetween(@PathVariable(value = "product") productId: Long,
                          @RequestParam(value = "start") start: Int,
                          @RequestParam(value = "finish") finish: Int): ReviewDto

    @PostMapping("\${api.review-service.rout}")
    fun addReview(@PathVariable(value = "product") productId: Long, @RequestBody review: ReviewDto): ReviewDto

    @PatchMapping("\${api.review-service.current.rout}")
    fun editReview(@PathVariable(value = "product") productId: Long, @RequestBody review: ReviewDto): ReviewDto

    @DeleteMapping("\${api.review-service.current.rout}")
    fun deleteReview(@PathVariable(value = "product") productId: Long,
                     @PathVariable(value = "id") reviewId: Long)

    @Component
    class ReviewServiceDefaultRealisation : ReviewServiceFeignClient {

        @InjectLogger(ReviewServiceDefaultRealisation::class)
        private lateinit var logger: Logger

        override fun getAllReviews(product: Long): List<ReviewDto> {
            TODO("Not yet implemented")
        }

        override fun getReviewById(productId: Long, reviewId: Long): ReviewDto {
            TODO("Not yet implemented")
        }

        override fun getReviewsBetween(productId: Long, start: Int, finish: Int): ReviewDto {
            TODO("Not yet implemented")
        }

        override fun addReview(productId: Long, review: ReviewDto): ReviewDto {
            TODO("Not yet implemented")
        }

        override fun editReview(productId: Long, review: ReviewDto): ReviewDto {
            TODO("Not yet implemented")
        }

        override fun deleteReview(productId: Long, reviewId: Long) {
            TODO("Not yet implemented")
        }

    }
}
