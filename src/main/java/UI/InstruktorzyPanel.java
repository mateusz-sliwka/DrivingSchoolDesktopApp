package UI;

import Controlers.*;
import Entities.InstruktorzyEntity;
import Entities.KategorieInstruktorowEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

class InstruktorzyPanel extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1;
    private InstruktorzyEntity current;
    JLabel login = new JLabel();

    JMenuBar pasek = new JMenuBar();
    JButton refresh = new JButton("Refresh");
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
        this.add(przypisz);

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

        if (source == przypisz) {
            ic = new InstruktorzyControler();
            long ID = Integer.parseInt((String) rezerwacjeTable.getValueAt(rezerwacjeTable.getSelectedRow(), 0));
            new AddCategoryToInstructorFrame(ID, this);

        }
        if (source == delete) {
            long ID = Integer.parseInt((String) rezerwacjeTable.getValueAt(rezerwacjeTable.getSelectedRow(), 0));
            ic.deleteByID(ID);
            refreshList();
        }
        if (source == edit) {
            long ID = Integer.parseInt((String) rezerwacjeTable.getValueAt(rezerwacjeTable.getSelectedRow(), 0));
            new EditInstructorFrame(ID, this);
        }
        if (source == add) {
            new AddInstructorFrame(this);
        }
    }

}