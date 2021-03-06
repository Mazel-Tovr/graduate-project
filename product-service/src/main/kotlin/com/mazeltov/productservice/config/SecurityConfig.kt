package com.mazeltov.productservice.config

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
                .antMatchers(HttpMethod.GET, products).permitAll()//.hasAnyRole(USER.name,ADMIN.name)
                .antMatchers(HttpMethod.POST, reviewService.plus("/**")).hasAnyRole(USER.name, ADMIN.name)
                .antMatchers(HttpMethod.PATCH, reviewService.plus("/**")).hasAnyRole(USER.name, ADMIN.name)
                .antMatchers(HttpMethod.DELETE, reviewService.plus("/**")).hasAnyRole(USER.name, ADMIN.name)
                .antMatchers(HttpMethod.POST, groupVariant.plus("/**")).hasAnyRole(USER.name, ADMIN.name)
                .antMatchers(HttpMethod.PATCH, groupVariant.plus("/**")).hasAnyRole(USER.name, ADMIN.name)
                .antMatchers(HttpMethod.POST, group.plus("/**")).hasAnyRole(USER.name, ADMIN.name)
                .antMatchers(HttpMethod.PATCH, group.plus("/**")).hasAnyRole(USER.name, ADMIN.name)
                .antMatchers(HttpMethod.PATCH, group.plus("/**")).hasAnyRole(USER.name, ADMIN.name)
                .anyRequest().authenticated()
                .and().csrf().disable()
    }
}
