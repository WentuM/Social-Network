//package ru.kpfu.itis.demo.blog.web.controller;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.security.Principal;
//import java.util.Map;
//
//@RestController
//public class FacebookController {
//
//    @GetMapping("/")
//    public String home(Principal principal) {
//        Map<String, Object> authDetails = (Map<String, Object>) ((OAuth2Authentication) principal)
//                .getUserAuthentication()
//                .getDetails();
//
//        String userName = (String) authDetails.get("name");
//
//        return "Hey " + userName + ", Welcome to Daily Code Buffer!!";
//    }
//}