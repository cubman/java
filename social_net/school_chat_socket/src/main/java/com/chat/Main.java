package com.chat;

import com.chat.server.SchoolServer;

public class Main {

    public static void main(String[] args) {
        SchoolServer server = new SchoolServer();

        System.out.println("Приложение стартовало");
        Thread thread = new Thread(server);
        thread.start();
    }
}
