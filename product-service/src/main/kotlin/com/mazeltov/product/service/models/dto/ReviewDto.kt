package com.mazeltov.product.service.models.dto


data class ReviewDto(
        var id: Long = -1,
        var productId: Long,
        var userId: Long,
        var date: String,
        var content: String
)
