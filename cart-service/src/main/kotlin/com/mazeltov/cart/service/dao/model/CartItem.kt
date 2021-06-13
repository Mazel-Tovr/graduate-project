package com.mazeltov.cart.service.dao.model

import javax.persistence.*
/**
 * Модель которая хранится в базе данных
 */
@Entity
@Table(name = "cart_item")
data class CartItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1,
    val productId: Long = -1,
    val productGroupId: Long = -1,
    val amount: Int = 1,
    val price: Int = 0
)
