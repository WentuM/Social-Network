package ru.kpfu.itis.demo.blog.web;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.kpfu.itis.demo.blog.web.entity.ChatMessageEntity;
import ru.kpfu.itis.demo.blog.web.jpa.repository.ChatMessageRepository;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

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

        Assertions.assertEquals(Optional.of(chatMessageEntity), chatMessageRepository.findById(chatMessageEntity.getId()));
    }
}