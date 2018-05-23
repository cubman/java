package com.server;

import com.teminal.MainWindow;
import com.teminal.Terminal;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        MainWindow jFrame = new MainWindow();
        jFrame.setThisFrame(jFrame);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }
}
