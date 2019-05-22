package UI;

import Entities.KursanciEntity;

import javax.swing.*;

class UserPanel extends JPanel {
    private static final long serialVersionUID = 1;
    private KursanciEntity current;
    JLabel login = new JLabel();

    UserPanel(KursanciEntity current) {
        this.current = current;
        System.out.println(current.getImie());
        login.setText("Witaj: " + current.getImie() + " "+current.getNazwisko());
        this.add(login);
    }
}