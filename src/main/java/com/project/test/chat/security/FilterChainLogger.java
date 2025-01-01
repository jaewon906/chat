package com.project.test.chat.security;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
public class FilterChainLogger {
    private final ApplicationContext applicationContext;

    public FilterChainLogger(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void logFilterChain() {
        FilterChainProxy filterChainProxy = applicationContext.getBean(FilterChainProxy.class);
        List<SecurityFilterChain> filterChains = filterChainProxy.getFilterChains();

        for (SecurityFilterChain chain : filterChains) {
            System.out.println("Filter Chain: " + chain);
            chain.getFilters().forEach(filter ->
                    System.out.println(" - Filter: " + filter.getClass().getSimpleName())
            );
        }
    }
}
