package com.mazeltov.review.models

import javax.persistence.*

@Entity
@Table(name = "product")
data class Product(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
       // @Column(name = "product_id")
        val id: Long = -1,
        @Column(unique = true)
        val productId: Long,
        @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        var reviews:List<Review> = emptyList()
)
