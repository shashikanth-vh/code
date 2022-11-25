package com.springboot.example.app;

public class AppNotFoundException extends RuntimeException {

    public AppNotFoundException(String exception) {
        super(exception);
    }

}
