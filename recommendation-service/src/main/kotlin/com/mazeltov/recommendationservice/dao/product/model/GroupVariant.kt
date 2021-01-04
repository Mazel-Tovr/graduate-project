package com.mazeltov.recommendationservice.dao.product.model

import javax.persistence.*

//TODO try make id val
@Entity
@Table(name = "group_variant")
data class GroupVariant(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "group_variant_id")
        var id: Long = 0,
        var name: String = ""//,
//        @OneToMany(mappedBy = "groupVariants", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
//        var productGroup: List<ProductGroup> = emptyList()
)
