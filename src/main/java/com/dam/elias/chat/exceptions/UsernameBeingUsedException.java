package com.dam.elias.chat.exceptions;

public class UsernameBeingUsedException extends RuntimeException {
    public UsernameBeingUsedException(String message) {
        super(message);
    }
    public UsernameBeingUsedException() {}
}
