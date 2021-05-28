package com.mazel.tov

import io.restassured.*
import org.junit.jupiter.api.*


class ApiTest {

    private val gatewayUri = "http://localhost:8762"

    @Test
    fun login() {
        RestAssured.given().formParam("username", "user").formParam("password", "user")
            .post("$gatewayUri/api/authorization/login").then().statusCode(200)
    }

    @Test
    fun getAllProducts() {
        RestAssured.given().get("$gatewayUri/api/prod/1/products").then().statusCode(200)
    }

    @Test
    fun getCurrentProduct() {
        RestAssured.given().get("$gatewayUri/api/prod/4/products/1").then().statusCode(200)
    }

    @Test
    fun getAllProductReviews() {
        RestAssured.given().get("$gatewayUri/api/prod/rew/1/reviews").then().statusCode(200)
    }
}
