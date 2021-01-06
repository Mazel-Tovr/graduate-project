package com.mazeltov.orderservice.dao.repository

import com.mazeltov.orderservice.dao.model.*
import org.springframework.data.jpa.repository.*
import org.springframework.stereotype.*

@Repository
interface OrderItemRepository : JpaRepository<OrderItem, Long>
