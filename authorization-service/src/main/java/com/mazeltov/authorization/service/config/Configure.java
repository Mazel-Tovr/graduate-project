package com.mazeltov.authorization.service.config;

import com.mazeltov.common.spring.*;
import org.springframework.context.annotation.*;

@Configuration
public class Configure {

    @Bean
    public InjectLoggerAnnotationBeanPostProcessor injectLoggerAnnotationBeanPostProcessor(){
        return new InjectLoggerAnnotationBeanPostProcessor();
    }
}
