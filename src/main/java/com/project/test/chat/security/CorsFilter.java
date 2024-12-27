package com.project.test.chat.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class CorsFilter extends OncePerRequestFilter {

    @Value("${header.allowed.origin.url}")
    private String allowedOrigins;

    @Value("${header.allowed.methods}")
    private String allowedMethods;

    @Value("${header.allowed.headers}")
    private String allowedHeaders;

    @Value("${header.allowed.credentials}")
    private String allowedCredentials;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
        res.setHeader("Access-Control-Allow-Origin", allowedOrigins);
        res.setHeader("Access-Control-Allow-Methods", allowedMethods);
        res.setHeader("Access-Control-Allow-Headers", allowedHeaders);
        res.setHeader("Access-Control-Allow-Credentials", allowedCredentials);

        chain.doFilter(req, res);
    }
}
