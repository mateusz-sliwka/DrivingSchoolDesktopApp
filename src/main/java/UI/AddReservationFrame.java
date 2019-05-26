package UI;

import javax.swing.*;
import java.awt.*;

public class AddReservationFrame extends JFrame {
    private static final long serialVersionUID = 1L;


    public AddReservationFrame(long flaga) {
        super("Nowa rezerwacja");
        JPanel panel = new AddReservationPanel(flaga);
        this.setSize(new Dimension(280, 400));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(panel);
        this.setVisible(true);
    }
}