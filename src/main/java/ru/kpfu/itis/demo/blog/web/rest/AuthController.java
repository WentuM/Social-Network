package ru.kpfu.itis.demo.blog.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.demo.blog.web.dto.UserDTO;
import ru.kpfu.itis.demo.blog.web.dto.UserForm;
import ru.kpfu.itis.demo.blog.web.service.SignUpService;
import ru.kpfu.itis.demo.blog.web.service.UserService;
import ru.kpfu.itis.demo.blog.web.controller.ControllerUtils;
import ru.kpfu.itis.demo.blog.web.security.config.jwt.JwtProvider;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/")
public class AuthController {

    @Autowired
    private SignUpService signUpService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;

    private final AuthenticationManager authenticationManager;

    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/auth")
    public ResponseEntity<Map<String, String>> auth(@RequestBody AuthRequest request) {
        UserDTO userDTO = userService.findByEmailAndPassword(request.getLogin(), request.getPassword());
        System.out.println(userDTO);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUserEmail(), userDTO.getPassword()));
        String token = jwtProvider.generateToken(userDTO.getUserEmail());
        Map<String, String> response = new HashMap<>();
        response.put("email", userDTO.getUserEmail());
        response.put("access-token", token);
        return ResponseEntity.ok(response);
    }

    @PermitAll
    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@Valid UserForm userForm, BindingResult bindingResult) {
        Map<String, String> response = new HashMap<>();
        if (userForm.getPassword() != null && !userForm.getPassword().equals(userForm.getRepeat_password())) {
            response.put("passwordError", "Пароли не совпадают");
        }
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        if (!signUpService.signUp(userForm)) {
            response.put("emailError", "Пользователь с такой почтой уже существует");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
