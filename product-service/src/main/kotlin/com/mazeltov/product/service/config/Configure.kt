package com.mazeltov.product.service.config

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.*

@Configuration
@EnableConfigurationProperties
@PropertySources(value = [
    PropertySource("classpath:routs.yml")]
)
class Configure {

}
