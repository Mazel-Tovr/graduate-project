package com.mazeltov.cart.service.controllers

import com.mazeltov.cart.service.services.*
import com.mazeltov.common.*
import com.mazeltov.common.dto.*
import org.springframework.beans.factory.annotation.*
import org.springframework.http.*
import org.springframework.web.bind.annotation.*

@RestController
class CartController {

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    @Value("\${jwt.header}")
    private lateinit var header: String

    @Autowired
    private lateinit var cartService: CartService

    @GetMapping("\${api.cart-service.user.rout}")
    fun getOrCreateAllUserCarts(@PathVariable(value = "userName") userName: String): ResponseEntity<*> =
        checkHeaders(userName) {
            cartService.getOrCreateCarts(userName)
        }


    @GetMapping("\${api.cart-service.user.current.rout}")
    fun getCart(
        @PathVariable(value = "userName") userName: String,
        @PathVariable(value = "cartId") cartId: Long
    ): ResponseEntity<*> = runCatching {
        checkHeaders(userName) {
            cartService.getCart(cartId)
        }
    }.getOrElse {
        return "${it.message}".toResponseEntity(HttpStatus.BAD_REQUEST)
    }


    @PostMapping("\${api.cart-service.user.current.rout}")
    fun addProductToCart(
        @PathVariable(value = "userName") userName: String,
        @PathVariable(value = "cartId") cartId: Long,
        @RequestParam(value = "amount", required = false) amount: Int?,
        @RequestBody productDto: ProductDto
    ): ResponseEntity<*> = runCatching {
        checkHeaders(userName) {
            cartService.addProductIntoCart(cartId, productDto, amount ?: 1)
        }
    }.onFailure {
        return  ResponseEntity(it.message, HttpStatus.BAD_REQUEST)
    }.getOrElse {
        return ResponseEntity(it.message, HttpStatus.BAD_REQUEST)
    }


    @PatchMapping("\${api.cart-service.user.current.rout}")
    fun changeAmountOfProducts(
        @PathVariable(value = "userName") userName: String,
        @PathVariable(value = "cartId") cartId: Long,
        @RequestBody cartItem: CartItemAmountDto
    ): ResponseEntity<*> = runCatching {
        checkHeaders(userName) {
            cartService.changeProductAmount(cartId, cartItem)
        }
    }.getOrElse {
        return ResponseEntity(it.message, HttpStatus.BAD_REQUEST)
    }

    @PutMapping("\${api.cart-service.rout}")
    fun updatePricesInAllCarts(@RequestBody productDto: ProductDto): ResponseEntity<*> = run {
        cartService.updatePrices(productDto)
        ResponseEntity("Price updated", HttpStatus.OK)
    }


    private inline fun checkHeaders(userName: String, block: () -> Any): ResponseEntity<*> {
        return if (getUserIdFromRequest(header, secret) == userName) {
            block().toResponseEntity()
        } else {
            "This cart doesn't belong to you".toResponseEntity(HttpStatus.FORBIDDEN)
        }
    }

}
