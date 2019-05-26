package UI;

import javax.swing.*;
import java.awt.*;

public class EditReservationFrame extends JFrame {
    private static final long serialVersionUID = 1L;


    public EditReservationFrame(long ID, long flaga) {
        super("Edycja rezerwacji");
        EditReservationPanel panel = new EditReservationPanel(ID,flaga);
        this.setSize(new Dimension(280, 400));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(panel);
        this.setVisible(true);
    }
}