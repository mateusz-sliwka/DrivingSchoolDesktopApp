package UI;

import Entities.InstruktorzyEntity;

import javax.swing.*;
import java.awt.*;

public class EditReservationFrame extends JFrame {
    private static final long serialVersionUID = 1L;


    public EditReservationFrame(long ID,long flaga, ReservationPanel reservationPanel, InstruktorzyEntity instructor) {
        super("Edycja rezerwacji");
        EditReservationPanel panel = new EditReservationPanel(ID, flaga,reservationPanel,instructor);
        this.setSize(new Dimension(280, 280));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(panel);
        this.setVisible(true);
    }
}