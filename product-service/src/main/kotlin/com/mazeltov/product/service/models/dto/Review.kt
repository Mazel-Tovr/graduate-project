package com.mazeltov.product.service.models.dto


data class Review(
        var id: Long,
        var productId: Long,
        var userId: Long,
        var date: String,
        var content: String
)
