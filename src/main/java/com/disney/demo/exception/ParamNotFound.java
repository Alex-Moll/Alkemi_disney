package com.disney.demo.exception;

public class ParamNotFound extends RuntimeException {
    
    public ParamNotFound(String error){
        super(error);
    }
    
}
