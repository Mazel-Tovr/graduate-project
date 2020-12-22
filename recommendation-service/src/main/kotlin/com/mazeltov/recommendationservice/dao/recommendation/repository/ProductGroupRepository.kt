package com.mazeltov.recommendationservice.dao.recommendation.repository

import com.mazeltov.recommendationservice.dao.recommendation.model.*
import org.springframework.data.jpa.repository.*
import org.springframework.stereotype.*
import java.util.*

@Repository
interface ProductGroupRepository : JpaRepository<ProductGroup, Long> {

    fun findByProductGroupId(productGroupId: Long): Optional<ProductGroup>

}
