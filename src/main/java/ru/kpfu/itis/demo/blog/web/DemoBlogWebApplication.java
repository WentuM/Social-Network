package ru.kpfu.itis.demo.blog.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class DemoBlogWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoBlogWebApplication.class, args);
    }

}
