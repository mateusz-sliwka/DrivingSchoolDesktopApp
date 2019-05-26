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

class EditReservationPanel extends JPanel implements ActionListener {
    InstruktorzyControler ic = new InstruktorzyControler();
    RezerwacjeControler rc = new RezerwacjeControler();
    KursanciControler kc = new KursanciControler();
    UslugiControler uc = new UslugiControler();
    private static final long serialVersionUID = 1;
    JLabel kursantLabel = new JLabel("Wybierz kursanta:");
    JComboBox<String> kursantBox = new JComboBox();
    JLabel instruktorLabel = new JLabel("Wybierz instruktora:");
    JComboBox<String> instruktorBox = new JComboBox();
    JLabel uslugaLabel = new JLabel("Wybierz usługę:");
    JComboBox<String> uslugaBox = new JComboBox();
    JButton register = new JButton("Aktualizuj");
    JButton cancel = new JButton("Anuluj");
    RezerwacjeEntity current;


    EditReservationPanel(long ID,long flaga) {
        current = rc.getByID(ID);
        List<KursanciEntity> lista = kc.getAll();
        for(int i=0;i<lista.size();i++)
        kursantBox.addItem(lista.get(i).getImieNazwisko());
        List<UslugiEntity> lista2 = uc.getAll();
        for(int i=0;i<lista2.size();i++)
            uslugaBox.addItem(lista2.get(i).getNazwa());
        List<InstruktorzyEntity> lista3 = ic.getAll();
        for(int i=0;i<lista3.size();i++)
            instruktorBox.addItem(lista3.get(i).getImieNazwisko());
        register.addActionListener(this);
        cancel.addActionListener(this);
        instruktorBox.setSelectedItem(ic.getByID(current.getInstruktorId()).getImieNazwisko());
        kursantBox.setSelectedItem(kc.getByID(current.getKursantId()).getImieNazwisko());
        uslugaBox.setSelectedItem(uc.getByID(current.getUslugaId()).getNazwa());
        this.add(kursantLabel);
        this.add(kursantBox);
        this.add(uslugaLabel);
        this.add(uslugaBox);
        if(flaga==1) {
            this.add(instruktorLabel);
            this.add(instruktorBox);
        }
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
        if(source==register){
            RezerwacjeEntity re = new RezerwacjeEntity();
            re.setInstruktorId(ic.getByFS((String)instruktorBox.getSelectedItem()).getInstruktorId());
            re.setKursantId(kc.getByFS((String)kursantBox.getSelectedItem()).getKursantId());
            re.setUslugaId(uc.getByName((String)uslugaBox.getSelectedItem()).getUslugaId());
            re.setDataDodania(new Date(System.currentTimeMillis()));
            rc.update(re,current.getRezerwacjaId());
            JOptionPane.showMessageDialog(this,"Rezerwacja zostala zaktualizowana");
            Window win = SwingUtilities.getWindowAncestor(this);
            ((Window) win).dispose();
        }

    }

}