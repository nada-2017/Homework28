package com.example.springsecurity.ApiException;

public class ApiException extends RuntimeException{

    public ApiException(String message){
        super(message);
    }
}
