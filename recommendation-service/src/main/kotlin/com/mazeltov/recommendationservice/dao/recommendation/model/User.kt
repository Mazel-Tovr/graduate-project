package com.mazeltov.recommendationservice.dao.recommendation.model

import com.mazeltov.recommendationservice.dao.product.model.*
import javax.persistence.*

@Entity
@Table(name = "user")
data class User(@Id
                @GeneratedValue(strategy = GenerationType.IDENTITY)
                var id: Long = -1,
                @Column(unique = true)
                var userId: Long = -1
)
//TODO Mb add list of int to user wn
