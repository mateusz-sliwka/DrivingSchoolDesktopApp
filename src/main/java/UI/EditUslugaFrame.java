package UI;

import javax.swing.*;
import java.awt.*;

public class EditUslugaFrame extends JFrame {
    private static final long serialVersionUID = 1L;


    public EditUslugaFrame(long ID, UslugiPanel p1) {
        super("Edycja usługi");
        EditUslugaPanel panel = new EditUslugaPanel(ID, p1);
        this.setSize(new Dimension(280, 400));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(panel);
        this.setVisible(true);
    }
}