package ru.kpfu.itis.demo.blog.impl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.demo.blog.api.dto.UserForm;
import ru.kpfu.itis.demo.blog.api.service.SignUpService;
import ru.kpfu.itis.demo.blog.impl.entity.UserEntity;
import ru.kpfu.itis.demo.blog.impl.jpa.repository.UsersRepository;

import java.util.Optional;

@Component
public class SignUpServiceImpl implements SignUpService {

    private final UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SignUpServiceImpl(PasswordEncoder passwordEncoder, UsersRepository usersRepository) {
        this.passwordEncoder = passwordEncoder;
        this.usersRepository = usersRepository;
    }


    @Override
    public boolean signUp(UserForm userForm) {
        Optional<UserEntity> userEntity = usersRepository.findByEmail(userForm.getEmail());
        if (userEntity.isPresent()) {
            return false;
        }

        UserEntity newUser = UserEntity.builder()
                .email(userForm.getEmail())
                .hashPassword(passwordEncoder.encode(userForm.getPassword()))
                .name(userForm.getUsername())
                .role(UserEntity.Role.USER)
                .state(UserEntity.State.ACTIVE)
                .provider(UserEntity.Provider.LOCAL)
                .build();
        usersRepository.save(newUser);
        return true;
    }

    @Override
    public void signUpOauth(String email, String name) {
        Optional<UserEntity> userEntity = usersRepository.findByEmail(email);
        if (!userEntity.isPresent()) {
            UserEntity newUser = UserEntity.builder()
                    .email(email)
                    .name(name)
                    .role(UserEntity.Role.USER)
                    .state(UserEntity.State.ACTIVE)
                    .provider(UserEntity.Provider.GOOGLE)
                    .build();
            usersRepository.save(newUser);
        }
    }
}
