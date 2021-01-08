package com.mazeltov.common.security

import io.jsonwebtoken.*
import org.springframework.security.core.*
import javax.servlet.http.*


fun String.verifyJWT(secret: String): Boolean {
    return Jwts.parser().setSigningKey(secret).isSigned(this)
}

fun String.getRoleFromToken(secret: String): UserRole? {
    return Jwts.parser().setSigningKey(secret).parseClaimsJws(this).body
            .get("authorities", ArrayList<LinkedHashMap<String, String>>()::class.java).filter {
                it.values.first().startsWith("ROLE_")
            }.map { it.values.first().substring(5, it.values.first().length) }.firstOrNull()?.let { UserRole.valueOf(it) }
}

fun getToken(request: HttpServletRequest, header: String): String? {
    val authHeader = request.getHeader(header)
    if (authHeader != null && authHeader.startsWith("Bearer ")) {
        return authHeader.substring(7)
    }
    return null
}
