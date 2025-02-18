package com.dam.elias.chat;

import com.dam.elias.chat.api.model.exceptions.UserNotInThisChatException;

import java.util.Objects;

public class PrivateChat extends Chat {
    private User firstUser, secondUser;

    public PrivateChat(User firstUser, User secondUser) {
        super(secondUser.getUsername());
        setUser(firstUser);
        setUser(secondUser);
    }

    /**
     * Returns the other user in this chat
     * @param sender one user of the chat
     * @return the other user
     * @throws UserNotInThisChatException
     */
    public User getOtherUser(User sender) throws UserNotInThisChatException {
        if(firstUser.equals(sender)) {
            return secondUser;
        } else if(secondUser.equals(sender)) {
            return firstUser;
        }
        throw new UserNotInThisChatException();
    }

    public void setUser(User user) {
        if(user == null){
            throw new IllegalArgumentException("A user cannot be set to null");
        }
        if(firstUser == null){
            firstUser = user;
        } else if(secondUser == null) {
            secondUser = user;
        } else {
            throw new IllegalArgumentException("All users are already set");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PrivateChat that = (PrivateChat) o;
        return Objects.equals(firstUser, that.firstUser) && Objects.equals(secondUser, that.secondUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstUser, secondUser);
    }
}
