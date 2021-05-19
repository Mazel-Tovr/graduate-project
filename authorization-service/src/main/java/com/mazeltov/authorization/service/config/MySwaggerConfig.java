package com.mazeltov.authorization.service.config;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.*;
import springfox.documentation.spi.service.contexts.*;
import springfox.documentation.spring.web.plugins.*;
import springfox.documentation.swagger2.annotations.*;

import java.util.*;
import java.util.function.*;

import static springfox.documentation.builders.PathSelectors.*;

@Configuration
@EnableSwagger2
public class MySwaggerConfig {
    @Bean
    public Docket api() {
        List<SecurityScheme> schemeList = new ArrayList<SecurityScheme>();
        schemeList.add(new ApiKey("JWT", "Authorization", "header"));
        return new Docket(DocumentationType.SWAGGER_2)
                .securityContexts(securityContext())
                .securitySchemes(schemeList)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(paths())
                .build();
    }

    @Value("${api.authorization-service.rout}")
    private String authorizationService;

    private List<SecurityContext> securityContext() {
        return Collections.singletonList(SecurityContext.builder().securityReferences(defaultAuth()).build());
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Collections.singletonList(new SecurityReference("JWT", authorizationScopes));
    }

    private Predicate<String> paths() {
        return regex("/.*")
                .or(regex(authorizationService + ".*"));

    }
}
