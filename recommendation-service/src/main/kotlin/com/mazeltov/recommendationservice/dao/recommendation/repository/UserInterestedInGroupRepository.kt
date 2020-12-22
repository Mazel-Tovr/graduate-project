package com.mazeltov.recommendationservice.dao.recommendation.repository

import com.mazeltov.recommendationservice.dao.recommendation.model.*
import org.springframework.data.jpa.repository.*
import org.springframework.stereotype.*
import java.util.*

@Repository
interface UserInterestedInGroupRepository : JpaRepository<UserInterestedInGroup, Long> {

    //TODO test this //Check for nullable if userIntG is not present !!!!
    fun findByUserAndProductGroup(user: User, productGroup: ProductGroup): Optional<UserInterestedInGroup>

    fun findAllByUserId(userId: Long): List<UserInterestedInGroup>

}
