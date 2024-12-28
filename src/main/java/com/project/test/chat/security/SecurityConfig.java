package com.project.test.chat.security;

import com.project.test.chat.CONST.CONSTS;
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
            http.authorizeHttpRequests((auth)->{
                auth.requestMatchers("/").permitAll()
                     .requestMatchers("/ws/**").hasAnyRole(CONSTS.ROLE_USER, CONSTS.ROLE_ADMIN) // WEBSOCKET
                     .requestMatchers("/REST/v2/home").permitAll() // API
                     .requestMatchers("/REST/v2/login").permitAll()
                     .requestMatchers("/REST/v2/checkAuthentication").hasAnyRole(CONSTS.ROLE_USER, CONSTS.ROLE_ADMIN)
                    .requestMatchers("/REST/v2/chat").hasAnyRole(CONSTS.ROLE_USER, CONSTS.ROLE_ADMIN)
                    .requestMatchers("/REST/v2/main").hasAnyRole(CONSTS.ROLE_USER, CONSTS.ROLE_ADMIN);
        });
//          .formLogin(form->form.loginPage("/notLogin"));

        http.addFilterBefore(loginFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
