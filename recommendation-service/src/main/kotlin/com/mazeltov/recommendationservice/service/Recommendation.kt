package com.mazeltov.recommendationservice.service

import com.mazeltov.common.exception.*
import com.mazeltov.recommendationservice.dao.product.model.*
import com.mazeltov.recommendationservice.dao.product.repository.*
import com.mazeltov.recommendationservice.dao.recommendation.model.*
import com.mazeltov.recommendationservice.dao.recommendation.model.ProductGroup
import com.mazeltov.recommendationservice.dao.recommendation.repository.*
import org.springframework.beans.factory.annotation.*
import org.springframework.data.domain.*
import org.springframework.stereotype.*

@Service
class Recommendation {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var productGroupRepository: ProductGroupRepository

    @Autowired
    private lateinit var productOperations: ProductOperations

    @Autowired
    private lateinit var userInterestedInGroupRepository: UserInterestedInGroupRepository

    fun userVisitGroup(userId: Long, groupId: Long) {
        val user = getOrCreateUser(userId)
        val group = getOrCreateProductGroup(groupId)
        getOrCreateUserInterestedInGroup(user, group).let { userInterestedInGroup ->
            userInterestedInGroup.visitTime++
            userInterestedInGroupRepository.save(userInterestedInGroup)
        }
    }

    fun getRecommendations(userId: Long, count: Int = 5): List<Product> {

        val groupId = userInterestedInGroupRepository.findFirstByUserOrderByVisitTimeDesc(getOrCreateUser(userId))?.productGroup?.id
                ?: productOperations.getRandomProduct().firstOrNull()?.productGroup?.id
                ?: throw EmptyDdExistException("Database is Empty")

        return productOperations.getRandomProductByProductGroup(groupId, PageRequest.of(0, count))
    }


    private fun getOrCreateUser(id: Long): User = userRepository.findByUserId(id)
            ?: userRepository.save(User(userId = id))

    private fun getOrCreateProductGroup(id: Long): ProductGroup = productGroupRepository.findByProductGroupId(id)
            ?: productGroupRepository.save(ProductGroup(productGroupId = id))


    private fun getOrCreateUserInterestedInGroup(user: User, productGroup: ProductGroup): UserInterestedInGroup =
            userInterestedInGroupRepository.findByUserAndProductGroup(user, productGroup)
                    ?: userInterestedInGroupRepository.save(UserInterestedInGroup(user = user, productGroup = productGroup))


}
