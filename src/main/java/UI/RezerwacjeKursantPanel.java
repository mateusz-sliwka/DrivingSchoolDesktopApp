package UI;

import Controlers.InstruktorzyControler;
import Controlers.KursanciControler;
import Controlers.RezerwacjeControler;
import Controlers.UslugiControler;
import Entities.InstruktorzyEntity;
import Entities.KursanciEntity;
import Entities.RezerwacjeEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class RezerwacjeKursantPanel extends JPanel implements ActionListener{


    private static final long serialVersionUID = 1;
    private KursanciEntity current;
    JLabel login = new JLabel();
    JButton refresh = new JButton("Refresh");
    JButton delete = new JButton("Usuń rezerwację");
    JButton addReservation = new JButton("Dodaj rezerwację");
    JButton plik = new JButton("Wygeneruj raport Twoich rezerwacji");
    String[] kolumny = {"ID", "Usługa", "Instruktor", "Data i godzina rezerwacji"};
    JTable rezerwacjeTable = new JTable();
    DefaultTableModel model = new DefaultTableModel(kolumny, 0);
    JScrollPane pane = new JScrollPane(rezerwacjeTable);
    RezerwacjeControler rc = new RezerwacjeControler();

    RezerwacjeKursantPanel(KursanciEntity current) {
        this.current = current;
        login.setText("Witaj " + current.getImie() + " " + current.getNazwisko() + " w zakładce rezerwacji");
        this.add(login);
        plik.addActionListener(this);
        addReservation.addActionListener(this);
        refresh.addActionListener(this);
        rezerwacjeTable.setFillsViewportHeight(true);
        rezerwacjeTable.setModel(model);
        rezerwacjeTable.setAutoCreateRowSorter(true);
        delete.addActionListener(this);
        this.add(pane);
        this.add(refresh);
        this.add(addReservation);
        this.add(delete);
        this.add(plik);
        RefreshTable();

    }

    public void RefreshTable(){
        model.setRowCount(0);
        RezerwacjeControler rc = new RezerwacjeControler();
        List<RezerwacjeEntity> rezerwacje;
        rezerwacje = rc.getByKursant(current.getKursantId());
        for (int i = 0; i < rezerwacje.size(); i++) {
            System.out.println(rezerwacje.get(i).getInstruktorId());
            String[] zawartosc = {Integer.toString((int) rezerwacje.get(i).getRezerwacjaId()),
                    rezerwacje.get(i).getUslugiByUslugaId().getNazwa(),
                    rezerwacje.get(i).getInstruktorzyByInstruktorId().getImieNazwisko(),
                    rezerwacje.get(i).getDataRezerwacji().toString()+ " g: " +rezerwacje.get(i).getGodzRozpoczecia()+":00"};
            model.addRow(zawartosc);
        }
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source==refresh){
            RefreshTable();
        }
        else if(source==delete){
            long ID = Integer.parseInt((String) rezerwacjeTable.getValueAt(rezerwacjeTable.getSelectedRow(), 0));
            rc.deleteByID(ID);
            RefreshTable();
        }
        else if(source==addReservation){
            new AddReservationFrameKursant(this, current);
        }
        else if(source==plik){
            try {
                String nazwa = JOptionPane.showInputDialog("Podaj nazwe pliku: ");
                String data1 = JOptionPane.showInputDialog("Od jakiej daty (yyyy-mm-dd)");
                String data2 = JOptionPane.showInputDialog("Do jakiej daty (yyyy-mm-dd)");
                Date date1 = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(data1).getTime());
                Date date2 = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(data2).getTime());
                System.out.println(date1);
                System.out.println(date2);
                RezerwacjeControler rc = new RezerwacjeControler();
                List<RezerwacjeEntity> rezerwacje = rc.getByKursant(current.getKursantId());
                FileWriter fw = new FileWriter(new File(nazwa + ".txt"));
                fw.write("Raport dla Kursanta " + current.getImieNazwisko() + " za okres od " + date1.toString() + " do " + date2.toString()
                        + "\nZrealizowane zajęcia: " + String.valueOf(rezerwacje.size())
                        + "\nRezerwacje: ");
                for (RezerwacjeEntity re : rezerwacje) {
                    if (re.getDataDodania().compareTo(date1) >= 0 & re.getDataDodania().compareTo(date2) <= 0)
                        fw.write(re.doRaportu());
                    System.out.println(date1);
                    System.out.println(date2);
                    System.out.println(re.getDataDodania());
                }
                fw.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (ParseException e1) {
                e1.printStackTrace();
            }

        }

    }
}
