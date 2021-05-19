package com.mazeltov.common.dto

import com.mazeltov.common.security.*
import java.util.*

data class ProductDto(
    val id: Long = -1,
    val name: String,
    val amount: Int,
    val price: Int,
    val created: Date,
    val description: String,
    val productGroupId: Long,
    val isAvailable: Boolean
)

data class ProductGroupDto(
    val id: Long = -1,
    val name: String,
    val groupVariantsId: Long
)

data class GroupVariantDto(
    val id: Long = -1,
    val name: String
)

data class ReviewDto(
    val id: Long = -1,
    val productId: Long,
    val userId: Long,
    val date: Date,
    val content: String
)

data class VisitDto(
    val userName: String?,
    val groupId: Long
)

data class UserDto(
    val userName: String,
    val password: String,
    val name: String,
    val email: String
)

data class UserForAdminDto(
    val userName: String,
    val password: String,
    val name: String,
    val email: String,
    var role: UserRole
)
