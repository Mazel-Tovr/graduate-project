package com.mazeltov.recommendationservice.dao.recommendation.model

import com.mazeltov.recommendationservice.dao.product.model.*
import javax.persistence.*

@Entity
@Table(name = "product")
data class User(@Id
                @GeneratedValue(strategy = GenerationType.IDENTITY)
                var id: Long = -1,
                @Column(name = "user_id")
                var userId: Long
)
//TODO Mb add list of int to user wn
