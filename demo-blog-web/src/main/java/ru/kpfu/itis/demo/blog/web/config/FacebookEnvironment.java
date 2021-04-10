package ru.kpfu.itis.demo.blog.web.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("client")
public class FacebookEnvironment extends CredentialsProperties  {
}
