package UI;

import Controlers.*;
import Entities.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class AddReservationPanel extends JPanel implements ActionListener, ItemListener {
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
    JComboBox<String> rok = new JComboBox<>();
    JComboBox<String> miesiac = new JComboBox<>();
    JComboBox<String> dzien = new JComboBox<>();
    JComboBox<String> kategoria = new JComboBox<>();
    JComboBox<String> godzina = new JComboBox<>();
    JComboBox<String> uslugaBox = new JComboBox();
    JButton register = new JButton("Dodaj");
    JButton cancel = new JButton("Anuluj");
    ReservationPanel current;
    InstruktorzyEntity instructor;
    long flaga = 0;


    boolean sprawdzCzyMaKategorie(String symbol, InstruktorzyEntity instructor2) {
        List<KategorieInstruktorowEntity> lista = (List<KategorieInstruktorowEntity>) instructor2.getKategorieInstruktorowsByInstruktorId();
        KategorieControler kc = new KategorieControler();
        for (int i = 0; i < lista.size(); i++)
            if (symbol.equals(kc.getByID(lista.get(i).getKategoriaId()).getSymbol()))
                return true;
        return false;
    }

    AddReservationPanel(long flaga, ReservationPanel reservationPanel, InstruktorzyEntity instructor) {
        this.flaga = flaga;
        kategoria.addItemListener(this);
        kategoria.addActionListener(this);
        this.instructor = instructor;
        current = reservationPanel;
        List<KursanciEntity> lista = kc.getAll();
        for (int i = 0; i < lista.size(); i++)
            kursantBox.addItem(lista.get(i).getImieNazwisko());
        List<UslugiEntity> lista2 = uc.getAll();
        for (int i = 0; i < lista2.size(); i++)
            uslugaBox.addItem(lista2.get(i).getNazwa());
        List<InstruktorzyEntity> lista3 = ic.getAll();

        register.addActionListener(this);
        cancel.addActionListener(this);
        this.add(kursantLabel);
        this.add(kursantBox);
        this.add(uslugaLabel);
        this.add(uslugaBox);


        this.add(new JLabel("Wybierz kategorie: "));
        KategorieControler kc = new KategorieControler();
        List<KategorieEntity> kategorie = kc.getAll();
        for (int i = 0; i < kategorie.size(); i++) {
            if (flaga == 1)
                kategoria.addItem(kategorie.get(i).getSymbol());
            else if (sprawdzCzyMaKategorie(kategorie.get(i).getSymbol(), instructor))
                kategoria.addItem(kategorie.get(i).getSymbol());
        }
        this.add(kategoria);
        KategorieControler kic = new KategorieControler();
        List<KategorieInstruktorowEntity> kic2 = (List<KategorieInstruktorowEntity>) kic.getByID(kic.getIDbyname(kategoria.getSelectedItem().toString()))
                .getKategorieInstruktorowsByKategoriaId();
        this.add(instruktorLabel);
        this.add(instruktorBox);
        for (int i = 1; i < 32; i++) {
            if (i < 10)
                dzien.addItem("0" + i);
            else
                dzien.addItem(String.valueOf(i));
        }
        for (int i = 1; i < 13; i++)
            if (i < 10)
                miesiac.addItem("0" + i);
            else
                miesiac.addItem(String.valueOf(i));
        for (int i = 2019; i < 2022; i++)
            rok.addItem(String.valueOf(i));
        this.add(new JLabel("Wybierz date Twojej nowej rezerwacji: "));
        this.add(dzien);
        this.add(miesiac);
        this.add(rok);
        this.add(new JLabel("Wybierz godzine rozpoczecia: "));
        int h = 6;
        for (int i = 0; i < 17; i++) {
            godzina.addItem(String.valueOf(h + i));
        }
        this.add(godzina);
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
            RezerwacjeEntity re = new RezerwacjeEntity();
            KategorieControler kc2 = new KategorieControler();
            re.setInstruktorId(ic.getByFS((String) instruktorBox.getSelectedItem()).getInstruktorId());
            re.setKursantId(kc.getByFS((String) kursantBox.getSelectedItem()).getKursantId());
            re.setUslugaId(uc.getByName((String) uslugaBox.getSelectedItem()).getUslugaId());
            re.setDataDodania(new Date(System.currentTimeMillis()));
            re.setGodzRozpoczecia(godzina.getSelectedItem().toString());
            re.setKategoriaId(kc2.getIDbyname(kategoria.getSelectedItem().toString()));
            String datastringowa = rok.getSelectedItem().toString() + "-" + miesiac.getSelectedItem().toString() + "-" + dzien.getSelectedItem().toString();
            Date data = java.sql.Date.valueOf(datastringowa);
            re.setDataRezerwacji(data);
            rc.add(re);
            JOptionPane.showMessageDialog(this, "Rezerwacja zostala dodana");
            current.refreshList();
            Window win = SwingUtilities.getWindowAncestor(this);
            ((Window) win).dispose();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        Object source = e.getSource();
        if (source == kategoria && flaga == 1) {
            KategorieControler kc = new KategorieControler();
            instruktorBox.removeAllItems();
            KategorieEntity ke = kc.getByID(kc.getIDbyname(kategoria.getSelectedItem().toString()));
            List<KategorieInstruktorowEntity> lista = (List<KategorieInstruktorowEntity>) ke.getKategorieInstruktorowsByKategoriaId();
            for (int i = 0; i < lista.size(); i++)
                instruktorBox.addItem(lista.get(i).getInstruktorzyByInstructorId().getImieNazwisko());
        }

    }
}


