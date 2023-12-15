package com.mentaljava.mentaljavarestapiproject.jwt.filter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mentaljava.mentaljavarestapiproject.jwt.handler.CustomAuthenticationProvider;
import com.mentaljava.mentaljavarestapiproject.table.admin.dto.AdminDTO;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private CustomAuthenticationProvider customAuthenticationProvider;

    public CustomAuthenticationFilter(CustomAuthenticationProvider customAuthenticationProvider) {
        super.setAuthenticationManager(new ProviderManager(customAuthenticationProvider));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authRequest;

        try {
            authRequest = getAuthRequest(request);
            setDetails(request, authRequest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private UsernamePasswordAuthenticationToken getAuthRequest(HttpServletRequest request) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);

        AdminDTO user = objectMapper.readValue(request.getInputStream(), AdminDTO.class);
        System.out.println("user ============ " + user);
        return new UsernamePasswordAuthenticationToken(user.getAdminId(), user.getAdminPw(), user.getAuthorities());
    }
}
