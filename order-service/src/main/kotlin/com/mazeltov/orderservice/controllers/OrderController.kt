package com.mazeltov.orderservice.controllers

import com.mazeltov.common.*
import com.mazeltov.common.dto.*
import com.mazeltov.common.security.*
import com.mazeltov.orderservice.services.*
import org.springframework.beans.factory.annotation.*
import org.springframework.http.*
import org.springframework.web.bind.annotation.*

@RestController
class OrderController {

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    @Value("\${jwt.header}")
    private lateinit var header: String

    @Autowired
    private lateinit var orderService: OrderService

    @PostMapping("\${api.order-service.user-orders.rout}")
    fun createOrder(@PathVariable("userName") userName: String, @RequestBody order: OrderDto): ResponseEntity<*> {
        return checkHeaders(userName) { orderService.createOrder(userName, order) }
    }

    @GetMapping("\${api.order-service.user-orders.current.rout}")
    fun getOrder(
        @PathVariable("userName") userName: String,
        @PathVariable("orderId") orderId: Long
    ): ResponseEntity<*> = runCatching {
        checkHeaders(userName) { orderService.getOrder(userName, orderId) }
    }.getOrElse {
        "${it.message}".toResponseEntity(HttpStatus.BAD_REQUEST)
    }


    @GetMapping("\${api.order-service.user-orders.rout}")
    fun getAllOrders(@PathVariable("userName") userName: String): ResponseEntity<*> {
        return checkHeaders(userName) { orderService.getAllOrders(userName) }
    }

    @PatchMapping("\${api.order-service.user-orders.current.item.rout}")
    fun editOrderItem(
        @PathVariable("userName") userName: String,
        @PathVariable("orderId") orderId: Long,
        @PathVariable("itemId") itemId: Long,
        @RequestBody itemDto: OrderItemDto
    ) = runCatching {
        checkHeaders(userName) { orderService.editOrderItem(userName, orderId, itemId, itemDto) }
    }.getOrElse {
        "${it.message}".toResponseEntity(HttpStatus.BAD_REQUEST)
    }

    @PutMapping("\${api.order-service.user-orders.current.info.rout}")
    fun editItemOrderInfo(
        @PathVariable("userName") userName: String,
        @PathVariable("orderId") orderId: Long,
        @RequestBody infoDto: OrderInfoDto
    ) = runCatching {
        checkHeaders(userName) { orderService.editOrderInfo(userName, orderId, infoDto) }
    }.getOrElse {
        "${it.message}".toResponseEntity(HttpStatus.BAD_REQUEST)
    }

    @PatchMapping("\${api.order-service.user-orders.current.info.rout}")
    fun editItemOrderStatus(
        @PathVariable("userName") userName: String,
        @PathVariable("orderId") orderId: Long,
        @RequestBody infoDto: OrderInfoDto
    ) = runCatching {
        checkHeaders(userName) { orderService.editOrderInfoStatus(userName, orderId, infoDto) }
    }.getOrElse {
        "${it.message}".toResponseEntity(HttpStatus.BAD_REQUEST)
    }

    private inline fun checkHeaders(userName: String, block: () -> Any): ResponseEntity<*> {
        return if (getUserIdFromRequest(header, secret) == userName ||
            getUserRoleFromRequest(header, secret) == UserRole.ADMIN
        ) {
            block().toResponseEntity()
        } else {
            "This order doesn't belong to you".toResponseEntity(HttpStatus.FORBIDDEN)
        }
    }

}
