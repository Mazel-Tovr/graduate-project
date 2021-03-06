package com.mazeltov.review.models

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "review")
data class Review(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = -1,
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "product")
        var product: Product = Product(),
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "user")
        var user: User = User(),
        var date: Date = Date(),
        var content: String = ""
)
