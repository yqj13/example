package com.imli.exception;

public class ServiceException extends RuntimeException{
    public ServiceException(String msg){
        super(msg);
    }
}
