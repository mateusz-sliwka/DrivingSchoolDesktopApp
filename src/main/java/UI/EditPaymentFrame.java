package UI;

import javax.swing.*;
import java.awt.*;

public class EditPaymentFrame extends JFrame {
    private static final long serialVersionUID = 1L;


    public EditPaymentFrame(long ID, PaymentPanel p1) {
        super("Edycja usługia");
        EditPaymentPanel panel = new EditPaymentPanel(ID, p1);
        this.setSize(new Dimension(280, 180));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(panel);
        this.setVisible(true);
    }
}