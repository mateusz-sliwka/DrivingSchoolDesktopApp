package UI;

import Controlers.*;
import Entities.InstruktorzyEntity;
import Entities.KategorieInstruktorowEntity;
import Entities.RezerwacjeEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

class InstruktorzyPanel extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1;
    private InstruktorzyEntity current;
    JLabel login = new JLabel();

    JMenuBar pasek = new JMenuBar();
    JButton refresh = new JButton("Refresh");
    JButton plik = new JButton("Generuj raport instruktora");
    JButton delete = new JButton("Usuń pozycję");
    JButton edit = new JButton("Edytuj pozycje");
    JButton przypisz = new JButton("Przypisz kategorie");
    String[] kolumny = {"ID", "Imie", "Nazwisko", "Start pracy", "Koniec pracy", "Email", "Kategorie"};
    JTable rezerwacjeTable = new JTable();
    JButton add = new JButton("Dodaj instruktora");
    DefaultTableModel model = new DefaultTableModel(kolumny, 0);
    JScrollPane pane = new JScrollPane(rezerwacjeTable);
    InstruktorzyControler ic = new InstruktorzyControler();
    RezerwacjeControler rc = new RezerwacjeControler();
    KursanciControler kc = new KursanciControler();
    UslugiControler uc = new UslugiControler();

    public void refreshList() {
        ic = new InstruktorzyControler();
        model.setRowCount(0);
        List<InstruktorzyEntity> instruktorzy = ic.getAll();
        for (int i = 0; i < instruktorzy.size(); i++) {
            String kategorie = "";
            KategorieControler kc = new KategorieControler();
            Iterator<KategorieInstruktorowEntity> iterator;
            Collection<KategorieInstruktorowEntity> kolekcja = instruktorzy.get(i).getKategorieInstruktorowsByInstruktorId();
            for (KategorieInstruktorowEntity kie : kolekcja) {
                kategorie += kc.getByID(kie.getKategoriaId()).getSymbol();
            }
            if (instruktorzy.get(i).getKategorieInstruktorowsByInstruktorId().toArray().length != 0)
                System.out.println((KategorieInstruktorowEntity) instruktorzy.get(i).getKategorieInstruktorowsByInstruktorId().toArray()[0]);

            String[] zawartosc = {Integer.toString((int) instruktorzy.get(i).getInstruktorId()),
                    instruktorzy.get(i).getImie(),
                    instruktorzy.get(i).getNazwisko(),
                    instruktorzy.get(i).getEmail(),
                    instruktorzy.get(i).getGodzRozpoczecia(),
                    instruktorzy.get(i).getGodzZakonczenia(),
                    kategorie
            };
            model.addRow(zawartosc);
        }
    }

    InstruktorzyPanel(InstruktorzyEntity current) {
        edit.addActionListener(this);

        add.addActionListener(this);
        refresh.addActionListener(this);
        przypisz.addActionListener(this);
        rezerwacjeTable.setFillsViewportHeight(true);
        rezerwacjeTable.setModel(model);
        rezerwacjeTable.setAutoCreateRowSorter(true);
        System.out.println(current.getImie());
        login.setText("Zarządzanie instruktorami");
        delete.addActionListener(this);
        this.current = current;
        this.add(login);
        this.add(pane);
        this.add(refresh);
        this.add(add);
        this.add(edit);
        this.add(delete);
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
            if(!rezerwacjeTable.getSelectionModel().isSelectionEmpty()) {
            long ID = Integer.parseInt((String) rezerwacjeTable.getValueAt(rezerwacjeTable.getSelectedRow(), 0));
            ic.deleteByID(ID);
            refreshList(); }  else
                JOptionPane.showMessageDialog(this,"Wybierz ktorys wiersz");
        }
        if (source == edit) {
            if(!rezerwacjeTable.getSelectionModel().isSelectionEmpty()) {
                long ID = Integer.parseInt((String) rezerwacjeTable.getValueAt(rezerwacjeTable.getSelectedRow(), 0));
                new EditInstructorFrame(ID, this);
            }  else
                JOptionPane.showMessageDialog(this,"Wybierz ktorys wiersz");

        }
        if (source == add) {
            new AddInstructorFrame(this);
        }
        if (source == plik) {
            if(!rezerwacjeTable.getSelectionModel().isSelectionEmpty()){
            long ID = Integer.parseInt((String) rezerwacjeTable.getValueAt(rezerwacjeTable.getSelectedRow(), 0));

            try {
                String nazwa = JOptionPane.showInputDialog("Podaj nazwe pliku: ");
                String data1 = JOptionPane.showInputDialog("Od jakiej daty (yyyy-mm-dd)");
                String data2 = JOptionPane.showInputDialog("Do jakiej daty (yyyy-mm-dd)");
                Date date1 = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(data1).getTime());
                Date date2 = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(data2).getTime());
                System.out.println(date1);
                System.out.println(date2);
                RezerwacjeControler rc = new RezerwacjeControler();
                List<RezerwacjeEntity> rezerwacje = rc.getByInstruktor(ID);
                FileWriter fw = new FileWriter(new File(nazwa + ".txt"));
                fw.write("Raport dla instruktora " + ic.getByID(ID).getImieNazwisko() + " za okres od " + date1.toString() + " do " + date2.toString()
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
    }}

}