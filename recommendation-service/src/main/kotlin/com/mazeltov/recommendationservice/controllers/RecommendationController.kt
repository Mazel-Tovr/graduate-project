package com.mazeltov.recommendationservice.controllers

import com.mazeltov.common.dto.*
import com.mazeltov.recommendationservice.dao.product.model.*
import com.mazeltov.recommendationservice.service.*
import org.springframework.beans.factory.annotation.*
import org.springframework.http.*
import org.springframework.web.bind.annotation.*

@RestController
class RecommendationController {

    @Autowired
    private lateinit var recommendation: Recommendation

    @PostMapping("\${api.recommendation-service.rout}")
    fun userViewedProduct(@RequestBody visitDto: VisitDto): ResponseEntity<Any> {
        recommendation.userVisitGroup(visitDto.userId, visitDto.groupId)
        return ResponseEntity(HttpStatus.OK)
    }

    @GetMapping("\${api.recommendation-service.current-user.rout}")
    fun getRecommendations(@PathVariable(value = "id") userId: Long): List<Product> {
        return recommendation.getRecommendations(userId)
    }

    @GetMapping("\${api.recommendation-service.current-user.certain-count.rout}")
    fun getCertainAmountOfRecommendations(
            @PathVariable(value = "id") userId: Long,
            @PathVariable(value = "count") count: Int
    ): List<Product> {
        return recommendation.getRecommendations(userId, count)
    }
}
