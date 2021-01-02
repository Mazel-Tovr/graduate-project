package com.mazeltov.review.config

import com.mazeltov.common.spring.*
import org.springframework.beans.factory.config.*
import org.springframework.context.annotation.*

@Configuration
@PropertySources(value = [
    PropertySource("classpath:routs.yml")]
)
class Configure {
    @Bean
    fun injectLoggerAnnotationBeanPostProcessor(): BeanPostProcessor {
        return InjectLoggerAnnotationBeanPostProcessor()
    }
}
