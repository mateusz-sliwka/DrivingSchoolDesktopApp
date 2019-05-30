package UI;

import Controlers.*;
import Entities.*;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;

public class AddReservationPanelKursant extends JPanel implements ActionListener {


    InstruktorzyControler ic = new InstruktorzyControler();
    RezerwacjeControler rc = new RezerwacjeControler();
    KursanciControler kc = new KursanciControler();
    UslugiControler uc = new UslugiControler();
    private static final long serialVersionUID = 1;
    JLabel instruktorLabel = new JLabel("Wybierz instruktora:");
    JComboBox<String> instruktorBox = new JComboBox();
    JLabel uslugaLabel = new JLabel("Wybierz usługę:");
    JComboBox<String> uslugaBox = new JComboBox();
    JComboBox<String> kategoria = new JComboBox<>();
    JComboBox<String> rok = new JComboBox<>();
    JComboBox<String> miesiac = new JComboBox<>();
    JComboBox<String> dzien = new JComboBox<>();
    JComboBox<String> godzina = new JComboBox<>();
    JButton register = new JButton("Dodaj");
    JButton cancel = new JButton("Anuluj");
    RezerwacjeKursantPanel current;
    Long kursantID;

    boolean sprawdzCzyMaKategorie(String symbol, InstruktorzyEntity instructor2) {
        List<KategorieInstruktorowEntity> lista = (List<KategorieInstruktorowEntity>) instructor2.getKategorieInstruktorowsByInstruktorId();
        KategorieControler kc2 = new KategorieControler();
        for (int i = 0; i < lista.size(); i++)
            if (symbol.equals(kc2.getByID(lista.get(i).getKategoriaId()).getSymbol()))
                return true;
        return false;
    }

    boolean sprawdzCzyWolnyTermin(Date data, String godzina, InstruktorzyEntity instruktor){
        List<RezerwacjeEntity> rinstruktora=rc.getByInstruktor(instruktor.getInstruktorId()); //lista rezerwacji tego instruktora
        List<Date>datyzajete= new ArrayList<>();
        //System.out.println(data.toString()+"godzina: "+godzina);
        for(int i=0; i<rinstruktora.size();i++)
        {
            datyzajete.add(rinstruktora.get(i).getDataRezerwacji());
           // System.out.println(datyzajete.get(i));
        }
        List<String>godzinyzajete= new ArrayList<>();
        for(int i=0; i<rinstruktora.size();i++)
        {
            godzinyzajete.add(rinstruktora.get(i).getGodzRozpoczecia());
            //System.out.println(godzinyzajete.get(i));
        }

        for(int i=0; i<datyzajete.size();i++)
        {
            if(data.equals(datyzajete.get(i)))
                if(godzina.equals(godzinyzajete.get(i))) return false;
        }

        return true;}

    AddReservationPanelKursant( RezerwacjeKursantPanel prev, KursanciEntity kursant) {
        current=prev;
        kursantID=kursant.getKursantId();
        List<UslugiEntity> lista2 = uc.getAll();
        for (int i = 0; i < lista2.size(); i++)
            uslugaBox.addItem(lista2.get(i).getNazwa());
        List<InstruktorzyEntity> lista3 = ic.getAll();
        for (int i = 0; i < lista3.size(); i++)
            instruktorBox.addItem(lista3.get(i).getImieNazwisko());

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


        int h = 6;
        for (int i = 0; i < 17; i++) {
            godzina.addItem(String.valueOf(h + i));
        }


        register.addActionListener(this);
        cancel.addActionListener(this);
        this.add(instruktorLabel);
        this.add(instruktorBox);
        this.add(uslugaLabel);
        this.add(uslugaBox);
        this.add(new JLabel("Wybierz kategorie: "));
        KategorieControler kc3 = new KategorieControler();
        List<KategorieEntity> kategorie = kc3.getAll();
        for (int i = 0; i < kategorie.size(); i++) {
            if(sprawdzCzyMaKategorie(kategorie.get(i).getSymbol(), ic.getByFS((String) instruktorBox.getSelectedItem())))
                kategoria.addItem(kategorie.get(i).getSymbol());
        }
        this.add(kategoria);
        this.add(new JLabel("Wybierz date Twojej nowej rezerwacji: "));
        this.add(dzien);
        this.add(miesiac);
        this.add(rok);
        this.add(new JLabel("Wybierz godzine rozpoczecia: "));
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
            KategorieControler kc2 = new KategorieControler();
            RezerwacjeEntity re = new RezerwacjeEntity();
            re.setInstruktorId(ic.getByFS((String) instruktorBox.getSelectedItem()).getInstruktorId());
            re.setKursantId(kursantID);
            re.setKategoriaId((kc2.getIDbyname(kategoria.getSelectedItem().toString())));
            re.setUslugaId(uc.getByName((String) uslugaBox.getSelectedItem()).getUslugaId());
            re.setDataDodania(new Date(System.currentTimeMillis()));
            re.setGodzRozpoczecia(godzina.getSelectedItem().toString());
            String datastringowa = rok.getSelectedItem().toString() + "-" + miesiac.getSelectedItem().toString() + "-" + dzien.getSelectedItem().toString();
            Date data = java.sql.Date.valueOf(datastringowa);
            re.setDataRezerwacji(data);
            if(sprawdzCzyWolnyTermin(data, godzina.getSelectedItem().toString(), ic.getByFS((String)instruktorBox.getSelectedItem()))==true)
            {
                rc.add(re);
                JOptionPane.showMessageDialog(this, "Rezerwacja zostala dodana");
                current.RefreshTable();
                Window win = SwingUtilities.getWindowAncestor(this);
                ((Window) win).dispose();
            }
            else JOptionPane.showMessageDialog(this, "Termin zajety-zmien date/godzine rezerwacji");

        }

    }

}
