package com.mazeltov.common.swagger

import springfox.documentation.service.*
import springfox.documentation.spi.service.contexts.*

/**
 * Утилитный метод для сваггера
 */
fun securityContext(): List<SecurityContext> {
    return listOf(SecurityContext.builder().securityReferences(defaultAuth()).build())
}

/**
 * Утилитный метод для сваггера
 */
private fun defaultAuth(): List<SecurityReference> {
    val authorizationScope = AuthorizationScope("global", "accessEverything")
    val authorizationScopes = arrayOfNulls<AuthorizationScope>(1)
    authorizationScopes[0] = authorizationScope
    return listOf(SecurityReference("JWT", authorizationScopes))
}
