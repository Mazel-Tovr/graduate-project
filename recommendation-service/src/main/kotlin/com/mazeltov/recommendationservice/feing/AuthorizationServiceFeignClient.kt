package com.mazeltov.recommendationservice.feing

import org.springframework.cloud.openfeign.*
import org.springframework.web.bind.annotation.*

@FeignClient(
        name = "\${authorization-service.name}",
        fallback = AuthorizationServiceFeignClient.AuthorizationServiceFeignClientRealisation::class
)
interface AuthorizationServiceFeignClient {

    @GetMapping("\${api.review-service.current}")
    fun getUserById(@PathVariable(value = "id") userId: Long): Any?

    class AuthorizationServiceFeignClientRealisation : AuthorizationServiceFeignClient {
        override fun getUserById(userId: Long): Any? {
            return null
        }
    }
}
