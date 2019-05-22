package UI;

import Entities.InstruktorzyEntity;

import javax.swing.*;

class AdminPanel extends JPanel {
    private static final long serialVersionUID = 1;
    private InstruktorzyEntity current;
    JLabel login = new JLabel();

    AdminPanel(InstruktorzyEntity current) {
        this.current = current;
        System.out.println(current.getImie());
        login.setText("Witaj: " + current.getImie() + " "+current.getNazwisko());
        this.add(login);
    }
}