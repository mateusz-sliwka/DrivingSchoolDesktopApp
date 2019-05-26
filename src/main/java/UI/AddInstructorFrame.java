package UI;

import javax.swing.*;
import java.awt.*;

public class AddInstructorFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    AddInstructorPanel panel;

    public AddInstructorFrame(InstruktorzyPanel panel2) {

        super("Nowe konto kursanta");
        panel = new AddInstructorPanel(panel2);
        this.setSize(new Dimension(280, 400));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(panel);
        this.setVisible(true);
    }
}