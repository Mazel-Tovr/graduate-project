package com.mazeltov.product.service.config

import org.springframework.context.annotation.*

@Configuration
@PropertySources(value = [
    PropertySource("classpath:routs.yml")]
)
class Configure {

}
