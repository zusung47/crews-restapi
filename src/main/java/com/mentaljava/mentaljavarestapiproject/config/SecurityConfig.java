package com.mentaljava.mentaljavarestapiproject.config;

import com.mentaljava.mentaljavarestapiproject.jwt.filter.CustomAuthenticationFilter;
import com.mentaljava.mentaljavarestapiproject.jwt.handler.CustomAuthFailureHandler;
import com.mentaljava.mentaljavarestapiproject.jwt.handler.CustomAuthSuccessHandler;
import com.mentaljava.mentaljavarestapiproject.jwt.handler.JwtAccessDeniedHandler;
import com.mentaljava.mentaljavarestapiproject.jwt.handler.JwtAuthenticationEntryPoint;
import com.mentaljava.mentaljavarestapiproject.jwt.handler.TokenProvider;
import java.util.Arrays;
import javax.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final CustomAuthenticationFilter customAuthenticationFilter;
    private final CustomAuthSuccessHandler customAuthSuccessHandler;
    private final CustomAuthFailureHandler customAuthFailureHandler;


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().antMatchers("/css/**", "/js/**", "/images/**",
                "/lib/**", "/productimgs/**");
//        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .exceptionHandling()
                /* 기본 시큐리티 설정에서 JWT 토큰과 관련된 유효성과 권한 체크용 설정*/
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)//  필요한 권한 없이 접근(403)
                .accessDeniedHandler(jwtAccessDeniedHandler)          // 유효한 자격 증명 없을 시(401)

                .and()
                .authorizeRequests()
//                .antMatchers("/api/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/auth/**").permitAll()
                .anyRequest().permitAll()
                .and()
                /* 세션 인증 방식을 쓰지 않겠다는 설정 */
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors()
                .and()
                /* jwt토큰 방식을 쓰겠다는 설정*/
                .apply(new JwtSecurityConfig(tokenProvider))

                .and()
                .formLogin().disable()
                .addFilterBefore(customAuthFilter(), UsernamePasswordAuthenticationFilter.class)
                .httpBasic().disable();


        return httpSecurity.build();

    }

    private Filter customAuthFilter() {
        customAuthenticationFilter.setFilterProcessesUrl("/admin/login");
        customAuthenticationFilter.setAuthenticationSuccessHandler(customAuthSuccessHandler);
        customAuthenticationFilter.setAuthenticationFailureHandler(customAuthFailureHandler);
        customAuthenticationFilter.afterPropertiesSet();
        return customAuthenticationFilter;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration config = new CorsConfiguration();

        // 허용할 출처(origin) 목록
        config.setAllowedOrigins(Arrays.asList(
                "http://localhost:8080",
                "https://kauth.kakao.com",
                "https://kapi.kakao.com",
                "http://localhost:3000"
        ));

        // 허용할 HTTP 메서드 목록
        config.setAllowedMethods(Arrays.asList("GET", "PUT", "POST", "OPTIONS", "DELETE"));

        // 허용할 HTTP 헤더 목록
        config.setAllowedHeaders(Arrays.asList(
                "Authorization",
                "Content-Type",
                "Access-Control-Allow-Origin",
                "Access-Control-Allow-Credentials",
                "X-Requested-With",
                "application/json",
                "RefreshToken"
        ));

        // 자격 증명 포함 설정
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }



}
