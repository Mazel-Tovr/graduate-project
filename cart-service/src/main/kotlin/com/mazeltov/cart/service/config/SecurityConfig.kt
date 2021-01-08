package com.mazeltov.cart.service.config

import com.mazeltov.common.security.*
import org.springframework.beans.factory.annotation.*
import org.springframework.context.annotation.*
import org.springframework.http.*
import org.springframework.security.config.annotation.web.builders.*
import org.springframework.security.config.annotation.web.configuration.*
import org.springframework.security.web.authentication.www.*
import com.mazeltov.common.security.UserRole.*

@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    @Value("\${jwt.header}")
    private lateinit var header: String

    @Bean
    fun tokenVerificationFilter(): TokenVerificationFilter {
        return TokenVerificationFilter(header, secret)
    }

    override fun configure(http: HttpSecurity) {
        http
                .addFilterBefore(tokenVerificationFilter(), BasicAuthenticationFilter::class.java)
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
    }
}
