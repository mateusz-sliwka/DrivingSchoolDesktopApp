package UI;

import javax.swing.*;
import java.awt.*;

public class AddCategoryToInstructorFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    AddCategoryToInstructorPanel panel;

    public AddCategoryToInstructorFrame(long ID,InstruktorzyPanel panel2) {

        super("Nowe konto kursanta");
        panel = new AddCategoryToInstructorPanel(ID, panel2);
        this.setSize(new Dimension(280, 400));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(panel);
        this.setVisible(true);
    }
}