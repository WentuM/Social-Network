package ru.kpfu.itis.demo.blog.impl;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import ru.kpfu.itis.demo.blog.api.dto.UserDTO;
import ru.kpfu.itis.demo.blog.api.service.UserService;
import ru.kpfu.itis.demo.blog.impl.entity.UserEntity;
import ru.kpfu.itis.demo.blog.impl.jpa.repository.ChatRoomRepository;
import ru.kpfu.itis.demo.blog.impl.jpa.repository.UsersRepository;
import ru.kpfu.itis.demo.blog.impl.service.ChatMessageService;
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

    @Before
    public void setUp() {

    }

//    @Test
//    public void whenValidId_thenChatRoomShouldBeFound() {
//        String email = "email";
//
//        UserEntity userEntity = new UserEntity();
//        userEntity.setEmail(email);
//
//        UserDTO userDTO = new UserDTO();
//        userDTO.setUserEmail(email);
//    }
}