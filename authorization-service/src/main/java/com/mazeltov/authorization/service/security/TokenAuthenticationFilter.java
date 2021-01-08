package com.mazeltov.authorization.service.security;

import com.mazeltov.common.security.*;
import com.mazeltov.common.spring.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.context.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.web.filter.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @InjectLogger
    private Logger logger;

    @Value("${jwt.header}")
    private String AUTH_HEADER;

    @Autowired
    TokenHelper tokenHelper;

    @Autowired
    UserDetailsService userDetailServiceImpl;

    @Override
    public void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String authToken = UtilKt.getToken(request, AUTH_HEADER);

        if (authToken != null) {
            String username = tokenHelper.getUsernameFromToken(authToken);
            if (username != null) {
                UserDetails userDetails = userDetailServiceImpl.loadUserByUsername(username);

                TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
                authentication.setToken(authToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                SecurityContextHolder.getContext()
                        .setAuthentication(new AnonAuthentication());
                logger.trace("Username from token can't be found in DB.");
            }
        } else {
            SecurityContextHolder.getContext()
                    .setAuthentication(new AnonAuthentication());
            logger.trace("Authentication failed - no Bearer token provided.");
        }
        chain.doFilter(request, response);
    }
}
