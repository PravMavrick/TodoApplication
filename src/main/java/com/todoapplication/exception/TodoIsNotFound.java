package com.todoapplication.exception;

public class TodoIsNotFound extends RuntimeException{
    public TodoIsNotFound(String s) {
        super(s);
    }
}
