package UI;

import Entities.KursanciEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class UserPanel extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1;
    private KursanciEntity current;
    JLabel login = new JLabel();


    UserPanel(KursanciEntity current) {
        this.current = current;
//TODO: TUTAJ JEST PANEL POWITALNY USERA
        login.setText("Witaj: " + current.getImie() + " " + current.getNazwisko());
        this.add(login);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

    }
}