package UI;

import javax.swing.*;
import java.awt.*;

public class EditInstruktorFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    EditInstruktorPanel panel;

    public EditInstruktorFrame(long ID, InstruktorzyPanel panel2) {

        super("Edytuj konto kursanta");
        this.setSize(new Dimension(280, 400));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        panel = new EditInstruktorPanel(ID,panel2);
        this.setContentPane(panel);
        this.setVisible(true);
    }
}