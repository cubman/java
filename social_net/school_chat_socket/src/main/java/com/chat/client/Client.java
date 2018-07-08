package com.chat.client;

import com.chat.server.SchoolServer;

import javax.xml.ws.Service;
import java.io.*;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client implements Runnable {

    Socket socket;
    SchoolServer server;

    public Client(Socket socket, SchoolServer server) {
        this.socket = socket;
        this.server = server;
    }

    private String[] parseMessage(String message) {
        int index = message.indexOf(" ");

        if (index != -1) {
            String[] strings = new String[2];
            strings[0] = message.substring(0, index);
            strings[1] = message.substring(index + 1);
            return strings;
        } else
            return new String[0];
    }

    @Override
    public void run() {
        try (
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));

                BufferedWriter bufferedWriter = new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream()));
        ) {

            SchoolServer.sendToSocket("Enter user name", bufferedWriter);

            String name = null;

            while (socket.isConnected()) {
                name = bufferedReader.readLine();

                if (!server.addUser(name, socket, bufferedWriter, bufferedReader)) {
                    SchoolServer.sendToSocket("User \"" + name + "\" has already registred", bufferedWriter);
                    socket.close();
                    return;
                } else {
                    SchoolServer.sendToSocket("You successfuly registred", bufferedWriter);
                    server.sendAll(name);
                    break;
                }
            }

            SchoolServer.sendToSocket("You can send messages", bufferedWriter);

            while (socket.isConnected()) {
                String line = bufferedReader.readLine();
                System.out.println("Got meassge = " + line);

                String[] packet = parseMessage(line);

                if (packet.length == 2) {
                    if (server.containsUser(packet[0])) {
                        server.sendMessage(packet[0], name + " >> " + packet[1]);
                    } else {
                        System.out.println("Message: " + packet[0] + ", " + packet[1]);
                        SchoolServer.sendToSocket("User \"" + packet[0] + "\" was not registred yet", bufferedWriter);
                    }
                } else
                    SchoolServer.sendToSocket("Wrong message parse, length = " + packet.length, bufferedWriter);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
