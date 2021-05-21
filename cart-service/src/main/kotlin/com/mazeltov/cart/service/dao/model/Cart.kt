package com.mazeltov.cart.service.dao.model

import javax.persistence.*

@Entity
@Table(name = "cart")
data class Cart(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1,
    val userName: String = "",
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinTable(
        name = "item_to_cart",
        joinColumns = [JoinColumn(name = "cart_id")],
        inverseJoinColumns = [JoinColumn(name = "item_id")]
    )
    val cart: Set<CartItem> = emptySet()
)




