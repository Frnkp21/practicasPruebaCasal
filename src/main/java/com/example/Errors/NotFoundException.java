package com.example.Errors;

public class NotFoundException extends RuntimeException{
    private static final String DESCRIPTION = "Not Found Exception (401)";

    public NotFoundException(String detail){
        super(DESCRIPTION + ". "+ detail);
    }
}
