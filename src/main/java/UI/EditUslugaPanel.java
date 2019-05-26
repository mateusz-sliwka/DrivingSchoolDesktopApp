package UI;

import Controlers.InstruktorzyControler;
import Controlers.KursanciControler;
import Controlers.RezerwacjeControler;
import Controlers.UslugiControler;
import Entities.InstruktorzyEntity;
import Entities.KursanciEntity;
import Entities.RezerwacjeEntity;
import Entities.UslugiEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

class EditUslugaPanel extends JPanel implements ActionListener {

    UslugiControler uc = new UslugiControler();
    private static final long serialVersionUID = 1;


    JButton register = new JButton("Aktualizuj");
    JButton cancel = new JButton("Anuluj");
    JLabel nazwa = new JLabel("Nazwa: ");
    JTextField nazwaField = new JTextField(20);
    JLabel cena = new JLabel("Cena: ");
    JTextField cenaField = new JTextField(20);
    UslugiPanel p1;
    UslugiEntity current;


    EditUslugaPanel(long ID, UslugiPanel p1) {
        this.p1 = p1;
        current = uc.getByID(ID);
        register.addActionListener(this);
        cancel.addActionListener(this);
        nazwaField.setText(current.getNazwa());
        cenaField.setText(String.valueOf(current.getCena()));

        this.add(nazwa);
        this.add(nazwaField);
        this.add(cena);
        this.add(cenaField);
        this.add(register);
        this.add(cancel);
        this.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == cancel) {
            Window win = SwingUtilities.getWindowAncestor(this);
            ((Window) win).dispose();
        }
        if (source == register) {
            UslugiEntity ue = new UslugiEntity();
            ue.setCena((long) Integer.parseInt(cenaField.getText()));
            ue.setNazwa(nazwaField.getText());
            uc.update(ue, current.getUslugaId());
            JOptionPane.showMessageDialog(this, "Usługa zostala zaktualizowana");
            Window win = SwingUtilities.getWindowAncestor(this);
            p1.refreshList();
            ((Window) win).dispose();
        }

    }

}