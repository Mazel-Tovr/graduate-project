package com.mazeltov.product.service.models

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "product")
data class Product(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id:Long,
        var name:String = "",
        @Temporal(TemporalType.TIMESTAMP)
        var created:Date,
        var description:String="",
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "group_id")
        var group: ProductGroup
)
