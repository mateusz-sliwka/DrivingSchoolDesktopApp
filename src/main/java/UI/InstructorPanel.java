package UI;

import Entities.InstruktorzyEntity;
import Entities.KursanciEntity;

import javax.swing.*;

class InstructorPanel extends JPanel {
    private static final long serialVersionUID = 1;
    private InstruktorzyEntity current;
    JLabel login = new JLabel();

    InstructorPanel(InstruktorzyEntity current) {
        this.current = current;
        System.out.println(current.getImie());
        login.setText("Witaj: " + current.getImie() + " "+current.getNazwisko());
        this.add(login);
    }
}