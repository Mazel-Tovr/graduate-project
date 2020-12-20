package com.mazeltov.recommendationservice.dao.product.model

import javax.persistence.*

@Entity
@Table(name = "product_group")
data class ProductGroup(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id:Long,
        var name:String = "",
        @OneToMany(mappedBy = "group", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        var groupVariants: List<GroupVariant>
)
