package com.dam.elias.chat.api.model;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    /**
     * El nombre de usuario es él id del usuario,
     * queda libre para que lo usen otros usuarios cuando se cierra la sesión.
     */
    private String username;

    public User(String username) {
        setUsername(username);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

     @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(username);
    }
}
