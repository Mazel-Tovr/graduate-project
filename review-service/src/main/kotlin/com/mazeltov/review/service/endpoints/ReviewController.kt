package com.mazeltov.review.service.endpoints

import com.mazeltov.review.service.models.*
import org.springframework.stereotype.*
import org.springframework.web.bind.annotation.*

@Controller
class ReviewController {

    @GetMapping("\${api.review-service}")
    fun getAllReviews(@PathVariable product: Long): List<Review>{
        TODO()
    }

    @GetMapping("\${api.review-service.current}")
    fun getReviewById(@PathVariable(value = "product") productId: Long,
                      @PathVariable(value = "id") reviewId: Long): Review{
        TODO()
    }

    @GetMapping("\${api.review-service}")
    fun getReviewsBetween(@PathVariable(value = "product") productId: Long,
                          @RequestParam(value = "start") start: Int,
                          @RequestParam(value = "finish") finish: Int): Review{
        TODO()
    }

    @PostMapping("\${api.review-service}")
    fun addReview(@PathVariable(value = "product") productId: Long, @RequestBody review: Review): Review{
        TODO()
    }

    @PatchMapping("\${api.review-service.current}")
    fun editReview(@PathVariable(value = "product") productId: Long, @RequestBody review: Review): Review{
        TODO()
    }

    @DeleteMapping("\${api.review-service.current}")
    fun deleteReview(@PathVariable(value = "product") productId: Long,
                     @PathVariable(value = "id") reviewId: Long){
        TODO()
    }
}
