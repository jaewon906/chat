package com.project.test.chat.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 메시지 브로커 설정
//        config.setUserDestinationPrefix("prefix"); // 사용자 별로 세션을 구분하여 특정 사용자에게 메시지 전달
        config.enableSimpleBroker("/subscribe"); // 클라이언트가 구독할 prefix
        config.setApplicationDestinationPrefixes("/publish"); // 클라이언트가 메시지를 보낼 prefix
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // WebSocket 연결 엔드포인트
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("http://localhost:*")
                .withSockJS(); // SockJS 지원

    }
}

