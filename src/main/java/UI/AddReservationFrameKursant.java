package UI;

import Entities.KursanciEntity;

import javax.print.attribute.standard.JobMessageFromOperator;
import javax.swing.*;
import java.awt.*;

public class AddReservationFrameKursant extends JFrame {
    private static final long serialVersionUID = 1L;
    ReservationPanel current;


    public AddReservationFrameKursant(RezerwacjeKursantPanel prev, KursanciEntity kursant) {

        super("Nowa rezerwacja");
        JPanel panel = new AddReservationPanelKursant(prev,kursant);
        this.setSize(new Dimension(280, 400));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(panel);
        this.setVisible(true);
    }
}
