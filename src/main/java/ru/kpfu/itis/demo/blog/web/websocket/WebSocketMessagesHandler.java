package ru.kpfu.itis.demo.blog.web.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import ru.kpfu.itis.demo.blog.web.dto.MessageDTO;

import java.util.HashMap;
import java.util.Map;

@Component
public class WebSocketMessagesHandler extends TextWebSocketHandler {

    private static final Map<String, WebSocketSession> sessions = new HashMap<>();

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage jsonMessage) throws Exception {
        MessageDTO message = objectMapper.readValue(jsonMessage.getPayload(), MessageDTO.class);
        // смотрю, не запоминал ли я еще такую сессию с такой id страницы
        if (!sessions.containsKey(message.getFrom())) {
            sessions.put(message.getFrom(), session);
        }

        for (WebSocketSession currentSession : sessions.values()) {
            currentSession.sendMessage(jsonMessage);
        }
    }
}
