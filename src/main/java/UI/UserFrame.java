package UI;

import Entities.KursanciEntity;

import javax.swing.*;
import java.awt.*;

public class UserFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    KursanciEntity current;

    public UserFrame(KursanciEntity current) {
        super("Panel kursanta");
        this.current = current;
        this.setSize(new Dimension(800, 500));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        UserPanel panel = new UserPanel(current);
        this.setContentPane(panel);
        this.setVisible(true);
    }
}