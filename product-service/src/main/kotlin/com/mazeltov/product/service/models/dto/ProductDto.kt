package com.mazeltov.product.service.models.dto

import java.util.*

data class ProductDto(
        var id: Long = 0,
        var name: String,
        var created: Date,
        var description: String,
        var productGroupId: Long,
        var isAvailable: Boolean
)
