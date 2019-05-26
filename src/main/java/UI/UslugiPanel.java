package UI;

import Controlers.InstruktorzyControler;
import Controlers.KursanciControler;
import Controlers.RezerwacjeControler;
import Controlers.UslugiControler;
import Entities.InstruktorzyEntity;
import Entities.KursanciEntity;
import Entities.UslugiEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

class UslugiPanel extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1;
    private InstruktorzyEntity current;
    JLabel login = new JLabel();

    JMenuBar pasek = new JMenuBar();
    JButton refresh = new JButton("Refresh");
    JButton delete = new JButton("Usuń pozycję");
    JButton edit = new JButton("Edytuj pozycje");
    String[] kolumny = {"ID", "Nazwa", "Cena"};
    JTable uslugiTable = new JTable();
    JButton add = new JButton("Dodaj usługe");
    DefaultTableModel model = new DefaultTableModel(kolumny, 0);
    JScrollPane pane = new JScrollPane(uslugiTable);
    InstruktorzyControler ic = new InstruktorzyControler();
    RezerwacjeControler rc = new RezerwacjeControler();
    KursanciControler kc = new KursanciControler();
    UslugiControler uc = new UslugiControler();

    public void refreshList() {
        model.setRowCount(0);
        UslugiControler rc2 = new UslugiControler();
        List<UslugiEntity> kursanci = rc2.getAll();
        for (int i = 0; i < kursanci.size(); i++) {
            String[] zawartosc = {Integer.toString((int) kursanci.get(i).getUslugaId()),
                    kursanci.get(i).getNazwa(), Integer.toString((int) kursanci.get(i).getCena())};
            model.addRow(zawartosc);
        }

    }

    UslugiPanel(InstruktorzyEntity current) {
        edit.addActionListener(this);
        add.addActionListener(this);
        refresh.addActionListener(this);
        uslugiTable.setFillsViewportHeight(true);
        uslugiTable.setModel(model);
        uslugiTable.setAutoCreateRowSorter(true);

        login.setText("Zarządzanie usługami");
        delete.addActionListener(this);
        this.current = current;
        this.add(login);
        this.add(pane);
        this.add(refresh);
        this.add(add);
        this.add(edit);
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
            long ID = Integer.parseInt((String) uslugiTable.getValueAt(uslugiTable.getSelectedRow(), 0));
            uc.deleteByID(ID);
            refreshList();
        }
        if (source == edit) {
            long ID = Integer.parseInt((String) uslugiTable.getValueAt(uslugiTable.getSelectedRow(), 0));
            new EditUslugaFrame(ID, this);
        }
        if (source == add) {
            String tytul = JOptionPane.showInputDialog("Podaj tytul uslugi: ");
            String cena = JOptionPane.showInputDialog("Podaj cene: ");
            UslugiEntity ue = new UslugiEntity();
            ue.setNazwa(tytul);
            ue.setCena((long) Integer.parseInt(cena));
            UslugiControler uc = new UslugiControler();
            uc.add(ue);
            refreshList();
        }
    }

}