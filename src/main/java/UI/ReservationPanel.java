package UI;

import Controlers.InstruktorzyControler;
import Controlers.KursanciControler;
import Controlers.RezerwacjeControler;
import Controlers.UslugiControler;
import Entities.InstruktorzyEntity;
import Entities.RezerwacjeEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ReservationPanel extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1;
    private InstruktorzyEntity current;
    JLabel login = new JLabel("Zarządzaj rezerwacjami");
    JMenuBar pasek = new JMenuBar();
    JButton refresh = new JButton("Refresh");
    JButton delete = new JButton("Usuń pozycję");
    JButton edit = new JButton("Edytuj pozycje");
    JButton plik = new JButton("Wygeneruj raport Twoich zajęć");
    JMenuItem menu = new JMenuItem("Zarządzaj rezerwacjami");
    String[] kolumny = {"ID", "Usługa", "Kategoria", "Kursant", "Instruktor", "Godzina rezerwacji","Data rezerwacji"};
    JTable rezerwacjeTable = new JTable();
    JButton add = new JButton("Dodaj rezerwacje");
    DefaultTableModel model = new DefaultTableModel(kolumny, 0);
    JScrollPane pane = new JScrollPane(rezerwacjeTable);
    InstruktorzyControler ic = new InstruktorzyControler();
    RezerwacjeControler rc = new RezerwacjeControler();
    KursanciControler kc = new KursanciControler();
    UslugiControler uc = new UslugiControler();

    public void refreshList() {
        model.setRowCount(0);
        RezerwacjeControler rc2 = new RezerwacjeControler();
        uc = new UslugiControler();
        ic = new InstruktorzyControler();
        kc = new KursanciControler();
        List<RezerwacjeEntity> rezerwacje;
        if (current.getCzyAdmin() == 1)
            rezerwacje = rc2.getAll();
        else
            rezerwacje = rc2.getByInstruktor(current.getInstruktorId());
        for (int i = 0; i < rezerwacje.size(); i++) {
            System.out.println(rezerwacje.get(i).getInstruktorId());
            String[] zawartosc = {Integer.toString((int) rezerwacje.get(i).getRezerwacjaId()),
                    rezerwacje.get(i).getUslugiByUslugaId().getNazwa(),
                    rezerwacje.get(i).getKategorieByKategoriaId().getSymbol(),
                    rezerwacje.get(i).getKursanciByKursantId().getImieNazwisko(),
                    rezerwacje.get(i).getInstruktorzyByInstruktorId().getImieNazwisko(),
                    rezerwacje.get(i).getGodzRozpoczecia(),
                    (rezerwacje.get(i).getDataRezerwacji().toString())};
            model.addRow(zawartosc);
        }
    }

    ReservationPanel(InstruktorzyEntity current) {
        edit.addActionListener(this);
        plik.addActionListener(this);
        add.addActionListener(this);
        refresh.addActionListener(this);
        rezerwacjeTable.setFillsViewportHeight(true);
        rezerwacjeTable.setModel(model);
        rezerwacjeTable.setAutoCreateRowSorter(true);
        pasek.add(menu);
        System.out.println(current.getImie());
        delete.addActionListener(this);
        this.current = current;
        this.add(login);
        this.add(pane);
        this.add(refresh);
        this.add(add);
        this.add(edit);
        this.add(delete);
        this.add(plik);

        if (current.getCzyAdmin() == 0) {
            delete.setVisible(false);
        }
        refreshList();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();
        if (source == refresh) {
            refreshList();
        }
        if (source == plik) {

            try {
                String nazwa = JOptionPane.showInputDialog("Podaj nazwe pliku: ");
                String data1 = JOptionPane.showInputDialog("Od jakiej daty (yyyy-mm-dd)");
                String data2 = JOptionPane.showInputDialog("Do jakiej daty (yyyy-mm-dd)");
                Date date1 = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(data1).getTime());
                Date date2 = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(data2).getTime());
                System.out.println(date1);
                System.out.println(date2);
                RezerwacjeControler rc = new RezerwacjeControler();
                List<RezerwacjeEntity> rezerwacje = ( List<RezerwacjeEntity>) current.getRezerwacjesByInstruktorId();
                FileWriter fw = new FileWriter(new File(nazwa + ".txt"));
                fw.write("Raport dla instruktora " + current.getImieNazwisko() + " za okres od " + date1.toString() + " do " + date2.toString()
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
        if (source == delete) {
            if(!rezerwacjeTable.getSelectionModel().isSelectionEmpty()){
            long ID = Integer.parseInt((String) rezerwacjeTable.getValueAt(rezerwacjeTable.getSelectedRow(), 0));
            rc.deleteByID(ID);
            refreshList(); }  else
            JOptionPane.showMessageDialog(this,"Wybierz ktorys wiersz");
        }
        if (source == edit) {
            if(!rezerwacjeTable.getSelectionModel().isSelectionEmpty()){
            long ID = Integer.parseInt((String) rezerwacjeTable.getValueAt(rezerwacjeTable.getSelectedRow(), 0));
            new EditReservationFrame(ID, current.getCzyAdmin(),this,current); }  else
            JOptionPane.showMessageDialog(this,"Wybierz ktorys wiersz");
        }
        if (source == add) {
            new AddReservationFrame(current.getCzyAdmin(), this, current);
        }
    }

}