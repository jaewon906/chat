package com.project.test.chat.security;

import com.project.test.chat.LoginFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CorsFilter corsFilter;

    private final LoginFilter loginFilter;

    public SecurityConfig(CorsFilter corsFilter, LoginFilter loginFilter) {
        this.corsFilter = corsFilter;
        this.loginFilter = loginFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(loginFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
