package com.antkorwin.githubactions;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class DataController {

    @GetMapping("/date")
    public String date() {
        return new Date().toString();
    }
}
