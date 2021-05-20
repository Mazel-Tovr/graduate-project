package com.mazeltov.common.swagger

import springfox.documentation.service.*
import springfox.documentation.spi.service.contexts.*

fun securityContext(): List<SecurityContext> {
    return listOf(SecurityContext.builder().securityReferences(defaultAuth()).build())
}

private fun defaultAuth(): List<SecurityReference> {
    val authorizationScope = AuthorizationScope("global", "accessEverything")
    val authorizationScopes = arrayOfNulls<AuthorizationScope>(1)
    authorizationScopes[0] = authorizationScope
    return listOf(SecurityReference("JWT", authorizationScopes))
}
