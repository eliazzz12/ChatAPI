package com.dam.elias.chat.api.model;

import java.util.List;

public class GroupChat extends Chat {
    private List<User> users;

    public GroupChat(String name, List<User> users) {
        super(name);
        setUsers(users);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsers(List<User> users) {
        if(users == null) {
            throw new IllegalArgumentException("Users cannot be null");
        }
        this.users = users;
        System.out.println("GROUPCHAT USERS:");
        for(User user : users) {
            System.out.println(user.getUsername());
        }
    }

    public String getName() {
        return name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        if(user != null) {
            users.add(user);
        }
    }
}
