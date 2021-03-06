package com.mazeltov.productservice.feignclients

import com.mazeltov.common.dto.*
import com.mazeltov.common.spring.*
import com.mazeltov.productservice.endpoints.*
import org.slf4j.*
import org.springframework.cloud.openfeign.*
import org.springframework.http.*
import org.springframework.stereotype.*
import org.springframework.web.bind.annotation.*

@FeignClient(
        name = "\${review-service.name}",
        fallback = ReviewServiceFeignClient.ReviewServiceDefaultRealisation::class
)
interface ReviewServiceFeignClient {

    @GetMapping("\${api.review-services.rout}")
    fun getAllReviews(@PathVariable(value = "productId") productId: Long): List<ReviewDto>

    @GetMapping("\${api.review-services.current.rout}")
    fun getReviewById(@PathVariable(value = "productId") productId: Long,
                      @PathVariable(value = "id") reviewId: Long): ResponseEntity<Any>

    @GetMapping("\${api.review-services.rout}")
    fun getReviewsBetween(@PathVariable(value = "productId") productId: Long,
                          @RequestParam(value = "start") start: Int,
                          @RequestParam(value = "finish") finish: Int): List<ReviewDto>

    @PostMapping("\${api.review-services.rout}")
    fun addReview(@PathVariable(value = "productId") productId: Long, @RequestBody review: ReviewDto): ResponseEntity<Any>

    @PatchMapping("\${api.review-services.current.rout}")
    fun editReview(@PathVariable(value = "productId") productId: Long, @RequestBody review: ReviewDto): ResponseEntity<Any>

    @DeleteMapping("\${api.review-services.current.rout}")
    fun deleteReview(@PathVariable(value = "productId") productId: Long,
                     @PathVariable(value = "id") reviewId: Long): ResponseEntity<Any>

    @Component
    class ReviewServiceDefaultRealisation : ReviewServiceFeignClient {

        @InjectLogger(ReviewServiceDefaultRealisation::class)
        private lateinit var logger: Logger

        override fun getAllReviews(productId: Long): List<ReviewDto> {
            return emptyList()
        }

        override fun getReviewById(productId: Long, reviewId: Long): ResponseEntity<Any> {
            return "Service is not available".wrapToResponseEntity(HttpStatus.BAD_REQUEST)
        }

        override fun getReviewsBetween(productId: Long, start: Int, finish: Int): List<ReviewDto> {
            return emptyList()
        }

        override fun addReview(productId: Long, review: ReviewDto): ResponseEntity<Any> {
            return "Service is not available".wrapToResponseEntity(HttpStatus.BAD_REQUEST)
        }

        override fun editReview(productId: Long, review: ReviewDto): ResponseEntity<Any> {
            return "Service is not available".wrapToResponseEntity(HttpStatus.BAD_REQUEST)
        }

        override fun deleteReview(productId: Long, reviewId: Long): ResponseEntity<Any> {
            return "Service is not available".wrapToResponseEntity(HttpStatus.BAD_REQUEST)
        }

    }
}
