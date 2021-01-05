package com.mazeltov.review

import org.springframework.boot.*
import org.springframework.boot.autoconfigure.*
import org.springframework.cloud.client.discovery.*

@SpringBootApplication
@EnableDiscoveryClient
class ReviewServiceApplication


fun main(args: Array<String>) {
	runApplication<ReviewServiceApplication>(*args)
}
