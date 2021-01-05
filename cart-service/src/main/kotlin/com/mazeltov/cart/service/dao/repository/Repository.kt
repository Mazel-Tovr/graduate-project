package com.mazeltov.cart.service.dao.repository

import com.mazeltov.cart.service.dao.model.*
import org.springframework.data.jpa.repository.*
import org.springframework.stereotype.*

@Repository
interface CartRepository : JpaRepository<Cart, Long> {
    fun findByUserId(userId: Long): Cart?
}

@Repository
interface CartItemRepository : JpaRepository<CartItem, Long>

@Repository
interface ItemToCartRepository : JpaRepository<ItemToCart, Long> {
    fun findByCartIdAndItemId(cart: Long, item: Long): ItemToCart?
}
