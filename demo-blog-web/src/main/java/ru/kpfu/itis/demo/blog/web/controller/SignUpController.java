package ru.kpfu.itis.demo.blog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kpfu.itis.demo.blog.api.dto.UserForm;
import ru.kpfu.itis.demo.blog.api.service.SignUpService;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.util.Map;

@Controller
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @PermitAll
    @GetMapping("/signUp")
    public String getSignUpPage() {
        return "login/registration";
    }

    @PermitAll
    @PostMapping("/signUp")
    public String signUp(@Valid UserForm userForm, BindingResult bindingResult, Model model) {
        if (userForm.getPassword() != null && !userForm.getPassword().equals(userForm.getRepeat_password())) {
            model.addAttribute("passwordError", "Пароли не совпадают");

            return "login/registration";
        }
        if (bindingResult.hasErrors()) {    
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errors);

            return "login/registration";
        }

        if (!signUpService.signUp(userForm)) {
            model.addAttribute("emailError", "Пользователь с такой почтой уже существует");
            return "login/registration";
        }
        return "redirect:/signIn";
    }
}
