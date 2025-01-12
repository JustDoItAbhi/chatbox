package kafkaproducer.kafkaproducer.websockets;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

public class ChatWebSocketHandler extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        // Broadcast the message to all connected WebSocket clients
        try {
            session.sendMessage(new TextMessage("New message received: " + message.getPayload()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}