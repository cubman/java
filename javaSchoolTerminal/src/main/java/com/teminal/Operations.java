package com.teminal;

import com.exceptions.CardWasNotFound;
import com.exceptions.InsuffienceFunds;
import com.server.Data;
import com.server.Server;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

public class Operations extends JFrame {
    private JPanel panel;
    private JButton buttonAccount;
    private JButton putMoney;
    private JButton withdrawMoney;
    private JTextField textField1;
    private JTextField textField2;
    private JLabel account;
    private JButton button1;
    private String card;
    JFrame mainWindow;

    public Operations(String card, JFrame mainWindow) {
        super("operations");
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();

        this.card = card;
        this.mainWindow = mainWindow;

        buttonAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Server server = Server.getInstance();

                try {
                    Data<String, String, String, BigDecimal> data = server.getDataByCard(card);

                    account.setText(String.format("%.2f руб", data.getMoney().floatValue()));
                } catch (CardWasNotFound cardWasNotFound) {
                    cardWasNotFound.printStackTrace();
                }
            }
        });

        putMoney.addActionListener(e -> {
            BigDecimal bigDecimal = BigDecimal.valueOf(Double.parseDouble(textField1.getText()));

            try {
                Server.getInstance().putMoney(card, bigDecimal);
            } catch (CardWasNotFound cardWasNotFound) {
                JOptionPane.showMessageDialog(null, cardWasNotFound.getMessage());
            }
        });

        withdrawMoney.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BigDecimal bigDecimal = BigDecimal.valueOf(Double.parseDouble(textField1.getText()));

                try {
                    Server.getInstance().withdrawMoney(card, bigDecimal);
                } catch (CardWasNotFound cardWasNotFound) {
                    JOptionPane.showMessageDialog(null, cardWasNotFound.getMessage());
                } catch (InsuffienceFunds insuffienceFunds) {
                    JOptionPane.showMessageDialog(null, insuffienceFunds.getMessage());
                }
            }
        });
        button1.addActionListener(e -> {
            mainWindow.setVisible(true);
            setVisible(false);
            dispose();
        });
    }


}
