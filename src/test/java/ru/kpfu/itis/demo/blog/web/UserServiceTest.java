package ru.kpfu.itis.demo.blog.web;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import ru.kpfu.itis.demo.blog.web.dto.UserDTO;
import ru.kpfu.itis.demo.blog.web.entity.UserEntity;
import ru.kpfu.itis.demo.blog.web.jpa.repository.UsersRepository;
import ru.kpfu.itis.demo.blog.web.service.UserService;
import ru.kpfu.itis.demo.blog.web.service.UserServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {

    @Test
    void contextLoads() {
    }

    @TestConfiguration
    class UserServiceImplTestContextConfiguration {

        @Bean
        public UserService userService() {
            return new UserServiceImpl(modelMapper);
        }
    }

    @MockBean
    ModelMapper modelMapper;

    @Autowired
    private UserServiceImpl userService;

    @MockBean
    private UsersRepository usersRepository;

    @Before
    public void setUp() {

    }

    @Test
    public void whenValidEmail_thenUserShouldBeFound() {
        String email = "email";

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);

        UserDTO userDTO = new UserDTO();
        userDTO.setUserEmail(email);

        when(usersRepository.findByEmail(email)).thenReturn(Optional.of(userEntity));
        when(modelMapper.map(userEntity, UserDTO.class)).thenReturn(userDTO);

        assertEquals(userDTO, userService.findByEmail(email).orElseThrow(IllegalStateException::new));
        verify(modelMapper, times(1)).map(userEntity, UserDTO.class);
    }
}