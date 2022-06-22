package ru.kpfu.itis.demo.blog.web.service;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.demo.blog.web.aspect.DemoBefore;

@Service
public class TestAopService {
    public void sayHello(){
        System.out.println("Hello");
        privateHello();
    }

    @DemoBefore
    public String hello(){
        return "Hello";
    }

    public void publicHello(){
        System.out.println("public Hello");
    }
    private void privateHello(){
        System.out.println("private Hello");
    }
}
