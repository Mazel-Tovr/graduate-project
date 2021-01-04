package com.mazeltov.recommendationservice.dao.product.repository


import com.mazeltov.recommendationservice.dao.product.model.*
import org.springframework.data.domain.*
import org.springframework.data.jpa.repository.*
import org.springframework.data.repository.*
import org.springframework.stereotype.*
import org.springframework.stereotype.Repository

@Repository
interface ProductOperations : JpaRepository<Product, Long> {

    @Query("Select p From Product p Where p.productGroup.id = ?1 ORDER BY rand()")
    fun getRandomProductByProductGroup(productGroupId: Long, pageable: Pageable): List<Product>

    @Query("Select p From Product p ORDER BY rand()")
    fun getRandomProduct(pageable: Pageable = PageRequest.of(0, 1)): List<Product>

}
