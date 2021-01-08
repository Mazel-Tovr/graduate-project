package com.mazeltov.authorization.service.security;

import com.fasterxml.jackson.databind.*;
import com.mazeltov.authorization.service.dao.model.*;
import com.mazeltov.common.spring.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.security.web.authentication.*;
import org.springframework.stereotype.*;

import javax.servlet.http.*;

@Component
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @InjectLogger
    private Logger logger;

    @Value("${jwt.expires_in}")
    private int EXPIRES_IN;

    @Autowired
    TokenHelper tokenHelper;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        clearAuthenticationAttributes(request);
        User user = (User) authentication.getPrincipal();
        String jwt = tokenHelper.generateToken(user.getUsername(), user.getAuthorities());

        UserTokenState userTokenState = new UserTokenState(jwt, EXPIRES_IN);
        try {
            String jwtResponse = objectMapper.writeValueAsString(userTokenState);
            response.setContentType("application/json");
            response.getWriter().write(jwtResponse);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    private static class UserTokenState {
        private String jws;
        private int expires;

        public UserTokenState(String jws, int expires) {
            this.jws = jws;
            this.expires = expires;
        }

        public String getJws() {
            return jws;
        }

        public void setJws(String jws) {
            this.jws = jws;
        }

        public int getExpires() {
            return expires;
        }

        public void setExpires(int expire) {
            this.expires = expire;
        }
    }
}
