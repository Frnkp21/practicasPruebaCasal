package com.example.Errors;

public class UserIdNotFoundException extends Exception{

    public static final String DESCRIPTION = "User id not found: ";
    public UserIdNotFoundException(){
        super(DESCRIPTION);
    }
    public UserIdNotFoundException(String detail){
        super (DESCRIPTION+ detail);
    }
}
