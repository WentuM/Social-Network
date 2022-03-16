package ru.kpfu.itis.demo.blog.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Assert;

@SpringBootTest
class DemoBlogApiApplicationTests {

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
