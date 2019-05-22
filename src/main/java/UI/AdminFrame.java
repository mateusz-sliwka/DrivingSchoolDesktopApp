package UI;

import Entities.InstruktorzyEntity;

import javax.swing.*;
import java.awt.*;

public class AdminFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    InstruktorzyEntity current;

    public AdminFrame(InstruktorzyEntity current) {
        super("Panel administratora");
        this.current = current;
        this.setSize(new Dimension(800, 500));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        AdminPanel panel = new AdminPanel(current);
        this.setContentPane(panel);
        this.setVisible(true);
    }
}