package com.mazeltov.review.service.models

import javax.persistence.*

@Entity
@Table(name = "review")
data class Review(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = -1,
        var productId: Long,
        var userId: Long,
        var date: String,
        var content: String
)
