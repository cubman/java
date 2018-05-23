package com.teminal;

import com.server.Server;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainWindow extends JFrame {
    private JList list1;
    private JButton start;
    private JPanel panel;
    private JFrame thisFrame;

    public MainWindow() {
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();

        CardList();

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (list1.getSelectedIndex() < 0) {
                    JOptionPane.showMessageDialog(null, "Выберите карту для активации");
                    return;
                }

                JFrame frame = new Terminal(thisFrame, list1.getSelectedValue().toString());
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    private void CardList() {
        Server server =  Server.getInstance();
        DefaultListModel<String> model = new DefaultListModel<>();

        list1.setModel(model);

        List<String> list = server.getAllCards();

        for ( int i = 0; i < list.size(); ++i ){
            model.addElement(list.get(i));
        }
    }

    public void setThisFrame(JFrame jFrame) {
        thisFrame = jFrame;
    }
}
