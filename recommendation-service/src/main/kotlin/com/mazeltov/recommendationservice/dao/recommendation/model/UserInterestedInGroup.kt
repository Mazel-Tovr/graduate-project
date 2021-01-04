package com.mazeltov.recommendationservice.dao.recommendation.model

import javax.persistence.*

@Entity
@Table(name = "user_interested_in_group")
data class UserInterestedInGroup(@Id
                                 @GeneratedValue(strategy = GenerationType.IDENTITY)
                                 var id: Long = -1,
                                 @ManyToOne(fetch = FetchType.EAGER)
                                 @JoinColumn(name = "user")
                                 var user: User = User(),
                                 @ManyToOne(fetch = FetchType.EAGER)
                                 @JoinColumn(name = "product_group")
                                 var productGroup: ProductGroup = ProductGroup(),
                                 var visitTime: Long = 0
)
