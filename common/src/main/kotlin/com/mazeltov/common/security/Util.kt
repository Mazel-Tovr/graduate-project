package com.mazeltov.common.security

import io.jsonwebtoken.*
import org.springframework.security.core.*
import javax.servlet.http.*


fun String.verifyJWT(secret: String): Boolean = Jwts.parser().setSigningKey(secret).isSigned(this)

fun String.getRoleFromToken(
    secret: String
): UserRole? = runCatching {
    Jwts.parser().setSigningKey(secret).parseClaimsJws(this).body
        .get("authorities", ArrayList<LinkedHashMap<String, String>>()::class.java).filter {
            it.values.first().startsWith("ROLE_")
        }.map { it.values.first().substring(5, it.values.first().length) }.firstOrNull()?.let { UserRole.valueOf(it) }
}.getOrNull()

fun String.getUserNameFromToken(
    secret: String
): String? = Jwts.parser().setSigningKey(secret).parseClaimsJws(this).body.subject

fun getToken(
    request: HttpServletRequest,
    header: String
): String? = request.getHeader(header)?.takeIf { it.startsWith("Bearer ") }?.substring(7)

