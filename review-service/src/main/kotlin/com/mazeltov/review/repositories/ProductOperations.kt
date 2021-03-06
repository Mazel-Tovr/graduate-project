package com.mazeltov.review.repositories

import com.mazeltov.review.models.*
import org.springframework.data.jpa.repository.*
import org.springframework.stereotype.*

@Repository
interface ProductOperations : JpaRepository<Product, Long> {
    fun findByProductId(productId: Long): Product?
}
