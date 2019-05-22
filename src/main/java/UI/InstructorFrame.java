package UI;

import Entities.InstruktorzyEntity;
import Entities.KursanciEntity;

import javax.swing.*;
import java.awt.*;

public class InstructorFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    InstruktorzyEntity current;

    public InstructorFrame(InstruktorzyEntity current) {
        super("Panel instruktora");
        this.current = current;
        this.setSize(new Dimension(800, 500));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        InstructorPanel panel = new InstructorPanel(current);
        this.setContentPane(panel);
        this.setVisible(true);
    }
}