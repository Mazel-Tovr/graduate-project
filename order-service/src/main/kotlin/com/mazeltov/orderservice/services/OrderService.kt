package com.mazeltov.orderservice.services

import com.mazeltov.common.dto.*
import com.mazeltov.common.exception.*
import com.mazeltov.orderservice.dao.model.*
import com.mazeltov.orderservice.dao.repository.*
import org.springframework.beans.factory.annotation.*
import org.springframework.stereotype.*
import org.springframework.transaction.annotation.*

@Service
class OrderService {
    @Autowired
    private lateinit var orderRepository: OrderRepository

    @Autowired
    private lateinit var orderItemRepository: OrderItemRepository


    fun createOrder(userName: String, order: OrderDto): Order {

        //TODO Feing request to prod

        return orderRepository.save(Order(
            userName = userName,
            name = order.name,
            address = order.address,
            city = order.city,
            comment = order.comment,
            status = Status.LAYOUT,
            totalPrice = order.items.sumBy { it.price },
            items = order.items.map {
                OrderItem(price = it.price,
                    amount = it.amount,
                    productId = it.productId,
                    groupId = it.groupId)
            }.toSet()
        ))
    }


    fun getOrder(userName: String, orderId: Long): Order {
        return orderRepository.findByUserNameAndId(userName, orderId)
            ?: throw OrderDoesNotExistException("Order doesn't exist exception")
    }


    fun getAllOrders(userName: String): List<Order> {
        return orderRepository.findAllByUserName(userName)
    }


    fun editOrderItem(
        userName: String,
        orderId: Long,
        itemId: Long,
        itemDto: OrderItemDto
    ): Order = orderRepository.findById(orderId).takeIf { it.isPresent }?.get()?.let { prevOrder ->
        val itemToReplace = prevOrder.items.find { items ->
            items.productId == itemDto.productId && items.groupId == itemDto.groupId
        } ?: throw ProductNotInOrderException("Product not in order")
        if (itemDto.amount <= 0) {
            orderRepository.save(prevOrder.copy(items = prevOrder.items - itemToReplace))
        } else {
            prevOrder.copy(items = (prevOrder.items - itemToReplace) + orderItemRepository.save(itemToReplace.copy(
                amount = itemDto.amount)))
        }
    } ?: throw OrderDoesNotExistException("Order doesn't exist exception")


    fun editOrderInfo(
        userName: String,
        orderId: Long,
        infoDto: OrderInfoDto
    ): Order = orderRepository.findById(orderId).takeIf { it.isPresent }?.get()?.let { prevOrder ->
        orderRepository.save(prevOrder.copy(name = infoDto.name,
            address = infoDto.address,
            city = infoDto.city,
            comment = infoDto.comment))
    } ?: throw OrderDoesNotExistException("Order doesn't exist exception")


    fun editOrderInfoStatus(
        userName: String,
        orderId: Long,
        infoDto: OrderInfoDto
    ): Order = orderRepository.findById(orderId).takeIf { it.isPresent }?.get()?.let { prevOrder ->
        val status = runCatching { Status.valueOf(infoDto.status) }.getOrNull() ?: throw UnknownOrderStatusException("Unknown Order Status")
        orderRepository.save(prevOrder.copy(name = infoDto.name,
            status = status))
    } ?: throw OrderDoesNotExistException("Order doesn't exist exception")


}


// ORDER EDIT
//        return orderRepository.findByUserIdAndId(userName, order.id)?.also { oldOrder ->
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
//        orderRepository.findById(orderId).takeIf { it.isPresent }?.get()?.let { prevOrder ->
//            if (prevOrder.status != Status.LAYOUT) throw  OrderEditingException("Order can't bee edited it's status is ${prevOrder.status}")
//            val list: MutableList<OrderItem> = mutableListOf()
//            order.items.map { item ->
//                prevOrder.items.find { it.groupId == item.groupId && it.productId == item.productId }
//                    ?.run {
//                        if (item.amount > 0)
//                            orderItemRepository.save(copy(price = item.price, amount = item.price))
//                        else {
//                            orderItemRepository.delete(this)
//                            copy(amount = 0)
//                        }
//                    }
//                    ?: OrderItem(
//                        price = item.price,
//                        amount = item.amount,
//                        productId = item.productId,
//                        groupId = item.groupId
//                    ).also { list.add(it) }
//            }.filter { it.amount > 0 }.toSet()
//            orderRepository.flush()
//            return orderRepository.findById(orderId).get().run {
//                val newItems = items + list
//                orderRepository.save(copy(
//                    name = order.name,
//                    comment = order.comment,
//                    address = order.address,
//                    city = order.city,
//                    items = newItems,
//                    totalPrice = newItems.sumBy { it.amount * it.price },
//                    status = prevOrder.status.takeIf { newItems.isNotEmpty() } ?: Status.CANCELLED
//                ))
//            }
//        } ?: throw OrderDoesNotExistException("Order doesn't exist exception")

//        order.items.forEach { newItem ->
//            orderItemRepository.findById(newItem.id).ifPresent {
//                orderItemRepository.save(it.copy(
//                    productId = newItem.productId,
//                    groupId = newItem.productId,
//                    price = newItem.price))
//            }
//        }
//        return orderRepository.findByUserNameAndId(userName, order.id)?.also { oldOrder ->
//            orderRepository.save(
//                oldOrder.copy(name = order.name,
//                    address = order.address,
//                    city = order.city,
//                    comment = order.comment,
//                    items = order.items,
//                    totalPrice = order.items.map { it.price }.sum()))
//        } ?: throw OrderDoesNotExistException("Order doesn't exist exception")
