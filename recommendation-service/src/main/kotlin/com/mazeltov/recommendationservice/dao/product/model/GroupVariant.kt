package com.mazeltov.recommendationservice.dao.product.model

import javax.persistence.*

//TODO try make id val
data class GroupVariant(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id:Long,
        var name:String = "",
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "group_id")
        var group: ProductGroup
)
