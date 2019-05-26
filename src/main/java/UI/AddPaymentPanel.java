package UI;

import Controlers.KursanciControler;
import Controlers.PlatnosciControler;
import Entities.KursanciEntity;
import Entities.PlatnosciEntity;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

class AddPaymentPanel extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1;
    JLabel kwotaLabel = new JLabel("Podaj kwotę:");
    JTextField kwota = new JTextField(20);
    JLabel kursantLabel = new JLabel("Wybierz kursanta");
    JComboBox<String> kursant = new JComboBox<String>();
    JLabel komunikat = new JLabel("<html><font color='red'>Podaj wszystkie dane.</html>");
    JButton register = new JButton("Zarejestruj");
    JButton cancel = new JButton("Anuluj");
    PaymentPanel panel;

    AddPaymentPanel(PaymentPanel panel) {
        this.panel = panel;
        register.addActionListener(this);
        cancel.addActionListener(this);
        KursanciControler kc = new KursanciControler();
        List<KursanciEntity> kursanci = kc.getAll();
        for (KursanciEntity k : kursanci) {
            kursant.addItem(k.getImieNazwisko());
        }
        this.add(kwotaLabel);
        this.add(kwota);
        this.add(kursantLabel);
        this.add(kursant);
        this.add(register);
        this.add(cancel);
        this.add(komunikat);
        komunikat.setVisible(false);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == register) {
            KursanciControler kc = new KursanciControler();
            PlatnosciEntity pe = new PlatnosciEntity();
            PlatnosciControler pc = new PlatnosciControler();
            pe.setKwota(Integer.parseInt(kwota.getText()));
            pe.setKursantId(kc.getByFS(kursant.getSelectedItem().toString()).getKursantId());
            pe.setDataPlatnosci(new Date(System.currentTimeMillis()));
            pc.add(pe);
            JOptionPane.showMessageDialog(this, "Płatność została dodana!");
            panel.refreshList();
            Window win = SwingUtilities.getWindowAncestor(this);
            ((Window) win).dispose();
        }


    }

}

