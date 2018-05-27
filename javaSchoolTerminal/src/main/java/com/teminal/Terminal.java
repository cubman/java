package com.teminal;

import com.exceptions.CardWasNotFound;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import com.server.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Terminal extends JFrame {
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button9;
    private JButton button8;
    private JButton buttonOut;
    private JButton button0;
    private JButton buttonClear;
    private JButton button7;
    private JLabel password;
    private JPanel panel;
    private JPanel theifPanel;
    private JLabel police;
    private String card;
    private JFrame mainFrame;

    private final String labelText = "Введите пароль";
    public static final int PASSWORD_LENGTH = 4;

    private class Listner implements ActionListener {
        private String text;
        private int errorEnter = 3;

        public Listner(String text) {
            this.text = text;
        }

        public void actionPerformed(ActionEvent e) {

            if (password.getText().equals(labelText))
                password.setText(text);
            else
                password.setText(password.getText() + text);

            if (password.getText().length() >= PASSWORD_LENGTH)
                try {
                    if (password.getText().equals(Server.getInstance().getDataByCard(card).getPassword())) {
                        JFrame jFrame = new Operations(card, mainFrame);
                        jFrame.setLocationRelativeTo(null);
                        jFrame.setVisible(true);
                        setVisible(false);
                        dispose();
                    } else {
                        --errorEnter;

                        if (errorEnter <= 0) {

                            Webcam webcam = Webcam.getDefault();
                            webcam.setViewSize(new Dimension(176, 144));
                            webcam.open();

                            // get image
                            BufferedImage image = webcam.getImage();

                            Graphics g = theifPanel.getGraphics();

                            g.drawImage(image, 0, 0, null);

                            theifPanel.paintComponents(g);
                            webcam.close();

                            police.setText("За Вами выехали...");


                            return;
                        }

                        JOptionPane.showMessageDialog(null, "неверный пароль, повторите попытку(Осталось попыток = " + errorEnter);
                        password.setText(labelText);
                    }
                } catch (CardWasNotFound cardWasNotFound) {
                    JOptionPane.showMessageDialog(null, cardWasNotFound.getMessage());
                }
        }
    }

    public Terminal(JFrame mainWindow, String card) {
        super("terminal " + card);
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();

        mainWindow.setVisible(false);

        mainFrame = mainWindow;
        this.card = card;
        password.setText(labelText);

        button1.addActionListener(new Listner(button1.getText()));
        button2.addActionListener(new Listner(button2.getText()));
        button3.addActionListener(new Listner(button3.getText()));
        button4.addActionListener(new Listner(button4.getText()));
        button5.addActionListener(new Listner(button5.getText()));
        button6.addActionListener(new Listner(button6.getText()));
        button7.addActionListener(new Listner(button7.getText()));
        button8.addActionListener(new Listner(button8.getText()));
        button9.addActionListener(new Listner(button9.getText()));
        button0.addActionListener(new Listner(button0.getText()));


        buttonClear.addActionListener(e -> password.setText(labelText));
        buttonOut.addActionListener(e -> {
            mainFrame.setVisible(true);
            setVisible(false);
            dispose();
        });
    }


}
