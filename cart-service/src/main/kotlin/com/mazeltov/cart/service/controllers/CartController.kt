package com.mazeltov.cart.service.controllers

import com.mazeltov.cart.service.dao.model.*
import com.mazeltov.cart.service.dao.repository.*
import com.mazeltov.cart.service.services.*
import com.mazeltov.common.dto.*
import org.springframework.beans.factory.annotation.*
import org.springframework.http.*
import org.springframework.web.bind.annotation.*

@RestController
class CartController {

    @Autowired
    private lateinit var cartService: CartService

    @Autowired
    private lateinit var cartService1: CartItemRepository

    @GetMapping("\${api.cart-service.current.rout}")
    fun getCart(@PathVariable(value = "userId") userId: Long): Cart {
        return cartService.getOrCart(userId)
    }


    @PutMapping("\${api.cart-service.current.amount.rout}")
    fun addProductToCart(@PathVariable(value = "cartId") cartId: Long,
                         @PathVariable(value = "amount") amount: Int,
                         @RequestBody productDto: ProductDto): ResponseEntity<*> {
        return runCatching { cartService.addProductIntoCart(cartId, productDto, amount) }
                .onFailure {
                    return ResponseEntity(it.message, HttpStatus.BAD_REQUEST)
                }.getOrElse {
                    return ResponseEntity("Server error ${it.message}", HttpStatus.INTERNAL_SERVER_ERROR)
                }.let {
                    ResponseEntity(it, HttpStatus.OK)
                }
    }

    @PatchMapping("\${api.cart-service.current.amount.rout}")
    fun changeAmountOfProducts(@PathVariable(value = "cartId") cartId: Long,
                               @PathVariable(value = "amount") amount: Int,
                               @RequestBody cartItem: CartItem): ResponseEntity<*> {
        return runCatching { cartService.changeProductAmount(cartId, cartItem, amount) }
                .onFailure {
                    return ResponseEntity(it.message, HttpStatus.BAD_REQUEST)
                }.getOrElse {
                    return ResponseEntity("Server error ${it.message}", HttpStatus.INTERNAL_SERVER_ERROR)
                }.let {
                    ResponseEntity(it, HttpStatus.OK)
                }

    }


}
