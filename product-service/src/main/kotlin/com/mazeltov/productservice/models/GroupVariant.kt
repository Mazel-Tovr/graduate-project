package com.mazeltov.productservice.models

import javax.persistence.*

/**
 * Модель которая хранится в базе данных
 */
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
