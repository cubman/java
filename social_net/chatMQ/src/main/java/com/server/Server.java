package com.server;

import com.client.Client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {

    private static final int PORT = 8000;

    ConcurrentHashMap<String,  SocketData> data = new ConcurrentHashMap<>();

    public Server() {

    }

    public void run() {
        try(
                ServerSocket serverSocket = new ServerSocket(PORT);
        )
        {
            System.out.println(String.format("Application started, and listening port: %d", PORT));
            System.out.println("Wait connect...");
            ExecutorService pool = Executors.newFixedThreadPool(10);

            while (true) {
                pool.execute(new Client(serverSocket.accept(), this));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendToSocket(String message, BufferedWriter bufferedWriter) {
        try {
            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendAll(String user) {
        Iterator<Map.Entry<String, SocketData>> iterator = data.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, SocketData> element = iterator.next();

            if(element.getKey().equals(user))
                continue;

            sendToSocket("System: " + user + " added ", element.getValue().getBufferedWriter());
        }
    }

    public boolean addUser(String user, Socket socket, BufferedWriter bufferedWriter, BufferedReader bufferedReader) {
        if(data.containsKey(user)) {
            return false;
        } else {
            data.put(user, new SocketData(socket, bufferedReader, bufferedWriter));
        }

        return true;
    }

    public boolean containsUser(String name) {
        return data.containsKey(name);
    }

    public void sendMessage(String sendTo, String mesage) {
        if(containsUser(sendTo)) {
            sendToSocket(mesage, data.get(sendTo).getBufferedWriter());
        }
    }

    class SocketData {
        private Socket socket;
        private BufferedWriter bufferedWriter;
        private BufferedReader bufferedReader;

        public SocketData(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
            this.socket = socket;
            this.bufferedWriter = bufferedWriter;
            this.bufferedReader = bufferedReader;
        }

        public Socket getSocket() {
            return socket;
        }

        public BufferedWriter getBufferedWriter() {
            return bufferedWriter;
        }

        public BufferedReader getBufferedReader() {
            return bufferedReader;
        }
    }
}
