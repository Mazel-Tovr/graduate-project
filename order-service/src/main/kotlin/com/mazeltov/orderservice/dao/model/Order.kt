package com.mazeltov.orderservice.dao.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "order_table")
data class Order(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = -1,
        val userId: Long = -1,
        var name: String = "",
        var address: String = "",
        var city: String = "",
        @Enumerated(EnumType.STRING)
        var status: Status = Status.UNKNOWN,
        var comment: String = "",
        var totalPrice: Long = -1,
        var created: Date = Date(),
        @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER, orphanRemoval = true)
        @JoinTable(
                name = "order_table_item",
                joinColumns = [JoinColumn(name = "order_id")],
                inverseJoinColumns = [JoinColumn(name = "item_id")]
        )
        val items: MutableSet<OrderItem> = mutableSetOf()
)
