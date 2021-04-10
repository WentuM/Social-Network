package ru.kpfu.itis.demo.blog.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "keys")
public class SocialEncryptEnvironment {
    private String key;
    private String salt;
}
