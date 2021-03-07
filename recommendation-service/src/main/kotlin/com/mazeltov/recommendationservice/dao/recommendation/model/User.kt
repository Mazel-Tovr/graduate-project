package com.mazeltov.recommendationservice.dao.recommendation.model

import javax.persistence.*

@Entity
@Table(name = "user")
data class User(@Id
                @GeneratedValue(strategy = GenerationType.IDENTITY)
                var id: Long = -1,
                @Column(unique = true)
                var userName: String = ""
)
//TODO Mb add list of int to user wn
