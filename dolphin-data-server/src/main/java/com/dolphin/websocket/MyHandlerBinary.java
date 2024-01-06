package com.dolphin.websocket;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.adapter.standard.StandardWebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

import jakarta.websocket.Session;

@Component
public class MyHandlerBinary extends BinaryWebSocketHandler {
	@Autowired
    private SessionManagerService sessionManagerService;
    

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String clientId = (String) session.getId();
        String email = (String) session.getAttributes().get("unique-identity");
        System.out.println("=afterConnectionEstablished binary===="+clientId+email);
        sessionManagerService.addSession(email, session);
        final Session nativeSession = ((StandardWebSocketSession) session).getNativeSession(Session.class);
        nativeSession.getUserProperties()
            .put("org.apache.tomcat.websocket.BLOCKING_SEND_TIMEOUT", 60_000);
        super.afterConnectionEstablished(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);

        sessionManagerService.removeSession(session.getId());
    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws IOException {
        sessionManagerService.sendBinaryDataToClientsWithSameEmail(sessionManagerService.getEmailForClientId(session.getId()), message);
    }

}
