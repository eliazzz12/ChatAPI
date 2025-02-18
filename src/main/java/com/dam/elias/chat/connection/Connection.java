package com.dam.elias.chat.connection;

import javafx.application.Application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;

public class Connection {
    private Socket server;
    protected String address;
    protected int port;
    protected ObjectOutputStream out;
    protected ObjectInputStream in;
    private boolean connected = false;

    public Connection(Application.Parameters params) {
        List<String> parameterList = params.getRaw();
        Iterator<String> iterator = parameterList.iterator();
        while (iterator.hasNext()) {
            String parameter = iterator.next();
            if(parameter.equalsIgnoreCase("-ip")) {
                String ip = iterator.next();
                System.out.println("Direccion ip: "+ip);
                setAddress(ip);
            }
            if(parameter.equalsIgnoreCase("-port")) {
                String port = iterator.next();
                System.out.println("Port: "+port);
                setPort(Integer.parseInt(port));
            }
        }
    }

    /**
     * Establishes the connection with the server
     * @throws IOException if an I/O error occurs when creating the socket.
     * @throws UnknownHostException if no IP address for the host could be found,
     * or if a scope_id was specified for a global IPv6 address.
     */
    public void connect() throws IOException, UnknownHostException {
        InetAddress addr = InetAddress.getByName(address);
        System.out.println("Conectando a " + address + ":" + port);
        server = new Socket(addr, port);
        out = new ObjectOutputStream(server.getOutputStream());
        in = new ObjectInputStream(server.getInputStream());
        connected = true;
    }

    /**
     * Closes the server connection
     *
     * @throws IOException if an I/ O error occurs when closing this socket.
     */
    public void disconnect() throws IOException {
        in.close();
        out.close();
        server.close();
        connected = false;
    }

    private void setAddress(String address) {
        if(address == null || address.isEmpty()) {
            throw new IllegalArgumentException("address cannot be null or empty");
        }
        this.address = address;
    }

    private void setPort(int port) {
        if(port < 0 || port > 65535) {
            throw new IllegalArgumentException("invalid port (" + port + ")");
        }
        this.port = port;
    }

    public ObjectInputStream getIn() {
        return in;
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    public boolean isConnected() {
        return connected;
    }
}
