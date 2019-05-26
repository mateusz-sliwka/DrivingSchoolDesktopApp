package UI;

import javax.swing.*;
import java.awt.*;

public class EditKursanciFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    EditKursanciPanel panel;

    public EditKursanciFrame(long ID,KursanciPanel panel2) {

        super("Edytuj konto kursanta");
        this.setSize(new Dimension(280, 400));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        panel = new EditKursanciPanel(ID,panel2);
        this.setContentPane(panel);
        this.setVisible(true);
    }
}