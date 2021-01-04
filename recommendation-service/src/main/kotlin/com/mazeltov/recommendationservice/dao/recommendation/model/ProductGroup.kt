package com.mazeltov.recommendationservice.dao.recommendation.model

import javax.persistence.*

@Entity
@Table(name = "product_group")
data class ProductGroup(@Id
                        @GeneratedValue(strategy = GenerationType.IDENTITY)
                        var id: Long = -1,
                        @Column(unique = true)
                        var productGroupId: Long = -1)
