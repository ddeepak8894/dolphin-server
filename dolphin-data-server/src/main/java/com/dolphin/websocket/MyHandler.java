package com.dolphin.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class MyHandler extends TextWebSocketHandler {
	
    @Autowired
    private SessionManagerService sessionManagerService;


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String clientId = (String) session.getId();
        String email = (String) session.getAttributes().get("unique-identity");
        System.out.println("=afterConnectionEstablished===="+clientId+email);
        sessionManagerService.addSession(email, session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String clientId = (String) (String) session.getId();
        String email = sessionManagerService.getEmailForClientId(clientId);
        System.out.println("===handleTextMessage=="+clientId+email);

        // Your message processing logic here...
        sessionManagerService.sendMessageToClientsWithSameEmail(email, message.getPayload());
    }



	public void someEventTriggered(String message) {
		// Some event triggered, broadcast the message to all connected clients
		
	}
	
}
