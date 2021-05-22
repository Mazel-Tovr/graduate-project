package com.mazeltov.review.repositories

import com.mazeltov.review.models.*
import org.springframework.data.jpa.repository.*
import org.springframework.stereotype.*

@Repository
interface UserOperations : JpaRepository<User, Long> {
    fun findByUserName(userName: String): User?
}
