package ru.kpfu.itis.demo.blog.web;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoBlogWebApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testSuccess() {
        Assert.assertEquals(1, 1);
    }

    @Test
    public void testFailure() {
        Assert.assertEquals(1, 2);
    }

}