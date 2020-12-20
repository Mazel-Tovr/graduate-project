package com.mazeltov.recommendationservice.dao.recommendation.model

import javax.persistence.*

data class UserInterestedInGroup(@Id
                                 @GeneratedValue(strategy = GenerationType.IDENTITY)
                                 var id: Long,
                                //TODO Write Link
                                 var user: User,
                                //TODO Write Link
                                 var group: ProductGroup,
                                 @Column(name = "visit_time")
                                 var visitTime: Long = 0
)
