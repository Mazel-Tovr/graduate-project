package com.mazeltov.cart.service.config

import com.mazeltov.common.security.*
import com.mazeltov.common.security.UserRole.*
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


    @Value("\${api.rout}")
    private lateinit var api: String

    @Value("\${api.cart-service.rout}")
    private lateinit var cartService: String


    @Bean
    fun tokenVerificationFilter(): TokenVerificationFilter {
        return TokenVerificationFilter(header, secret)
    }

    override fun configure(http: HttpSecurity) {
        http
            .addFilterBefore(tokenVerificationFilter(), BasicAuthenticationFilter::class.java)
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "$api/**").hasAnyRole(USER.name, ADMIN.name)
            .antMatchers(HttpMethod.PUT, cartService).hasAnyRole(ADMIN.name)
            .antMatchers(HttpMethod.POST, "$cartService/**").hasAnyRole(USER.name, ADMIN.name)
            .antMatchers(HttpMethod.PATCH, "$cartService/**").hasAnyRole(USER.name, ADMIN.name)
            .antMatchers("/").permitAll()
            .anyRequest().authenticated()
            .and().csrf().disable()
    }
}
