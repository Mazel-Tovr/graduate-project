package com.mazeltov.productservice.feignclients

import com.mazeltov.common.dto.*
import com.mazeltov.productservice.config.*
import com.mazeltov.productservice.models.*
import com.mazeltov.productservice.repositories.*
import org.springframework.beans.factory.annotation.*
import org.springframework.cloud.openfeign.*
import org.springframework.http.*
import org.springframework.stereotype.*
import org.springframework.web.bind.annotation.*



/**
 * Феин клинет для сервиса рекоммендаций
 */
@FeignClient(
    name = "\${recommendation-service.name}",
    fallback = RecommendationServiceFeignClient.RecommendationServiceDefaultRealisation::class,
    configuration = [Configure::class]
)
interface RecommendationServiceFeignClient {

    @PostMapping("\${api.recommendation-service.rout}")
    fun userViewedProduct(@RequestBody visitDto: VisitDto?): ResponseEntity<Any>


    @GetMapping("\${api.recommendation-service.current-user.rout}")
    fun getRecommendations(@PathVariable(value = "userName") userName: String?): List<Product>

    @GetMapping("\${api.recommendation-service.current-user.certain-count.rout}")
    fun getCertainAmountOfRecommendations(
        @PathVariable(value = "userName") userName: String?,
        @PathVariable(value = "count") count: Int
    ): List<Product>

    @Component
    class RecommendationServiceDefaultRealisation : RecommendationServiceFeignClient {
        @Autowired
        private lateinit var productOperations: ProductOperations

        override fun userViewedProduct(visitDto: VisitDto?): ResponseEntity<Any> {
            return ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE)
        }

        override fun getRecommendations(userName: String?): List<Product> {
            return productOperations.getRandomProduct()
        }

        override fun getCertainAmountOfRecommendations(userName: String?, count: Int): List<Product> {
            return productOperations.getRandomProduct()
        }

    }

}
