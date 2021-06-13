package com.mazeltov.cart.service.config

import com.mazeltov.common.spring.*
import org.springframework.beans.factory.config.*
import org.springframework.context.annotation.*

/**
 * Класс конфигурациия бинов приложения
 */
@Configuration
class Configure {

    @Bean
    fun injectLoggerAnnotationBeanPostProcessor(): BeanPostProcessor {
        return InjectLoggerAnnotationBeanPostProcessor()
    }

}
