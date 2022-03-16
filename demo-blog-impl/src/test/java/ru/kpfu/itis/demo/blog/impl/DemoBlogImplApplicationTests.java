package ru.kpfu.itis.demo.blog.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.kpfu.itis.demo.blog.impl.entity.PostEntity;
import ru.kpfu.itis.demo.blog.impl.entity.UserEntity;
import ru.kpfu.itis.demo.blog.impl.jpa.repository.UsersRepository;

import java.util.Optional;
import org.junit.jupiter.api.Assertions;

@SpringBootTest
class DemoBlogImplApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private UsersRepository usersRepository;

    @Test
    public void whenFindByName_thenReturnEmployee() {
        // given
        UserEntity alex = new UserEntity();
        alex.setEmail("email");
        usersRepository.save(alex);

        // when
        UserEntity found = usersRepository.findByEmail(alex.getEmail()).get();

        // then
        Assertions.assertEquals(found.getName(), alex.getName());
    }
}
