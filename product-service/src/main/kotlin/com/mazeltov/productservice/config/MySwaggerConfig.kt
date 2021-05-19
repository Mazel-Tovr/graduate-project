package com.mazeltov.productservice.config

import com.google.common.collect.*
import org.springframework.beans.factory.annotation.*
import org.springframework.context.annotation.*
import org.springframework.http.*
import springfox.documentation.builders.*
import springfox.documentation.builders.PathSelectors.*
import springfox.documentation.service.*
import springfox.documentation.spi.*
import springfox.documentation.spi.service.contexts.*
import springfox.documentation.spring.web.plugins.*
import springfox.documentation.swagger2.annotations.*
import java.util.function.*


@Configuration
@EnableSwagger2
class MySwaggerConfig {
    @Bean
    fun api(): Docket {
        val schemeList: MutableList<SecurityScheme> = ArrayList<SecurityScheme>()
        schemeList.add(ApiKey("JWT", "Authorization", "header"))
        return Docket(DocumentationType.SWAGGER_2)
            .securityContexts(securityContext())
            .securitySchemes(schemeList)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(paths())
            .build()
    }

    @Value("\${api.products.rout}")
    private lateinit var product: String

    @Value("\${api.groups.rout}")
    private lateinit var group: String

    @Value("\${api.group-variants.rout}")
    private lateinit var groupVariant: String

    private fun securityContext(): List<SecurityContext> {
        return listOf(SecurityContext.builder().securityReferences(defaultAuth()).build())
    }

    private fun defaultAuth(): List<SecurityReference?> {
        val authorizationScope = AuthorizationScope("global", "accessEverything")
        val authorizationScopes = arrayOfNulls<AuthorizationScope>(1)
        authorizationScopes[0] = authorizationScope
        return listOf(SecurityReference("JWT", authorizationScopes))
    }

    private fun paths(): Predicate<String> {
        return regex("/.*")
            .or(regex("$product.*"))
            .or(regex("$group.*"))
            .or(regex("$groupVariant.*"))
    }
}
