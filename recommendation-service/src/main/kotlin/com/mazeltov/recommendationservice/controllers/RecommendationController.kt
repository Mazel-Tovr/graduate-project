package com.mazeltov.recommendationservice.controllers

import com.mazeltov.recommendationservice.dao.product.model.*
import com.mazeltov.recommendationservice.dao.product.repository.*
import com.mazeltov.recommendationservice.dao.recommendation.model.*
import com.mazeltov.recommendationservice.dao.recommendation.model.ProductGroup
import com.mazeltov.recommendationservice.dao.recommendation.repository.*
import org.springframework.beans.factory.annotation.*
import org.springframework.web.bind.annotation.*

@RestController
class RecommendationController {

    @Autowired
    private lateinit var productGroupRepository: ProductGroupRepository
    @Autowired
    private lateinit var productOperations: ProductOperations

    @GetMapping("\${api.rout}")
    fun get(): List<ProductGroup> {

       println("Hello mf")
       return productGroupRepository.findAll()

    }
    @GetMapping("/all")
    fun geta(): List<Product> {

       println("Hello mf1")
       return productOperations.findAll()

    }
}
