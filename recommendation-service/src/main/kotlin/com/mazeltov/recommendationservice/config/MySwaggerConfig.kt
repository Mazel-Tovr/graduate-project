package com.mazeltov.recommendationservice.config

import com.mazeltov.common.swagger.*
import org.springframework.beans.factory.annotation.*
import org.springframework.context.annotation.*
import springfox.documentation.builders.*
import springfox.documentation.builders.PathSelectors.*
import springfox.documentation.service.*
import springfox.documentation.spi.*
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

    @Value("\${api.recommendation-service.rout}")
    private lateinit var recommendationService: String


    private fun paths(): Predicate<String> {
        return regex("/.*")
            .or(regex("$recommendationService/.*"))
    }
}
