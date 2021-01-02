package com.mazeltov.common.dto

import java.util.*

data class ProductDto(
        var id: Long = 0,
        var name: String,
        var created: Date,
        var description: String,
        var productGroupId: Long,
        var isAvailable: Boolean
)

data class ReviewDto(
        var id: Long = -1,
        var productId: Long,
        var userId: Long,
        var date: Date,
        var content: String
)
