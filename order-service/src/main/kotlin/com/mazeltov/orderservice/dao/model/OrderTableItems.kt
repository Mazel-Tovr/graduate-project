package com.mazeltov.orderservice.dao.model

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "order_table_item")
data class OrderTableItems(
    @EmbeddedId
    val id: OrderTableItemsKey = OrderTableItemsKey(),
    @ManyToOne(cascade = [CascadeType.ALL])
    @MapsId("orderId")
    @JoinColumn(name = "order_id", unique = false)
    val order: Order = Order(),

    @ManyToOne(cascade = [CascadeType.ALL])
    @MapsId("itemId")
    @JoinColumn(name = "item_id")
    val item: OrderItem = OrderItem()
)

@Embeddable
data class OrderTableItemsKey(
    @Column(name = "order_id")
    var orderId: Long = -1,
    @Column(name = "item_id")
    var itemId: Long = -1
) : Serializable
