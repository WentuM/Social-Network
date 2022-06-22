package ru.kpfu.itis.demo.blog.web;

import org.checkerframework.checker.nullness.Opt;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import ru.kpfu.itis.demo.blog.web.entity.ChatMessageEntity;
import ru.kpfu.itis.demo.blog.web.jpa.repository.ChatMessageRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class ChatMessageServiceTest {

    @Test
    void contextLoads() {
    }

    @MockBean
    ModelMapper modelMapper;

    @MockBean
    private ChatMessageRepository chatMessageRepository;

    @Before
    public void setUp() {

    }

    @Test
    public void shouldSaveChatMessage() {
        String content = "content";
        String id = "id";
        String chatId = "id";

        ChatMessageEntity chatMessageEntity = new ChatMessageEntity();
        chatMessageEntity.setId(id);
        chatMessageEntity.setChatId(chatId);
        chatMessageEntity.setContent(content);

        when(chatMessageRepository.save(chatMessageEntity)).thenReturn(chatMessageEntity);
        when(chatMessageRepository.findById(id)).thenReturn(Optional.of(chatMessageEntity));

        assertEquals(Optional.of(chatMessageEntity), chatMessageRepository.findById(chatMessageEntity.getId()));
    }
}