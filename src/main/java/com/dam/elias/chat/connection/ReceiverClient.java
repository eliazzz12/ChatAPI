package com.dam.elias.chat.connection;

import com.dam.elias.chat.api.model.Message;
import com.dam.elias.chat.api.model.User;
import com.dam.elias.chat.server.exceptions.HandlerNotFoundException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReceiverClient implements Runnable {
    private static Receiver controller;
    private ObjectInputStream in;

    public ReceiverClient(Receiver controller, ObjectInputStream in) {
        setController(controller);
        setIn(in);
    }

    @Override
    public void run() {
        boolean running = true;
        while (!Thread.interrupted() && running) {
            try {
                handle(in.readObject());
            } catch (SocketException _){
                running = false;
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private interface Handler {
        void handle(Object o);
    }

    private static final Map<Class, Handler> dispatch = Map.ofEntries(
            Map.entry(Message.class, o -> handleMessage((Message) o)),
            Map.entry(User.class, o -> handleUser((User) o)),
            Map.entry(ArrayList.class, o -> handleList((ArrayList<User>) o))
    );

    private static void handle(Object o) {
        Handler h = dispatch.get(o.getClass());
        if (h == null) {
            throw new HandlerNotFoundException("Handler not found for " + o.getClass()+ " class");
        }
        h.handle(o);
    }

    private static void handleList(List<User> list) {
        controller.updateOnlineUsers(list);
    }

    static void handleMessage(Message message){
        System.out.println("ReceiverClient: Recibiendo mensaje= " + message.getText());
        controller.receiveNewMessage(message);
    }

    static void handleUser(User userToUpdate){
        throw new UnsupportedOperationException("Not implemented yet");
        //updateUser(): actualizar datos en todos los clientes que tengan conversación con el user
    }

    public void setController(Receiver controller) {
        if(controller == null) {
            throw new IllegalArgumentException("controller must not be null");
        }
        ReceiverClient.controller = controller;
    }

    private void setIn(ObjectInputStream in) {
        if(in == null){
            throw new IllegalArgumentException("Object input stream can't be null");
        }
        this.in = in;
    }
}
