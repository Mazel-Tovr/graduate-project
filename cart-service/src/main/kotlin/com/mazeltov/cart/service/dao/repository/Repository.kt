package com.mazeltov.cart.service.dao.repository

import com.mazeltov.cart.service.dao.model.*
import org.springframework.data.jpa.repository.*
import org.springframework.data.repository.query.*
import org.springframework.stereotype.*
import org.springframework.transaction.annotation.*

@Repository
interface CartRepository : JpaRepository<Cart, Long> {
    fun findByUserName(userName: String): List<Cart>
}

@Repository
interface CartItemRepository : JpaRepository<CartItem, Long> {

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE CartItem item set item.price =:price WHERE item.productId =:productId AND item.productGroupId =:productGroupId")
    fun updatePrices(
        @Param("productId") productId: Long,
        @Param("productGroupId") productGroupId: Long,
        @Param("price") price: Int
    )
}

@Repository
interface ItemToCartRepository : JpaRepository<ItemToCart, Long> {
    fun findByCartIdAndItemId(cart: Long, item: Long): ItemToCart?
}
