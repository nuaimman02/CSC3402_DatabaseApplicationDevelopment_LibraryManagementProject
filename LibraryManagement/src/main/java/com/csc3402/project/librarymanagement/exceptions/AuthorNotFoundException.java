package com.csc3402.project.librarymanagement.exceptions;

public class AuthorNotFoundException extends RuntimeException {

    public AuthorNotFoundException(String message){
        super(message);
    }

}
