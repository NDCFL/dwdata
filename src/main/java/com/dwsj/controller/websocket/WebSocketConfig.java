package com.dwsj.controller.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myHandler(), "/sendOne").addInterceptors(new WebSocketInterceptor()).setAllowedOrigins("http://192.168.3.18:8080");
        registry.addHandler(myHandler(), "/sendAll").addInterceptors(new WebSocketInterceptor()).setAllowedOrigins("http://192.168.3.18:8080").withSockJS();
    }
    @Bean
    public WebSocketHandler myHandler() {
        return new MessageHandler();
    }
}