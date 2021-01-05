package com.mazeltov.cart.service.services

import com.mazeltov.cart.service.dao.model.*
import com.mazeltov.cart.service.dao.repository.*
import com.mazeltov.common.dto.*
import com.mazeltov.common.exception.*
import org.springframework.beans.factory.annotation.*
import org.springframework.stereotype.*
import org.springframework.transaction.annotation.*

@Service
class CartService {

    @Autowired
    private lateinit var cartRepository: CartRepository

    @Autowired
    private lateinit var cartItemRepository: CartItemRepository

    @Autowired
    private lateinit var itemToCartRepository: ItemToCartRepository

    fun getOrCart(userId: Long): Cart {
        return cartRepository.findByUserId(userId) ?: cartRepository.save(Cart(userId = userId))
    }

    //TODO Bug return old cart value
    fun addProductIntoCart(cartId: Long, product: ProductDto, amount: Int): Cart {
        return cartRepository.findById(cartId).orElseThrow { CartDoesNotExistException("Cart doesn't exist create it first") }
                .let { cart ->
                    if (cart.cart.any { cartItem ->
                                cartItem.productId == product.id && cartItem.productGroupId == product.productGroupId
                            })
                        throw ProductAlReadyInCartException("Product already in cart")
                    cartItemRepository.save(CartItem(
                            productId = product.id,
                            productGroupId = product.productGroupId,
                            amount = amount
                    )
                    ).let { cartItem ->
                        itemToCartRepository.save(ItemToCart(cart = cart, item = cartItem))
                        cart.cart.plus(cartItem) //doesn't work
                    }
                    cart //doesn't work
                }

    }

    //TODO Bug return old cart value
    fun changeProductAmount(cartId: Long, cartItem: CartItem, newAmount: Int): Cart {
        itemToCartRepository.findByCartIdAndItemId(cartId, cartItem.id)?.let { itemToCart ->
            if (newAmount <= 0) {
                cartRepository.findById(cartId).ifPresent {
                    it.cart.minus(itemToCart.item)
                    cartRepository.save(it)
                }
                itemToCartRepository.delete(itemToCart)
                cartItemRepository.delete(itemToCart.item)//doesn't work
            } else cartItemRepository.save(itemToCart.item.copy(amount = newAmount))
        } ?: throw ItemNotInTheCartException("Item doesn't exist create it first")

        return cartRepository.findById(cartId).orElseThrow { CartDoesNotExistException("Cart doesn't exist create it first") }
    }

}
