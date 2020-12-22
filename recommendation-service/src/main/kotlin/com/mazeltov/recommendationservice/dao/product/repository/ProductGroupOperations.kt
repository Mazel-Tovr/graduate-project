package com.mazeltov.recommendationservice.dao.product.repository

import com.mazeltov.recommendationservice.dao.product.model.*
import org.springframework.data.jpa.repository.*
import org.springframework.stereotype.*

@Repository
interface ProductGroupOperations : JpaRepository<ProductGroup,Long> {
}
