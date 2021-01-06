package com.mazeltov.cart.service.dao.model

import java.io.*
import javax.persistence.*


@Entity
@Table(name = "item_to_cart")
data class ItemToCart(
        @EmbeddedId
        val id: ItemToCartKey = ItemToCartKey(),
        @ManyToOne(cascade = [CascadeType.REFRESH])
        @MapsId("cartId")
        @JoinColumn(name = "cart_id", unique = false)
        val cart: Cart = Cart(),
        @ManyToOne(cascade = [CascadeType.REMOVE, CascadeType.REFRESH])
        @MapsId("itemId")
        @JoinColumn(name = "item_id")
        val item: CartItem = CartItem()
)

@Embeddable
data class ItemToCartKey(
        @Column(name = "cart_id")
        var cartId: Long = -1,
        @Column(name = "item_id")
        var itemId: Long = -1
) : Serializable
