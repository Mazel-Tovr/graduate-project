package com.mazeltov.productservice.repositories


import com.mazeltov.productservice.models.*
import org.springframework.data.jpa.repository.*
import org.springframework.stereotype.*

@Repository
interface ProductGroupOperations : JpaRepository<ProductGroup, Long> {
    fun findByGroupVariantsId(groupVariantId: Long): List<ProductGroup>
    fun findByIdAndGroupVariantsId(groupId: Long, GroupVariantId: Long): ProductGroup?
}
