package com.mazeltov.orderservice.controllers

import com.mazeltov.orderservice.dao.model.*
import com.mazeltov.orderservice.services.*
import org.springframework.beans.factory.annotation.*
import org.springframework.web.bind.annotation.*

@RestController
class OrderController {

    @Autowired
    private lateinit var orderService: OrderService

    @PostMapping("\${api.order-service.user-orders.rout}")
    fun createOrder(@PathVariable("userId") userId: Long, @RequestBody order: Order): Order {
        return orderService.createOrder(userId, order)
    }

    @GetMapping("\${api.order-service.user-orders.current.rout}")
    fun getOrder(@PathVariable("userId") userId: Long, @PathVariable("orderId") orderId: Long): Order {
        return orderService.getOrder(userId, orderId)
    }

    @GetMapping("\${api.order-service.user-orders.rout}")
    fun getAllOrder(@PathVariable("userId") userId: Long): List<Order> {
        return orderService.getAllOrder(userId)
    }

    @PatchMapping("\${api.order-service.user-orders.rout}")
    fun editOrder(@PathVariable("userId") userId: Long, @RequestBody order: Order): Order {
        return orderService.editOrder(userId, order)
    }


}
