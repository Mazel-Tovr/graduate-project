package com.mazeltov.recommendationservice.service

import com.mazeltov.recommendationservice.dao.product.model.*
import com.mazeltov.recommendationservice.dao.product.repository.*
import com.mazeltov.recommendationservice.dao.recommendation.model.*
import com.mazeltov.recommendationservice.dao.recommendation.model.ProductGroup
import com.mazeltov.recommendationservice.dao.recommendation.repository.*
import com.mazeltov.recommendationservice.exception.*
import com.mazeltov.recommendationservice.feing.*
import org.springframework.beans.factory.annotation.*
import org.springframework.stereotype.*

@Service
class Recommendation {

    @Autowired
    private lateinit var authorizationService: AuthorizationServiceFeignClient

    @Autowired
    private lateinit var productGroupRepositoryMain: ProductGroupOperations

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var productGroupRepository: ProductGroupRepository

    @Autowired
    private lateinit var userInterestedInGroupRepository: UserInterestedInGroupRepository

    fun userVisitGroup(userId: Long, groupId: Long) {
        validateUserAndGroupOnExistence(userId, groupId)
        val user = createUserIfNotPresent(userId)
        val group = createProductGroupIfNotPresent(groupId)
        createUserInterestedInGroupRepositoryIfNotPresent(user, group).let { userInterestedInGroup ->
            userInterestedInGroup.visitTime.inc()
            userInterestedInGroupRepository.save(userInterestedInGroup)
        }
    }

    fun getRecommendation() : List<Product>{
        TODO("NOT IMPLEMENTED")
    }


    private fun validateUserAndGroupOnExistence(userId: Long, groupId: Long) {
        if (authorizationService.getUserById(userId) == null)
            throw UserIsNotExistException("User with such id=$userId doesn't exists")
        if (!productGroupRepositoryMain.findById(groupId).isPresent)
            throw ProductGroupIsNotExistException("Product group whit such id=$groupId doesn't exists")
    }

    private fun createUserIfNotPresent(id: Long) = userRepository.findByUserId(id).run {
        if (isPresent) get() else userRepository.save(User(userId = id))
    }

    private fun createProductGroupIfNotPresent(id: Long) = productGroupRepository.findByProductGroupId(id).run {
        if (isPresent) get() else productGroupRepository.save(ProductGroup(productGroupId = id))
    }

    private fun createUserInterestedInGroupRepositoryIfNotPresent(user: User, productGroup: ProductGroup) =
            userInterestedInGroupRepository.findByUserAndProductGroup(user, productGroup).run {
                if (isPresent) get() else userInterestedInGroupRepository.save(UserInterestedInGroup(user = user, group = productGroup))
            }

}
