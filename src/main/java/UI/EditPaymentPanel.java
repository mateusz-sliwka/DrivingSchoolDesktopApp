package UI;

import Controlers.KursanciControler;
import Controlers.PlatnosciControler;
import Controlers.UslugiControler;
import Entities.KursanciEntity;
import Entities.PlatnosciEntity;
import Entities.UslugiEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

class EditPaymentPanel extends JPanel implements ActionListener {

    UslugiControler uc = new UslugiControler();
    private static final long serialVersionUID = 1;


    JButton register = new JButton("Aktualizuj");
    JButton cancel = new JButton("Anuluj");
    JLabel nazwa = new JLabel("Kwota: ");
    JTextField nazwaField = new JTextField(20);
    JLabel kursantLabel = new JLabel("Kursant: ");
    JComboBox<String> kursant = new JComboBox<>();
    PaymentPanel p1;
    PlatnosciEntity current;


    EditPaymentPanel(long ID, PaymentPanel p1) {
        PlatnosciControler pc = new PlatnosciControler();

        this.p1 = p1;
        current = pc.getByID(ID);
        register.addActionListener(this);
        cancel.addActionListener(this);
        nazwaField.setText(String.valueOf(current.getKwota()));
        KursanciControler kc = new KursanciControler();
        List<KursanciEntity> kursanci = kc.getAll();
        for (KursanciEntity k : kursanci) {
            kursant.addItem(k.getImieNazwisko());
            if(k.getKursantId()==current.getKursantId()){
                kursant.setSelectedIndex(kursant.getItemCount()-1);

            }


        }



        this.add(nazwa);
        this.add(nazwaField);
        this.add(kursantLabel);
        this.add(kursant);
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
            PlatnosciControler pc = new PlatnosciControler();
            KursanciControler kc = new KursanciControler();
            PlatnosciEntity pe = new PlatnosciEntity();
            pe.setKwota(Integer.parseInt(nazwaField.getText()));
            pe.setKursantId(kc.getByFS((String)kursant.getSelectedItem()).getKursantId());
            pc.update(current.getPlatnoscId(),pe);
            JOptionPane.showMessageDialog(this, "Platnosc zostala zaktualizowana");
            Window win = SwingUtilities.getWindowAncestor(this);
            p1.refreshList();
            ((Window) win).dispose();
        }

    }

}