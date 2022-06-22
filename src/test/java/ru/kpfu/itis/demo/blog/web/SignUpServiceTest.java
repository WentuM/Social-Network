package ru.kpfu.itis.demo.blog.web;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kpfu.itis.demo.blog.web.dto.UserForm;
import ru.kpfu.itis.demo.blog.web.service.SignUpService;
import ru.kpfu.itis.demo.blog.web.entity.UserEntity;
import ru.kpfu.itis.demo.blog.web.jpa.repository.UsersRepository;
import ru.kpfu.itis.demo.blog.web.service.SignUpServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
class SignUpServiceTest {

    @Test
    void contextLoads() {
    }

    @TestConfiguration
    class UserServiceImplTestContextConfiguration {

        @Bean
        public SignUpService signUpService() {
            return new SignUpServiceImpl(passwordEncoder, usersRepository);
        }
    }

    @Autowired
    private SignUpServiceImpl signUpService;

    @MockBean
    ModelMapper modelMapper;

    @MockBean
    PasswordEncoder passwordEncoder;

    @MockBean
    private UsersRepository usersRepository;

    @Test
    public void whenUserIsNotSignUp_thenSignUpUser() {
        String email = "email@mail.ru";
        String name = "Danil";
        String password = "Password123";


        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);

        UserForm userForm = new UserForm();
        userForm.setEmail(email);
        userForm.setUsername(name);
        userForm.setPassword(password);

        UserEntity newUser = UserEntity.builder()
                .email(userForm.getEmail())
                .hashPassword(passwordEncoder.encode(userForm.getPassword()))
                .name(userForm.getUsername())
                .role(UserEntity.Role.USER)
                .state(UserEntity.State.ACTIVE)
                .provider(UserEntity.Provider.LOCAL)
                .build();

        when(usersRepository.findByEmail(email)).thenReturn(Optional.empty());

        assertTrue(signUpService.signUp(userForm));
    }

    @Test
    public void whenUserIsNotSignUp_thenCheckUserInDb() {
        String email = "email@mail.ru";
        String name = "Danil";
        String password = "Password123";


        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);

        UserForm userForm = new UserForm();
        userForm.setEmail(email);
        userForm.setUsername(name);
        userForm.setPassword(password);

        UserEntity newUser = UserEntity.builder()
                .email(userForm.getEmail())
                .hashPassword(passwordEncoder.encode(userForm.getPassword()))
                .name(userForm.getUsername())
                .role(UserEntity.Role.USER)
                .state(UserEntity.State.ACTIVE)
                .provider(UserEntity.Provider.LOCAL)
                .build();

        when(usersRepository.findByEmail(email)).thenReturn(Optional.empty());
        when(signUpService.signUp(userForm)).thenReturn(true);
        when(usersRepository.findByEmail(email)).thenReturn(Optional.ofNullable(newUser));

        assertEquals(newUser, usersRepository.findByEmail(email).orElseThrow(IllegalStateException::new));
    }
}