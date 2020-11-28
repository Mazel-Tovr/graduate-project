package com.mazeltov.product.service.dao

import javax.persistence.*

data class GroupVariant(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id:Long,
        var name:String = "",
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "group_id")
        var group:ProductGroup
)
