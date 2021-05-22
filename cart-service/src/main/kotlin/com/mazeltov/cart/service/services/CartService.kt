package com.mazeltov.cart.service.services

import com.mazeltov.cart.service.dao.model.*
import com.mazeltov.cart.service.dao.repository.*
import com.mazeltov.common.dto.*
import com.mazeltov.common.exception.*
import org.springframework.beans.factory.annotation.*
import org.springframework.stereotype.*

@Service
class CartService {

    @Autowired
    private lateinit var cartRepository: CartRepository

    @Autowired
    private lateinit var cartItemRepository: CartItemRepository

    fun getOrCreateCarts(userName: String): List<CartDto> = run {
        cartRepository.findByUserName(userName).takeIf {
            it.isNotEmpty()
        } ?: listOf(cartRepository.save(Cart(userName = userName)))
    }.toCartDto()

    fun getCart(cartId: Long): CartDto = cartRepository.findById(cartId).orElseThrow {
        throw CartDoesNotExistException("Cart with such id $cartId doesn't exist")
    }.toCartDto()

    fun updatePrices(product: ProductDto) {
        cartItemRepository.updatePrices(product.id, product.productGroupId, product.price)
    }

    @Throws(ProductAlReadyInCartException::class)
    fun addProductIntoCart(
        cartId: Long,
        product: ProductDto,
        amount: Int
    ): CartDto = cartRepository.findById(cartId).orElseThrow {
        CartDoesNotExistException("Cart doesn't exist create it first")
    }.let { cart ->
        if (cart.cart.any { cartItem ->
                cartItem.productId == product.id && cartItem.productGroupId == product.productGroupId
            }) throw ProductAlReadyInCartException("Product already in cart")
        cartRepository.save(cart.copy(cart = cart.cart + CartItem(
            productId = product.id,
            productGroupId = product.productGroupId,
            amount = amount,
            price = product.price
        )))
    }.toCartDto()

    fun changeProductAmount(cartId: Long, cartItem: CartItemAmountDto): CartDto =
        cartRepository.findById(cartId).orElseThrow {
            CartDoesNotExistException("Cart doesn't exist create it first")
        }.let { cart ->
            val itemToReplace = cart.cart.find { cartItems ->
                cartItems.productId == cartItem.productId && cartItems.productGroupId == cartItem.productGroupId
            } ?: throw ProductNotInCartException("Product not in cart")
            if (cartItem.newAmount <= 0) {
                cartRepository.save(cart.copy(cart = cart.cart - itemToReplace))
            } else {
                cart.copy(cart = (cart.cart - itemToReplace) + cartItemRepository.save(itemToReplace.copy(amount = cartItem.newAmount)))
            }
        }.toCartDto()

    private fun Cart.toCartDto() =
        CartDto(id, cart.map { it.toCartItemDto() }.toSet(), cart.sumBy { it.price * it.amount })

    private fun List<Cart>.toCartDto() = map { it.toCartDto() }

    private fun CartItem.toCartItemDto() = CartItemDto(productId, productGroupId, amount, price)

}
