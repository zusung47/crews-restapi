package com.mentaljava.mentaljavarestapiproject.jwt.handler;

import com.mentaljava.mentaljavarestapiproject.table.admin.dto.AdminDTO;
import com.mentaljava.mentaljavarestapiproject.table.admin.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken loginToken = (UsernamePasswordAuthenticationToken) authentication;

        String id = loginToken.getName();
        String password = (String) loginToken.getCredentials();

        AdminDTO detailsUser = (AdminDTO) customUserDetailsService.loadUserByUsername(id);
        System.out.println("passwordEncoder.matches(password, detailsUser.getPassword()) ============ " + passwordEncoder.matches(password, detailsUser.getPassword()));
        System.out.println("passwordEncoder.matches(password, detailsUser.getPassword()) ============ " + password);
        System.out.println("passwordEncoder.matches(password, detailsUser.getPassword()) ============ " + detailsUser.getPassword());
        if(!passwordEncoder.matches(password, detailsUser.getPassword())) {
            throw new BadCredentialsException(password + ": Password is incorrect.");
        }

        return new UsernamePasswordAuthenticationToken(detailsUser, password, detailsUser.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
