package UI;

import javax.swing.*;
import java.awt.*;

public class EditInstructorFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    EditInstructorPanel panel;

    public EditInstructorFrame(long ID, InstruktorzyPanel panel2) {

        super("Edytuj konto kursanta");
        this.setSize(new Dimension(280, 280));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        panel = new EditInstructorPanel(ID, panel2);
        this.setContentPane(panel);
        this.setVisible(true);
    }
}