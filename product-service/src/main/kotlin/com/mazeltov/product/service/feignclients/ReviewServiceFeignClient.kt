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

    @GetMapping("\${api.review-service}")
    fun getAllReviews(@PathVariable product: Long): List<Review>

    @GetMapping("\${api.review-service.current}")
    fun getReviewById(@PathVariable(value = "product") productId: Long,
                      @PathVariable(value = "id") reviewId: Long): Review

    @GetMapping("\${api.review-service}")
    fun getReviewsBetween(@PathVariable(value = "product") productId: Long,
                          @RequestParam(value = "start") start: Int,
                          @RequestParam(value = "finish") finish: Int): Review

    @PostMapping("\${api.review-service}")
    fun addReview(@PathVariable(value = "product") productId: Long, @RequestBody review: Review): Review

    @PatchMapping("\${api.review-service.current}")
    fun editReview(@PathVariable(value = "product") productId: Long, @RequestBody review: Review): Review

    @DeleteMapping("\${api.review-service.current}")
    fun deleteReview(@PathVariable(value = "product") productId: Long,
                     @PathVariable(value = "id") reviewId: Long)

    @Component
    class ReviewServiceDefaultRealisation : ReviewServiceFeignClient {

        @InjectLogger(ReviewServiceDefaultRealisation::class)
        private lateinit var logger: Logger

        override fun getAllReviews(product: Long): List<Review> {
            TODO("Not yet implemented")
        }

        override fun getReviewById(productId: Long, reviewId: Long): Review {
            TODO("Not yet implemented")
        }

        override fun getReviewsBetween(productId: Long, start: Int, finish: Int): Review {
            TODO("Not yet implemented")
        }

        override fun addReview(productId: Long, review: Review): Review {
            TODO("Not yet implemented")
        }

        override fun editReview(productId: Long, review: Review): Review {
            TODO("Not yet implemented")
        }

        override fun deleteReview(productId: Long, reviewId: Long) {
            TODO("Not yet implemented")
        }

    }
}
