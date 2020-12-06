package com.mazeltov.product.service.config.sping

import kotlin.reflect.*

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class InjectLogger(val clazz:KClass<*>)
