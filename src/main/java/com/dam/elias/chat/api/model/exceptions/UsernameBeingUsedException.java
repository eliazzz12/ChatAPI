package com.dam.elias.chat.api.model.exceptions;

public class UsernameBeingUsedException extends RuntimeException {
    public UsernameBeingUsedException(String message) {
        super(message);
    }
    public UsernameBeingUsedException() {}
}
