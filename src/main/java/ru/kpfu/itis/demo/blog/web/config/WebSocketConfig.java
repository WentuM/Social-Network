package ru.kpfu.itis.demo.blog.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.socket.config.annotation.*;
import ru.kpfu.itis.demo.blog.web.websocket.WebSocketHandshakeHandler;
import ru.kpfu.itis.demo.blog.web.websocket.WebSocketMessagesHandler;

@Configuration
@EnableWebSocket
@Import(BlogImplConfiguration.class)
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @EnableWebSocket
    @Configuration
    public class WebSocketConfiguration implements WebSocketConfigurer {

        @Autowired
        private WebSocketMessagesHandler messagesHandler;

        @Autowired
        private WebSocketHandshakeHandler handshakeHandler;

        @Override
        public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
            webSocketHandlerRegistry.addHandler(messagesHandler, "/chat").setHandshakeHandler(handshakeHandler);
        }
    }

}
