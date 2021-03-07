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
    fun userViewedProduct(@RequestBody visitDto: VisitDto?): ResponseEntity<Any> {
        visitDto?.let {
            recommendation.userVisitGroup(it.userName, it.groupId)
            return ResponseEntity(HttpStatus.OK)
        }
        return ResponseEntity("Guest user", HttpStatus.OK)
    }

    @GetMapping("\${api.recommendation-service.current-user.rout}")
    fun getRecommendations(@PathVariable(value = "userName") userName: String?): List<Product> {
        return recommendation.getRecommendations(userName)
    }

    @GetMapping("\${api.recommendation-service.current-user.certain-count.rout}")
    fun getCertainAmountOfRecommendations(
        @PathVariable(value = "userName") userName: String?,
        @PathVariable(value = "count") count: Int
    ): List<Product> {
        return recommendation.getRecommendations(userName, count)
    }
}
