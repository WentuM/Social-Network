package ru.kpfu.itis.demo.blog.impl;

import org.checkerframework.checker.units.qual.C;
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
import ru.kpfu.itis.demo.blog.impl.entity.ChatRoomEntity;
import ru.kpfu.itis.demo.blog.impl.entity.UserEntity;
import ru.kpfu.itis.demo.blog.impl.jpa.repository.ChatRoomRepository;
import ru.kpfu.itis.demo.blog.impl.jpa.repository.UsersRepository;
import ru.kpfu.itis.demo.blog.impl.service.ChatMessageService;
import ru.kpfu.itis.demo.blog.impl.service.ChatRoomService;
import ru.kpfu.itis.demo.blog.impl.service.UserServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class ChatRoomServiceTest {

    @Test
    void contextLoads() {
    }

    @MockBean
    ModelMapper modelMapper;

    @MockBean
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private ChatRoomService chatRoomService;

    @Before
    public void setUp() {

    }

    @Test
    public void whenValidId_thenChatRoomShouldBeFound() {
        String receiveId = "0";
        String sendId = "1";

        ChatRoomEntity chatRoomEntity = new ChatRoomEntity();
        chatRoomEntity.setRecipientId(receiveId);
        chatRoomEntity.setSenderId(sendId);
        chatRoomEntity.setChatId(sendId + "_" + receiveId);

        when(chatRoomRepository.findBySenderIdAndRecipientId(sendId, receiveId)).thenReturn(Optional.of(chatRoomEntity));

        assertEquals(chatRoomEntity.getChatId(), chatRoomService.getChatId(sendId, receiveId, false).orElseThrow(IllegalStateException::new));
    }

    @Test
    public void whenNotValidIdAndCreateIfNotExistFalse_thenReturnEmptyId() {
        String receiveId = "0";
        String sendId = "1";

        ChatRoomEntity chatRoomEntity = new ChatRoomEntity();
        chatRoomEntity.setRecipientId(receiveId);
        chatRoomEntity.setSenderId(sendId);
        chatRoomEntity.setChatId(sendId + "_" + receiveId);

        when(chatRoomRepository.findBySenderIdAndRecipientId(sendId, receiveId)).thenReturn(Optional.empty());

        assertEquals("", chatRoomService.getChatId(sendId, receiveId, false).orElseThrow(IllegalStateException::new));
    }

    @Test
    public void whenNotValidIdAndCreateIfNotExistTrue_thenReturnCreateNewChatId() {
        String receiveId = "0";
        String sendId = "1";

        ChatRoomEntity chatRoomEntity = new ChatRoomEntity();
        chatRoomEntity.setRecipientId(receiveId);
        chatRoomEntity.setSenderId(sendId);
        chatRoomEntity.setChatId(sendId + "_" + receiveId);

        when(chatRoomRepository.findBySenderIdAndRecipientId(sendId, receiveId)).thenReturn(Optional.empty());

        assertEquals(chatRoomEntity.getChatId(), chatRoomService.getChatId(sendId, receiveId, true).orElseThrow(IllegalStateException::new));
    }
}