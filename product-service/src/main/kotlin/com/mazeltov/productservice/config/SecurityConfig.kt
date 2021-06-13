package com.mazeltov.productservice.config

import com.mazeltov.common.security.*
import org.springframework.beans.factory.annotation.*
import org.springframework.context.annotation.*
import org.springframework.http.*
import org.springframework.security.config.annotation.web.builders.*
import org.springframework.security.config.annotation.web.configuration.*
import org.springframework.security.web.authentication.www.*
import com.mazeltov.common.security.UserRole.*

/**
 * Класс с описанием настройки безопасности
 */
@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Value("\${api.products.rout}")
    private lateinit var products: String

    @Value("\${api.review-services.rout}")
    private lateinit var reviewService: String

    @Value("\${api.group-variants.rout}")
    private lateinit var groupVariant: String

    @Value("\${api.groups.rout}")
    private lateinit var group: String


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
                .antMatchers(HttpMethod.POST, "$products/**").hasAnyRole(USER.name, ADMIN.name)
                .antMatchers(HttpMethod.DELETE, "$products/**").hasAnyRole(USER.name, ADMIN.name)
                .antMatchers(HttpMethod.PATCH, "$products/**").hasAnyRole(USER.name, ADMIN.name)
                .antMatchers(HttpMethod.POST, "$reviewService/**").hasAnyRole(USER.name, ADMIN.name)
                .antMatchers(HttpMethod.PATCH, "$reviewService/**").hasAnyRole(USER.name, ADMIN.name)
                .antMatchers(HttpMethod.DELETE, "$reviewService/**").hasAnyRole(USER.name, ADMIN.name)
                .antMatchers(HttpMethod.POST, "$groupVariant/**").hasAnyRole(USER.name, ADMIN.name)
                .antMatchers(HttpMethod.PATCH, "$groupVariant/**").hasAnyRole(USER.name, ADMIN.name)
                .antMatchers(HttpMethod.POST, "$group/**").hasAnyRole(USER.name, ADMIN.name)
                .antMatchers(HttpMethod.PATCH, "$group/**").hasAnyRole(USER.name, ADMIN.name)
                .antMatchers(HttpMethod.DELETE, "$group/**").hasAnyRole(USER.name, ADMIN.name)
                .anyRequest().authenticated()
                .and().csrf().disable()
    }
}
