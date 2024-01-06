package com.dolphin.websocket;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SessionManagerService {

    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
    private final Map<String, String> clientDetails = new ConcurrentHashMap<>();

    public void addSession( String email, WebSocketSession session) {
        sessions.put(session.getId(), session);
        clientDetails.put(session.getId(), email);
    }

    public void removeSession(String clientId) {
        sessions.remove(clientId);
        clientDetails.remove(clientId);
    }

    public WebSocketSession getSession(String clientId) {
        return sessions.get(clientId);
    }

    public String getEmailForClientId(String clientId) {
        return clientDetails.get(clientId);
    }


    public void sendMessageToClientsWithSameEmail(String email, String message) {
        for (Map.Entry<String, String> entry : clientDetails.entrySet()) {
            if (entry.getValue().equals(email)) {
                String clientId = entry.getKey();
                WebSocketSession session = getSession(clientId);
                if (session != null && session.isOpen()) {
                    try {
                        session.sendMessage(new TextMessage(message));
                    } catch (IOException e) {
                        // Handle exception
                        e.printStackTrace(); // Handle the exception based on your application's needs
                    }
                }
            }
        }
    }

    public void sendBinaryDataToClientsWithSameEmail(String email, BinaryMessage binaryData) {
        clientDetails.forEach((clientId, clientEmail) -> {
            if (clientEmail.equals(email)) {
                WebSocketSession session = getSession(clientId);
                if (session != null && session.isOpen()) {
                    try {
                        session.sendMessage(binaryData);
                    } catch (IOException e) {
                        // Handle exception
                        e.printStackTrace(); // Handle the exception based on your application's needs
                    }
                }
            }
        });
    }
}

