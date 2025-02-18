package com.dam.elias.chat.connection;

import com.dam.elias.chat.api.model.Message;
import com.dam.elias.chat.api.model.User;

import java.util.List;

/**
 * This interface defines how to receive information from the server
 */
public interface Receiver {
    void updateOnlineUsers(List<User> list);
    void receiveNewMessage(Message message);
}
