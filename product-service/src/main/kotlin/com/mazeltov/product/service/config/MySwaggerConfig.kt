package com.mazeltov.product.service.config

import org.springframework.beans.factory.annotation.*
import org.springframework.context.annotation.*
import springfox.documentation.builders.*
import springfox.documentation.builders.PathSelectors.*
import springfox.documentation.spi.*
import springfox.documentation.spring.web.plugins.*
import springfox.documentation.swagger2.annotations.*
import java.util.function.*

@Configuration
@EnableSwagger2
class MySwaggerConfig {
    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(paths())
                .build()
    }

    @Value("\${api.products.rout}")
    private lateinit var product: String

    @Value("\${api.products.rout}")
    private lateinit var group: String

    @Value("\${api.products.rout}")
    private lateinit var groupVariant: String


    private fun paths(): Predicate<String> {
        return regex("/.*")
                .or(regex("$product.*"))
                .or(regex("$group.*"))
                .or(regex("$groupVariant.*"))
    }
}
