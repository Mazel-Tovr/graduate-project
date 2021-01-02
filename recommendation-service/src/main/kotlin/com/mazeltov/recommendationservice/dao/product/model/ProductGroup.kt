package com.mazeltov.recommendationservice.dao.product.model

import javax.persistence.*

@Entity
@Table(name = "product_group")
data class ProductGroup(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "product_group_id")
        var id: Long = 0,
        var name: String = "",
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "group_variant")
        var groupVariants: GroupVariant = GroupVariant()
)
