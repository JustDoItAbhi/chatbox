package kafkaconsumer.kafkaconsumer.websockets;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new ChatWebSocketHandler(), "/chat").setAllowedOrigins("*");
    }
}

class ChatWebSocketHandler extends TextWebSocketHandler {

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

