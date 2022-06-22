package ru.kpfu.itis.demo.blog.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(BlogImplConfiguration.class)
public class BlogPostWebConfiguration {
}
