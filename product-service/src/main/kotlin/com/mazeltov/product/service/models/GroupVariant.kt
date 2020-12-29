package com.mazeltov.product.service.models

import javax.persistence.*

//TODO try make id val
@Entity
@Table(name = "group_variant")
data class GroupVariant(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = -1,
        var name: String = "",
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "product_group_id")
        var group: ProductGroup
)
