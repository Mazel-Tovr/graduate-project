package com.mazeltov.productservice.feignclients

import com.mazeltov.common.*
import com.mazeltov.common.dto.*
import com.mazeltov.common.spring.*
import com.mazeltov.productservice.endpoints.*
import org.slf4j.*
import org.springframework.cloud.openfeign.*
import org.springframework.http.*
import org.springframework.stereotype.*
import org.springframework.web.bind.annotation.*

@FeignClient(
        name = "\${cart-service.name}",
        fallback = CartServiceFeignClient.CartServiceDefaultRealisation::class
)
interface CartServiceFeignClient {

    @PatchMapping("\${api.cart-service.carts.rout}")
    fun updatePricesInAllCarts(@RequestBody productDto: ProductDto): ResponseEntity<*>


    @Component
    class CartServiceDefaultRealisation : CartServiceFeignClient {
        @InjectLogger(CartServiceDefaultRealisation::class)
        private lateinit var logger: Logger

        override fun updatePricesInAllCarts(productDto: ProductDto): ResponseEntity<*> {
            logger.warn("Cart service is not available")
            return "Cart service is not available".toResponseEntity(HttpStatus.GATEWAY_TIMEOUT)
        }

    }
}
