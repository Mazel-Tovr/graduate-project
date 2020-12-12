package com.mazeltov.review.service.repositories

import com.mazeltov.review.service.models.*
import org.springframework.data.jpa.repository.*
import org.springframework.stereotype.*

@Repository
interface ReviewOperations : JpaRepository<Long,Review> {
}
