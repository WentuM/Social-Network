package ru.itis.springlectionsdemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.springlectionsdemo.dto.UserForm;
import ru.itis.springlectionsdemo.models.User;
import ru.itis.springlectionsdemo.repositories.UsersRepository;

@Component
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UsersRepository usersRepository;
    //

    @Override
    public void signUp(UserForm form) {
        User newUser = new User();
        newUser.setEmail(form.getEmail());
        newUser.setPassword(form.getPassword());
        usersRepository.save(newUser);
    }
}
