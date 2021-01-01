package com.mazeltov.review.service.models

import javax.persistence.*

data class Product(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "product_id")
        val id: Long = -1,
        val productId: Long,
        @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        var reviews:List<Review>
)
