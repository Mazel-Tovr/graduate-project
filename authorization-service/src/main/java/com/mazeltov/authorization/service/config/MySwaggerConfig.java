package com.mazeltov.authorization.service.config;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import springfox.documentation.builders.*;
import springfox.documentation.spi.*;
import springfox.documentation.spring.web.plugins.*;
import springfox.documentation.swagger2.annotations.*;

import java.util.function.*;

import static springfox.documentation.builders.PathSelectors.*;

@Configuration
@EnableSwagger2
public class MySwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(paths())
                .build();
    }

    @Value("${api.authorization-service.rout}")
    private String authorizationService;

    private Predicate<String> paths() {
        return regex("/.*")
                .or(regex(authorizationService + ".*"));

    }
}
