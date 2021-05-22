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
    val userName: String,
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

data class CartItemAmountDto(
    val productId: Long,
    val productGroupId: Long,
    val newAmount: Int
)

data class CartDto(
    val cartId: Long,
    val cart: Set<CartItemDto> = emptySet(),
    val price: Int
)

data class CartItemDto(
    val productId: Long = -1,
    val productGroupId: Long = -1,
    val amount: Int = 1,
    val price: Int = 0
)

data class OrderDto(
    val name: String,
    val address: String,
    val city: String,
    val comment: String = "",
    val created: Date = Date(),
    var status: String = "",
    val items: Set<OrderItemDto>,
    val totalPrice: Long
)

data class OrderInfoDto(
    val name: String,
    val address: String,
    val city: String,
    val comment: String = "",
    val status: String = ""
)

data class OrderItemDto(
    val price: Int = -1,
    val amount: Int = -1,
    val productId: Long = -1,
    val groupId: Long = -1
)
