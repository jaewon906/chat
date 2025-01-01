package com.project.test.chat.security;

import com.project.test.chat.CONST.CONSTS;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
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
        http.csrf(AbstractHttpConfigurer::disable); // POST요청 시 CSRF 토큰 필요
        http.authorizeHttpRequests((auth)->{
            auth.requestMatchers("/").permitAll()
                .requestMatchers("/ws/**").authenticated() // WEBSOCKET
                .requestMatchers("/REST/v2/home").permitAll() // API
                .requestMatchers("/REST/v2/login/**").permitAll()
                .requestMatchers("/REST/v2/signup/**").permitAll()
                .requestMatchers("/REST/v2/auth/checkAuthentication").authenticated()
//                .requestMatchers("/REST/v2/chat").authenticated()
                .requestMatchers("/REST/v2/admin/**").hasAuthority(CONSTS.ROLE_ADMIN)
                .anyRequest().authenticated();
//            }).exceptionHandling((ex)->{
//                  ex.authenticationEntryPoint(authenticationEntryPoint())
//                    .accessDeniedHandler(accessDeniedHandler());
        });
//      .formLogin(form->form.loginPage("/notLogin"));

        http.addFilterBefore(loginFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(corsFilter, LoginFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (req, res, authException) -> {
            System.out.println("authenticationEntryPoint");
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        };
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (req, res, accessDeniedException) -> {
            System.out.println("accessDeniedHandler");
            res.sendError(HttpServletResponse.SC_FORBIDDEN);
        };
    }
}
