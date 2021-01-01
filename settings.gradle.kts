pluginManagement {
    plugins {
        kotlin("jvm") version "1.3.72"
    }

    repositories {
        maven { url = uri("https://repo.spring.io/milestone") }
        maven { url = uri("https://repo.spring.io/snapshot") }
        gradlePluginPortal()
    }
}

rootProject.name = "graduate-project"
include("review-service")
include("recommendation-service")
include("product-service")
include("order-service")
include("gateway")
include("eureka-server")
include("cart-service")
include("authorization-service")
include("common")
