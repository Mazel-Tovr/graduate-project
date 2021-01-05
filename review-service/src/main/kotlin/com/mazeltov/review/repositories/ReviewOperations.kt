package com.mazeltov.review.repositories

import com.mazeltov.review.models.*
import org.springframework.data.domain.*
import org.springframework.data.jpa.repository.*
import org.springframework.stereotype.*

@Repository
interface ReviewOperations : JpaRepository<Review, Long> {

    fun findAllByProductId(productId: Long): List<Review>

    fun findAllByProductId(productId: Long, pageable: Pageable): List<Review>

    fun findByProductIdAndId(productId: Long, id: Long): Review?


}
