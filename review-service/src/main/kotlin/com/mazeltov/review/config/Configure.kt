package com.mazeltov.review.config

import com.mazeltov.common.spring.*
import org.springframework.beans.factory.config.*
import org.springframework.boot.context.properties.*
import org.springframework.boot.env.*
import org.springframework.context.annotation.*
import org.springframework.core.io.support.*
import org.springframework.stereotype.*


@Configuration
@PropertySource("classpath:routs.yml")
@ConfigurationProperties
class Configure {
    @Bean
    fun injectLoggerAnnotationBeanPostProcessor(): BeanPostProcessor {
        return InjectLoggerAnnotationBeanPostProcessor()
    }

}


