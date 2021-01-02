package com.mazeltov.product.service.config

import com.mazeltov.common.spring.*
import org.springframework.beans.factory.config.*
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.*

@Configuration
@EnableConfigurationProperties
@PropertySources(value = [
    PropertySource("classpath:routs.yml")]
)
class Configure {


    @Bean
    fun injectLoggerAnnotationBeanPostProcessor(): BeanPostProcessor {
        return InjectLoggerAnnotationBeanPostProcessor()
    }

}
