package com.mazeltov.productservice.config

import com.mazeltov.common.*
import com.mazeltov.common.spring.*
import feign.*
import org.springframework.beans.factory.annotation.*
import org.springframework.beans.factory.config.*
import org.springframework.boot.context.properties.*
import org.springframework.context.annotation.*
import org.springframework.stereotype.*
import org.springframework.web.servlet.*

/**
 * Класс конфигурациия бинов приложения
 */
@Configuration
@EnableConfigurationProperties
@PropertySources()
class Configure {

    @Bean
    fun injectLoggerAnnotationBeanPostProcessor(): BeanPostProcessor {
        return InjectLoggerAnnotationBeanPostProcessor()
    }

    @Bean
    fun basicAuthRequestInterceptor(): RequestInterceptor {
        return ProxyInterceptor()
    }

    @Bean
    fun dispatcherServlet(): DispatcherServlet {
        return DispatcherServlet().also { it.setThreadContextInheritable(true) }
    }
}

@Component
class ProxyInterceptor : RequestInterceptor {

    @Value("\${jwt.header}")
    private lateinit var header: String

    override fun apply(requestTemplate: RequestTemplate) {
        val request = getActualRequest()
        val token = request.getHeader(header) ?: ""
        requestTemplate.header(header, token)
    }
}


