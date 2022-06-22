package ru.kpfu.itis.demo.blog.web;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.kpfu.itis.demo.blog.web.entity.ChatRoomEntity;
import ru.kpfu.itis.demo.blog.web.jpa.repository.ChatRoomRepository;
import ru.kpfu.itis.demo.blog.web.service.ChatRoomService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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