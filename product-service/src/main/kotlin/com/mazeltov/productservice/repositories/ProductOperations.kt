package com.mazeltov.productservice.repositories

import com.mazeltov.productservice.models.*
import org.springframework.data.jpa.repository.*
import org.springframework.stereotype.*

@Repository
interface ProductOperations : JpaRepository<Product,Long>{
}
