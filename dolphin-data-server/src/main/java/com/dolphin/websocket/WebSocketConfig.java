package com.dolphin.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	
    @Autowired
    private CustomHandshakeInterceptor customHandshakeInterceptor;
    @Autowired
    private URLParamsHandshakeInterceptor urlParamsHandshakeInterceptor;

    
    @Autowired
    private MyHandlerBinary myHandlerBinary;
    

    @Autowired
    private MyHandler myHandler;
	
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myHandler, "ws/sensor_cloud")
                .addInterceptors(customHandshakeInterceptor)
                .setAllowedOrigins("*");

        registry.addHandler(myHandlerBinary, "ws/binaryHandler")
                .addInterceptors(customHandshakeInterceptor)
                .setAllowedOrigins("*");
        
        registry.addHandler(myHandler, "ws/react_server")
        .addInterceptors(urlParamsHandshakeInterceptor)
        .setAllowedOrigins("*");
        
    }


    @Bean
    ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize(8192);
        container.setMaxBinaryMessageBufferSize(8192);
        container.setAsyncSendTimeout( (long) 120000);
        
        return container;
    }

}
