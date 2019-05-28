package UI;

import Controlers.*;
import Entities.InstruktorzyEntity;
import Entities.KategorieEntity;
import Entities.KategorieInstruktorowEntity;
import Entities.UslugiEntity;
import UI.EditUslugaFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

class KategoriePanel extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1;
    private InstruktorzyEntity current;
    JLabel login = new JLabel();

    JButton refresh = new JButton("Refresh");
    JButton delete = new JButton("Usuń pozycję");
    JButton edit = new JButton("Edytuj pozycje");
    String[] kolumny = {"ID", "Kategoria", "Instruktor"};
    JTable uslugiTable = new JTable();
    JButton add = new JButton("Dodaj przypisanie");
    DefaultTableModel model = new DefaultTableModel(kolumny, 0);

    JScrollPane pane = new JScrollPane(uslugiTable);
    InstruktorzyControler ic = new InstruktorzyControler();
    RezerwacjeControler rc = new RezerwacjeControler();
    KursanciControler kc = new KursanciControler();
    UslugiControler uc = new UslugiControler();

    public void refreshList() {
        model.setRowCount(0);
        KategorieInstruktorowControler rc2 = new KategorieInstruktorowControler();
        KategorieControler kc = new KategorieControler();
        List<KategorieInstruktorowEntity> kursanci = rc2.getAll();
        for (int i = 0; i < kursanci.size(); i++) {
            String[] zawartosc = {Integer.toString((int) kursanci.get(i).getIdWpisu()),
                    kursanci.get(i).getKategorieByKategoriaId().getSymbol(), kursanci.get(i).getInstruktorzyByInstructorId().getImieNazwisko()};
            model.addRow(zawartosc);
        }

    }

    KategoriePanel(InstruktorzyEntity current) {
        edit.addActionListener(this);
        add.addActionListener(this);
        refresh.addActionListener(this);
        uslugiTable.setFillsViewportHeight(true);
        uslugiTable.setModel(model);
        uslugiTable.setAutoCreateRowSorter(true);

        login.setText("Nadawanie praw instruktorom");
        delete.addActionListener(this);
        this.current = current;
        this.add(login);
        this.add(pane);
        this.add(refresh);
        this.add(add);
        this.add(delete);

        refreshList();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();
        if (source == refresh) {
            refreshList();
        }

        if (source == delete) {
            if(!uslugiTable.getSelectionModel().isSelectionEmpty()){
            KategorieInstruktorowControler kic = new KategorieInstruktorowControler();
            long ID = Integer.parseInt((String) uslugiTable.getValueAt(uslugiTable.getSelectedRow(), 0));
            kic.deleteByID(ID);
            refreshList();}  else
                JOptionPane.showMessageDialog(this,"Wybierz ktorys wiersz");

        }
        if (source == add) {
            new AddKategorieFrame(this);
        }
    }

}