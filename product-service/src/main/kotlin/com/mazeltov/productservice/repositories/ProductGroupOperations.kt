package com.mazeltov.productservice.repositories


import com.mazeltov.productservice.models.*
import org.springframework.data.jpa.repository.*
import org.springframework.stereotype.*

@Repository
interface ProductGroupOperations : JpaRepository<ProductGroup, Long> {
    fun findByGroupVariantId(groupVariantId: Long): List<ProductGroup>
    fun findByIdAndGroupVariantId(groupId: Long, GroupVariantId: Long): ProductGroup?
}
