package com.mazeltov.orderservice.services

import com.mazeltov.common.exception.*
import com.mazeltov.orderservice.dao.model.*
import com.mazeltov.orderservice.dao.repository.*
import org.springframework.beans.factory.annotation.*
import org.springframework.stereotype.*

@Service
class OrderService {
    @Autowired
    private lateinit var orderRepository: OrderRepository

    @Autowired
    private lateinit var orderItemRepository: OrderItemRepository


    fun createOrder(userId: Long, order: Order): Order {

        return orderRepository.save(order.apply {
            status = Status.LAYOUT; totalPrice = if (totalPrice == -1L) {
            order.items.map { it.price }.sum()
        } else {
            totalPrice
        }
        })
    }


    fun getOrder(userId: Long, orderId: Long): Order {
        return orderRepository.findByUserIdAndId(userId, orderId)
                ?: throw OrderDoesNotExistException("Order doesn't exist exception")
    }


    fun getAllOrder(userId: Long): List<Order> {
        return orderRepository.findAllByUserId(userId)
    }


    //TODO doesn't work
    fun editOrder(userId: Long, order: Order): Order {
//        return orderRepository.findByUserIdAndId(userId, order.id)?.also { oldOrder ->
//            orderRepository.save(oldOrder.copy(
//                    name = order.name,
//                    address = order.address,
//                    city = order.city,
//                    comment = order.comment,
//                    items = order.items,
//                    totalPrice = order.items.map { it.price }.sum()
//
//            ))
//        }
        order.items.forEach { newItem ->
            orderItemRepository.findById(newItem.id).ifPresent {
                orderItemRepository.save(it.copy(
                        productId = newItem.productId,
                        groupId = newItem.productId,
                        price = newItem.price))
            }
        }
        return orderRepository.findByUserIdAndId(userId, order.id)?.also { oldOrder ->
            orderRepository.save(
                    oldOrder.copy(name = order.name,
                    address = order.address,
                    city = order.city,
                    comment = order.comment,
                    items = order.items,
                    totalPrice = order.items.map { it.price }.sum()))
        } ?: throw OrderDoesNotExistException("Order doesn't exist exception")
    }

    fun changeOrderStatus(userId: Long, orderId: Long, status: Status) {

        orderRepository.findByUserIdAndId(userId, orderId)?.let {
            orderRepository.save(it.copy(status = status))
        } ?: throw OrderDoesNotExistException("Order doesn't exist exception")
    }


}
