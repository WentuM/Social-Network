package ru.kpfu.itis.demo.blog.web.exception;

public class NotFoundException extends Exception {
    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }
}
