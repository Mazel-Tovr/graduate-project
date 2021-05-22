package com.mazeltov.orderservice.dao.model

import javax.persistence.*

@Entity
@Table(name = "order_item")
data class OrderItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1,
    val price: Int = -1,
    val amount: Int = -1,
    @Column(name = "product_id")
    val productId: Long = -1,
    @Column(name = "product_group_id")
    val groupId: Long = -1
)
