package com.mazeltov.recommendationservice.dao.recommendation.repository

import com.mazeltov.recommendationservice.dao.recommendation.model.*
import org.springframework.data.jpa.repository.*
import org.springframework.stereotype.*
import java.util.*

@Repository
interface UserInterestedInGroupRepository : JpaRepository<UserInterestedInGroup, Long> {

    fun findByUserAndProductGroup(user: User, productGroup: ProductGroup): UserInterestedInGroup?

    fun findAllByUserId(userId: Long): List<UserInterestedInGroup>

    fun findFirstByUserOrderByVisitTimeDesc(user: User) : UserInterestedInGroup?
}
