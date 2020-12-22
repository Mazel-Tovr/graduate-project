package com.mazeltov.recommendationservice.dao.product.repository

import com.mazeltov.recommendationservice.dao.product.model.*
import org.springframework.data.jpa.repository.*
import org.springframework.stereotype.*

@Repository
interface GroupVariantOperations : JpaRepository<GroupVariant,Long> {
}
