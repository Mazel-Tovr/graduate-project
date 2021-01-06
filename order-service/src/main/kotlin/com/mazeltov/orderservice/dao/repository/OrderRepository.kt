package com.mazeltov.orderservice.dao.repository

import com.mazeltov.orderservice.dao.model.*
import org.springframework.data.jpa.repository.*
import org.springframework.stereotype.*

@Repository
interface OrderRepository : JpaRepository<Order, Long> {
    fun findByUserIdAndId(userId: Long, orderId: Long): Order?
    fun findAllByUserId(userId: Long): List<Order>
}
