package com.mazeltov.product.service.repositories

import com.mazeltov.product.service.models.*
import org.springframework.data.jpa.repository.*
import org.springframework.stereotype.*

@Repository
interface ProductOperations : JpaRepository<Product,Long>{
}
