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

class KursanciPanel extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1;
    private InstruktorzyEntity current;
    JLabel login = new JLabel();
    JMenuBar pasek = new JMenuBar();
    JButton refresh = new JButton("Refresh");
    JButton delete = new JButton("Usuń pozycję");
    JButton edit = new JButton("Edytuj pozycje");
    String[] kolumny = {"ID", "Imie", "Nazwisko", "Email", "Pesel", "PKK", "Saldo"};
    JTable kursanciTable = new JTable();
    JButton plik = new JButton("Generuj raport kursanta");
    JButton add = new JButton("Dodaj kursanta");
    DefaultTableModel model = new DefaultTableModel(kolumny, 0);
    JScrollPane pane = new JScrollPane(kursanciTable);
    InstruktorzyControler ic = new InstruktorzyControler();
    RezerwacjeControler rc = new RezerwacjeControler();
    KursanciControler kc = new KursanciControler();
    UslugiControler uc = new UslugiControler();

    public void refreshList() {
        model.setRowCount(0);
        RezerwacjeControler rc2 = new RezerwacjeControler();
        kc = new KursanciControler();
        List<KursanciEntity> kursanci;
        if (current.getCzyAdmin() == 1)
            kursanci = kc.getAll();
        else
            kursanci = kc.getByInstruktor(current.getInstruktorId());
        for (int i = 0; i < kursanci.size(); i++) {
            String[] zawartosc = {Integer.toString((int) kursanci.get(i).getKursantId()),
                    kursanci.get(i).getImie(), kursanci.get(i).getNazwisko(), kursanci.get(i).getEmail(), kursanci.get(i).getPesel(),
                    kursanci.get(i).getPkk(), String.valueOf(kc.getSaldo(kursanci.get(i)))};
            model.addRow(zawartosc);
        }
    }

    KursanciPanel(InstruktorzyEntity current) {
        edit.addActionListener(this);
        add.addActionListener(this);
        refresh.addActionListener(this);
        kursanciTable.setFillsViewportHeight(true);
        kursanciTable.setModel(model);
        kursanciTable.setAutoCreateRowSorter(true);
        System.out.println(current.getImie());
        login.setText("Zarządzanie kursantami");
        delete.addActionListener(this);
        this.current = current;
        this.add(login);
        this.add(pane);
        this.add(refresh);
        if (current.getCzyAdmin() == 1) {

            this.add(edit);
            this.add(delete);
        }
        this.add(add);
        this.add(plik);
        plik.addActionListener(this);
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
        if (source == delete) {
            if(!kursanciTable.getSelectionModel().isSelectionEmpty()){
            long ID = Integer.parseInt((String) kursanciTable.getValueAt(kursanciTable.getSelectedRow(), 0));
            kc.deleteByID(ID);
            refreshList();}
            else
                JOptionPane.showMessageDialog(this,"Wybierz ktorys wiersz");
        }
        if (source == edit) {
            if(!kursanciTable.getSelectionModel().isSelectionEmpty()){
            long ID = Integer.parseInt((String) kursanciTable.getValueAt(kursanciTable.getSelectedRow(), 0));
            new EditKursanciFrame(ID, this);}
            else
                JOptionPane.showMessageDialog(this,"Wybierz ktorys wiersz");
        }
        if (source == add) {
            new RegisterFrame();
            refreshList();
        }

        if (source == plik) {
            if(!kursanciTable.getSelectionModel().isSelectionEmpty()){
            long ID = Integer.parseInt((String) kursanciTable.getValueAt(kursanciTable.getSelectedRow(), 0));
            try {
                String nazwa = JOptionPane.showInputDialog("Podaj nazwe pliku: ");
                String data1 = JOptionPane.showInputDialog("Od jakiej daty (yyyy-mm-dd)");
                String data2 = JOptionPane.showInputDialog("Do jakiej daty (yyyy-mm-dd)");
                Date date1 = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(data1).getTime());
                Date date2 = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(data2).getTime());
                System.out.println(date1);
                System.out.println(date2);
                RezerwacjeControler rc = new RezerwacjeControler();
                List<RezerwacjeEntity> rezerwacje = rc.getByKursant(ID);
                FileWriter fw = new FileWriter(new File(nazwa + ".txt"));
                fw.write("Raport dla kursanta " + kc.getByID(ID).getImieNazwisko() + " za okres od " + date1.toString() + " do " + date2.toString()
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
        else
            JOptionPane.showMessageDialog(this,"Wybierz ktorys wiersz");
    }

}}