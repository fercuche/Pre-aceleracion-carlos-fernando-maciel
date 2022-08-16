package com.alkemy.challenge.exception;

public class ParamNotFound extends RuntimeException{
    public ParamNotFound(String error){
        super(error);
    }
}
