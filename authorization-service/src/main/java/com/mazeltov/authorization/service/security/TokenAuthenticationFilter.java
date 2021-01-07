package com.mazeltov.authorization.service.security;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.context.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.web.filter.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Value("${jwt.header}")
    private String AUTH_HEADER;

    @Autowired
    TokenHelper tokenHelper;

    @Autowired
    UserDetailsService userDetailServiceImpl;

    private String getToken(HttpServletRequest request) {

        String authHeader = request.getHeader(AUTH_HEADER);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }

        return null;
    }

    @Override
    public void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String error = "";
        String authToken = getToken(request);

        if (authToken != null) {

            // Get username from token
            String username = tokenHelper.getUsernameFromToken(authToken);
            if (username != null) {

                // Get user
                UserDetails userDetails =  userDetailServiceImpl.loadUserByUsername(username);

                // Create authentication
                TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
                authentication.setToken(authToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                error = "Username from token can't be found in DB.";
            }
        } else {
            error = "Authentication failed - no Bearer token provided.";
        }
        if (!error.equals("")) {
            System.out.println(error);
            SecurityContextHolder.getContext()
                    .setAuthentication(new AnonAuthentication()); // prevent show login form...
        }
        chain.doFilter(request, response);
    }
}
