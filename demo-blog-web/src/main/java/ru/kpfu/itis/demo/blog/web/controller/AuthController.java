//package ru.kpfu.itis.demo.blog.web.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import ru.kpfu.itis.demo.blog.impl.entity.UserEntity;
//import ru.kpfu.itis.demo.blog.impl.jpa.repository.UsersRepository;
//
//@RestController
//@RequiredArgsConstructor
//public class AuthController {
//    private final UsersRepository usersRepository;
//
////    @GetMapping("/")
//    public UserEntity auth() {
//        String accountId = SecurityContextHolder.getContext().getAuthentication().getName();
//        return usersRepository.findByAccountId(accountId)
//                .orElseThrow(() -> new RuntimeException("user not found, accountId= " + accountId));
//    }
//}
