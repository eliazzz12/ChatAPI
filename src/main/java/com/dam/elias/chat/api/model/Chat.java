package com.dam.elias.chat.api.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public abstract class Chat implements Serializable, Comparable<Chat> {
    protected String name;
    private final List<Message> messageList;
    private int unreadIndex;

    public Chat(String name) {
        setName(name);
        messageList = new ArrayList<>();
        unreadIndex = 0;
    }

    public void addMessage(Message message) {
        if(!messageList.contains(message)) {
            messageList.add(message);
            System.out.println("Chat: Message added: " + message.getText());
        }
        unreadIndex++;
        System.out.println("Chat: Mensajes no leidos: " + unreadIndex);
    }

    public void setAllRead(){
        unreadIndex = 0;
        System.out.println("Chat: TODO LEIDO");
    }

    public boolean isPrivate(){
        return this.getClass() == PrivateChat.class;
    }

    public Message getLastMessage(){
        return messageList.getLast();
    }

    public int getUnreadMessages(){
        return unreadIndex;
    }

    public void setName(String name) {
        if(name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    @Override
    public int compareTo(Chat o) {
        try {
            return this.getLastMessage().getTimestamp().compareTo(o.getLastMessage().getTimestamp());
        } catch (NullPointerException | NoSuchElementException _) {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chat chat = (Chat) o;
        return Objects.equals(name, chat.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
