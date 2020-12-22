package com.mazeltov.recommendationservice.dao.product.repository


import com.mazeltov.recommendationservice.dao.product.model.*
import org.springframework.data.jpa.repository.*
import org.springframework.data.repository.*
import org.springframework.stereotype.*
import org.springframework.stereotype.Repository

@Repository
interface ProductOperations : JpaRepository<Product,Long> {
}
