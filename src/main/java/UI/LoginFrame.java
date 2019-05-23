package UI;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    LoginPanel panel = new LoginPanel();

    public LoginFrame() {
        super("Logowanie");
        this.setSize(new Dimension(280, 280));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(panel);
        this.setVisible(true);
    }
}