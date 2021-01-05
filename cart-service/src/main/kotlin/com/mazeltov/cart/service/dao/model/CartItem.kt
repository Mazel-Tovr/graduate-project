package com.mazeltov.cart.service.dao.model

import javax.persistence.*

@Entity
@Table(name = "cart_item")
data class CartItem(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = -1,
        val productId: Long = -1,
        val productGroupId: Long = -1,
        val amount: Int = 1//,

//        @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
//        @JoinTable(
//                name = "item_to_cart",
//                joinColumns = [JoinColumn(name = "item_id")],
//                inverseJoinColumns = [JoinColumn(name = "cart_id")]
//        )
//        val items: Set<Cart> = emptySet()
)
