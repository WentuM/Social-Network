package ru.kpfu.itis.demo.blog.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.security.PermitAll;

@Controller
public class SingleTweetController {

    @PermitAll
    @GetMapping("/single")
    public String getSignInPage() {
        return "home_twitter/singletweet";
    }
}
