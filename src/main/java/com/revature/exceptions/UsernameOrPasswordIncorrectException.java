package com.revature.exceptions;

public class UsernameOrPasswordIncorrectException extends Exception {
    public UsernameOrPasswordIncorrectException(){
        super("Incorrect username or password.");
    }

    public UsernameOrPasswordIncorrectException(String message){
        super(message);
    }
}
