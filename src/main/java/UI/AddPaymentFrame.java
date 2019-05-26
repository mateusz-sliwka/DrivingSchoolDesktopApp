package UI;

import javax.swing.*;
import java.awt.*;

public class AddPaymentFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    AddPaymentPanel panel;

    public AddPaymentFrame(PaymentPanel panel2) {

        super("Nowa wpłata");
        panel = new AddPaymentPanel(panel2);
        this.setSize(new Dimension(280, 400));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(panel);
        this.setVisible(true);
    }
}