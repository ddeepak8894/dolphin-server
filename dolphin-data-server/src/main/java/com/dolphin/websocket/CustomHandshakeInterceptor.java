package com.dolphin.websocket;

import java.util.Map;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

@Component
public class CustomHandshakeInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(
        ServerHttpRequest request,
        ServerHttpResponse response,
        WebSocketHandler wsHandler,
        Map<String, Object> attributes
    ) throws Exception {
        // Extract details from request (headers, URL params, payload)
        String clientId = request.getHeaders().getFirst("client-ID");
        String email = request.getHeaders().getFirst("unique-identity");
        String clientType=request.getHeaders().getFirst("client-type");
        System.out.println(clientId+email);

        // Perform validation or link operations
        boolean isValid = validateAndLink(clientId, email);

        // Optionally, set attributes to pass data to WebSocket session
        if (isValid) {
            attributes.put("clientID", clientId);
            attributes.put("unique-identity", email);
            attributes.put("clientType", clientType);
        }

        return isValid; // Return true if connection should be accepted
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

    private boolean validateAndLink(String clientId, String email) {
        // Implement your validation and linking logic here
        // Return true if the connection should be accepted
        // Return false to reject the connection
        // Example: Check if the client ID and email are valid or link them together
        return (clientId != null && email != null);
    }
}
