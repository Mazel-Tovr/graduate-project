package com.mazeltov.common.dto

import java.util.*

data class ProductDto(
        val id: Long = -1,
        val name: String,
        val created: Date,
        val description: String,
        val productGroupId: Long,
        val isAvailable: Boolean
)

data class ReviewDto(
        val id: Long = -1,
        val productId: Long,
        val userId: Long,
        val date: Date,
        val content: String
)

data class VisitDto(
        val userId: Long,
        val groupId: Long
)

data class UserDto(
        val userName: String,
        val password: String,
        val name: String,
        val email: String

)


