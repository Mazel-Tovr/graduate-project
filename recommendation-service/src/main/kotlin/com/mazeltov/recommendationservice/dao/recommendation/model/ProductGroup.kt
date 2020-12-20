package com.mazeltov.recommendationservice.dao.recommendation.model

import javax.persistence.*

data class ProductGroup(@Id
                        @GeneratedValue(strategy = GenerationType.IDENTITY)
                        var id: Long,
                        @Column(name = "product_group_id")
                        var productGroupId: Long)
