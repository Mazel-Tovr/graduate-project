package com.mazeltov.recommendationservice.dao.product.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "product")
data class Product(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "product_id")
        var id: Long = 0,
        var name: String = "",
        @Temporal(TemporalType.TIMESTAMP)
        var created: Date = Date(),
        var description: String = "",
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "product_group")
        var productGroup: ProductGroup = ProductGroup(),
        var amount: Int = 0,
        var price: Int = 0
)
