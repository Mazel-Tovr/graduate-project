package com.mazeltov.review.service.endpoints

import com.mazeltov.review.service.models.*
import org.springframework.stereotype.*
import org.springframework.web.bind.annotation.*

@Controller
class ReviewController {

    @GetMapping("\${api.review-service.rout}")
    fun getAllReviews(@PathVariable product: Long): List<Review>{
        TODO()
    }

    @GetMapping("\${api.review-service.current.rout}")
    fun getReviewById(@PathVariable(value = "product") productId: Long,
                      @PathVariable(value = "id") reviewId: Long): Review{
        TODO()
    }

    @GetMapping("\${api.review-service.rout}")
    fun getReviewsBetween(@PathVariable(value = "product") productId: Long,
                          @RequestParam(value = "start") start: Int,
                          @RequestParam(value = "finish") finish: Int): Review{
        TODO()
    }

    @PostMapping("\${api.review-service.rout}")
    fun addReview(@PathVariable(value = "product") productId: Long, @RequestBody review: Review): Review{
        TODO()
    }

    @PatchMapping("\${api.review-service.current.rout}")
    fun editReview(@PathVariable(value = "product") productId: Long, @RequestBody review: Review): Review{
        TODO()
    }

    @DeleteMapping("\${api.review-service.current.rout}")
    fun deleteReview(@PathVariable(value = "product") productId: Long,
                     @PathVariable(value = "id") reviewId: Long){
        TODO()
    }
}
