package UI;

import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    RegisterPanel panel = new RegisterPanel();

    public RegisterFrame() {
        super("Nowe konto kursanta");
        this.setSize(new Dimension(300, 400));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(panel);
        this.setVisible(true);
    }
}