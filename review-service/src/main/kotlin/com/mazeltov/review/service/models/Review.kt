package com.mazeltov.review.service.models

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "review")
data class Review(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "review_id")
        val id: Long = -1,
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "product")
        var product: Product,
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "user")
        var user: User,
        var date: Date = Date(),
        var content: String
)
