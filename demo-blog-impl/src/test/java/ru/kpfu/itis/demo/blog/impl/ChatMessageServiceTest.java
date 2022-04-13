package ru.kpfu.itis.demo.blog.impl;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import ru.kpfu.itis.demo.blog.api.dto.PostDTO;
import ru.kpfu.itis.demo.blog.api.dto.UserDTO;
import ru.kpfu.itis.demo.blog.api.service.UserService;
import ru.kpfu.itis.demo.blog.impl.entity.ChatMessageEntity;
import ru.kpfu.itis.demo.blog.impl.entity.UserEntity;
import ru.kpfu.itis.demo.blog.impl.jpa.repository.ChatMessageRepository;
import ru.kpfu.itis.demo.blog.impl.jpa.repository.ChatRoomRepository;
import ru.kpfu.itis.demo.blog.impl.jpa.repository.UsersRepository;
import ru.kpfu.itis.demo.blog.impl.service.ChatMessageService;
import ru.kpfu.itis.demo.blog.impl.service.UserServiceImpl;

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

        assertEquals(chatMessageEntity, chatMessageRepository.findById(chatMessageEntity.getId()));
    }

    @Test
    public void shouldSaveChatMessageAndGetByChatId() {
        String content = "content";
        String id = "id";
        String chatId = "id";

        ChatMessageEntity chatMessageEntity = new ChatMessageEntity();
        chatMessageEntity.setId(id);
        chatMessageEntity.setChatId(chatId);
        chatMessageEntity.setContent(content);

        when(chatMessageRepository.save(chatMessageEntity)).thenReturn(chatMessageEntity);

        assertEquals(chatMessageEntity, chatMessageRepository.findByChatId(chatMessageEntity.getChatId()));
    }
}