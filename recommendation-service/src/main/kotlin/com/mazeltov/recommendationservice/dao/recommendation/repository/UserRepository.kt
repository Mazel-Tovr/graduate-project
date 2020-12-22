package com.mazeltov.recommendationservice.dao.recommendation.repository

import com.mazeltov.recommendationservice.dao.recommendation.model.*
import org.springframework.data.jpa.repository.*
import org.springframework.stereotype.*
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Long> {

    fun findByUserId(userId: Long): Optional<User>
}
