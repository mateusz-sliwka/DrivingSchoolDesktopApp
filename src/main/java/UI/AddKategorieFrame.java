package UI;

import UI.AddPaymentPanel;
import UI.PaymentPanel;

import javax.swing.*;
import java.awt.*;

public class AddKategorieFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    AddKategoriePanel panel;

    public AddKategorieFrame(KategoriePanel panel2) {

        super("Nowe prawa instuktora");
        panel = new AddKategoriePanel(panel2);
        this.setSize(new Dimension(280, 400));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(panel);
        this.setVisible(true);
    }
}