package com.dolphin.websocket;

import java.util.Map;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;

@Component
public class URLParamsHandshakeInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(
        ServerHttpRequest request,
        ServerHttpResponse response,
        WebSocketHandler wsHandler,
        Map<String, Object> attributes
    ) throws Exception {
        // Extract details from request URL
        String query = request.getURI().getQuery();

        // Parse query parameters manually
        Map<String, List<String>> queryParams = UriComponentsBuilder.fromUriString(request.getURI().toString()).build().getQueryParams();
        
        String email = queryParams.getOrDefault("unique-identity", List.of("")).get(0);

        System.out.println("Unique Identity: " + email);

        // Optionally, set the unique-identity as an attribute for the WebSocket session
        // Here, I'm assuming that this parameter is necessary for the WebSocket session
        if (!email.isEmpty()) {
            attributes.put("unique-identity", email);
            return true; // Accept the connection
        }

        return false; // Reject the connection if the unique-identity parameter is missing or empty
    }

    @Override
    public void afterHandshake(
        ServerHttpRequest request,
        ServerHttpResponse response,
        WebSocketHandler wsHandler,
        Exception ex
    ) {
        // After handshake logic (if needed)
    }
}


