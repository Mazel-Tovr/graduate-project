package com.mazeltov.review.models

import javax.persistence.*

@Entity
@Table(name = "user")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
       // @Column(name = "user_id")
        val id: Long = -1,
        @Column(unique = true)
        val userId: Long,
        @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        var reviews: List<Review> = emptyList()
)
