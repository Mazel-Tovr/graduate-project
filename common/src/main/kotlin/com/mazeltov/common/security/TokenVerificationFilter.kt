package com.mazeltov.common.security

import org.springframework.security.authentication.*
import org.springframework.security.core.*
import org.springframework.security.core.context.*
import org.springframework.web.filter.*
import javax.servlet.*
import javax.servlet.http.*

class TokenVerificationFilter(private val authHeader: String, private val authSecret: String) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        getToken(request, authHeader)?.let { token ->
            token.takeIf { it.verifyJWT(authSecret) }?.run {
                getRoleFromToken(authSecret)?.let { role ->
                    SecurityContextHolder.getContext().authentication =
                        Authenticated(role.grantedAuthorities(), token)
                }
            }
        } ?: setNotAuthenticate()


        filterChain.doFilter(request, response)
    }

    private fun setNotAuthenticate() {
        SecurityContextHolder.getContext()
            .authentication = AnonAuthentication()
    }

}

private class Authenticated(authorities: Collection<GrantedAuthority>, val token: String) :
    AbstractAuthenticationToken(authorities) {
    override fun isAuthenticated(): Boolean {
        return true
    }

    override fun getCredentials(): Any {
        return token
    }

    override fun getPrincipal(): Any? {
        return null
    }

}


private class AnonAuthentication : AbstractAuthenticationToken(null) {
    override fun getCredentials(): Any? {
        return null
    }

    override fun getPrincipal(): Any? {
        return null
    }

    override fun isAuthenticated(): Boolean {
        return true
    }

    override fun hashCode(): Int {
        return 7
    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj) {
            return true
        }
        if (obj == null) {
            return false
        }
        return javaClass == obj.javaClass
    }
}
