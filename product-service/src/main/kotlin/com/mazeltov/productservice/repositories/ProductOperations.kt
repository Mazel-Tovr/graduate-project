package com.mazeltov.productservice.repositories

import com.mazeltov.productservice.models.*
import org.springframework.data.domain.*
import org.springframework.data.jpa.repository.*
import org.springframework.stereotype.*

/**
 * Интерфейс для взаимодейсятвия с продуктом в базе данных
 */
@Repository
interface ProductOperations : JpaRepository<Product, Long> {
    fun findByProductGroupId(productGroupId: Long): List<Product>
    fun findByIdAndProductGroupId(productId: Long, productGroupId: Long): Product?
    @Query("Select p From Product p ORDER BY rand()")
    fun getRandomProduct(pageable: Pageable = PageRequest.of(0, 5)): List<Product>
}
