package com.mazeltov.recommendationservice.dao.recommendation.model

import javax.persistence.*

data class UserInterestedInGroup(@Id
                                 @GeneratedValue(strategy = GenerationType.IDENTITY)
                                 var id: Long = -1,
                                 @ManyToOne(fetch = FetchType.EAGER)
                                 @JoinColumn(name = "user_id")
                                 var user: User,
                                 @ManyToOne(fetch = FetchType.EAGER)
                                 @JoinColumn(name = "user_id")
                                 var group: ProductGroup,
                                 @Column(name = "visit_time")
                                 var visitTime: Long = 0
)
