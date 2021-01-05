package com.mazeltov.cart.service

import org.springframework.boot.*
import org.springframework.boot.autoconfigure.*
import org.springframework.cloud.client.discovery.*

@SpringBootApplication
@EnableDiscoveryClient
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}
