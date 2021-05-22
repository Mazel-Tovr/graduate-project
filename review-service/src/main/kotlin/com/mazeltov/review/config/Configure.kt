package com.mazeltov.review.config

import com.mazeltov.common.spring.*
import org.springframework.beans.factory.config.*
import org.springframework.boot.context.properties.*
import org.springframework.context.annotation.*


@Configuration
@ConfigurationProperties
class Configure {
    @Bean
    fun injectLoggerAnnotationBeanPostProcessor(): BeanPostProcessor {
        return InjectLoggerAnnotationBeanPostProcessor()
    }

}


