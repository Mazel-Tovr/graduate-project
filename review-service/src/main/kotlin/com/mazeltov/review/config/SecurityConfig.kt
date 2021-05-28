package com.mazeltov.review.config

import com.mazeltov.common.security.*
import org.springframework.beans.factory.annotation.*
import org.springframework.context.annotation.*
import org.springframework.http.*
import org.springframework.security.config.annotation.web.builders.*
import org.springframework.security.config.annotation.web.configuration.*
import org.springframework.security.web.authentication.www.*

@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    @Value("\${jwt.header}")
    private lateinit var header: String

    @Value("\${api.review-service.current.rout}")
    private lateinit var currentRevApi: String

    @Value("\${api.review-service.current.rout}")
    private lateinit var revApi: String

    @Bean
    fun tokenVerificationFilter(): TokenVerificationFilter {
        return TokenVerificationFilter(header, secret)
    }

    override fun configure(http: HttpSecurity) {
        http
            .addFilterBefore(tokenVerificationFilter(), BasicAuthenticationFilter::class.java)
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, revApi).hasAnyRole(UserRole.ADMIN.name, UserRole.USER.name)
            .antMatchers(HttpMethod.PUT, currentRevApi).hasAnyRole(UserRole.ADMIN.name, UserRole.USER.name)
            .antMatchers(HttpMethod.DELETE, currentRevApi).hasAnyRole(UserRole.ADMIN.name, UserRole.USER.name)
            .anyRequest().authenticated()
            .and().csrf().disable()
    }
}
