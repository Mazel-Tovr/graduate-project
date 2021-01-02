package com.mazeltov.recommendationservice.service

import com.mazeltov.common.exception.*
import com.mazeltov.common.util.*
import com.mazeltov.recommendationservice.dao.product.model.*
import com.mazeltov.recommendationservice.dao.recommendation.model.*
import com.mazeltov.recommendationservice.dao.recommendation.model.ProductGroup
import com.mazeltov.recommendationservice.dao.recommendation.repository.*
import org.springframework.beans.factory.annotation.*
import org.springframework.stereotype.*

@Service
class Recommendation {


    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var productGroupRepository: ProductGroupRepository

    @Autowired
    private lateinit var userInterestedInGroupRepository: UserInterestedInGroupRepository

    fun userVisitGroup(token: String, groupId: Long) {
        val userId = token.getUserIdFromToken() ?: throw UserDoesNotExistException("User doesn't present in token")
        val user = getOrCreateUser(userId)
        val group = getOrCreateProductGroup(groupId)
        getOrCreateUserInterestedInGroup(user, group).let { userInterestedInGroup ->
            userInterestedInGroup.visitTime.inc()
            userInterestedInGroupRepository.save(userInterestedInGroup)
        }
    }

    fun getRecommendation(): List<Product> {
        TODO("NOT IMPLEMENTED")
    }


    private fun getOrCreateUser(id: Long): User = userRepository.findByUserId(id).run {
        if (isPresent) get() else userRepository.save(User(userId = id))
    }

    private fun getOrCreateProductGroup(id: Long): ProductGroup = productGroupRepository.findByProductGroupId(id).run {
        if (isPresent) get() else productGroupRepository.save(ProductGroup(productGroupId = id))
    }

    private fun getOrCreateUserInterestedInGroup(user: User, productGroup: ProductGroup): UserInterestedInGroup =
            userInterestedInGroupRepository.findByUserAndProductGroup(user, productGroup).run {
                if (isPresent) get() else userInterestedInGroupRepository.save(UserInterestedInGroup(user = user, group = productGroup))
            }

}
