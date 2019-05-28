package UI;

import Entities.InstruktorzyEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class AddReservationFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    ReservationPanel current;


    public AddReservationFrame(long flaga, ReservationPanel reservationPanel, InstruktorzyEntity instructor) {

        super("Nowa rezerwacja");
        JPanel panel = new AddReservationPanel(flaga, reservationPanel, instructor);
        this.setSize(new Dimension(280, 400));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(panel);
        this.setVisible(true);
    }
}
