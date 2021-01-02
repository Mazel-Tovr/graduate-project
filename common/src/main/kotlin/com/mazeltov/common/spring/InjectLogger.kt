package com.mazeltov.common.spring

import kotlin.reflect.*

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class InjectLogger(val clazz:KClass<*> = Any::class)
