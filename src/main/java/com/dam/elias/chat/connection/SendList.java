package com.dam.elias.chat.connection;

import com.dam.elias.chat.api.model.User;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class SendList implements Runnable {
    private ObjectOutputStream out;
    private List<User> list;

    public SendList(ObjectOutputStream out, List<User> list) {
        setOut(out);
        setUserList(list);
    }

    @Override
    public void run() {
        int tryCount = 0;
        boolean sent = false;
        do{
            try {
                out.writeObject(list);
                out.flush();
                sent = true;
            } catch (IOException _) {
                tryCount++;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException _) {
                    Thread.currentThread().interrupt();
                }
            }
        } while (!sent && tryCount < 1000);
    }

    public void setOut(ObjectOutputStream out) {
        if(out == null) {
            throw new IllegalArgumentException("ObjectOutputStream cannot be null");
        }
        this.out = out;
    }

    public void setUserList(List<User> list) {
        if(list == null) {
            throw new IllegalArgumentException("User list cannot be null");
        }
        this.list = list;
    }
}
