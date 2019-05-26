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
import java.util.List;

class KursanciPanel extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1;
    private InstruktorzyEntity current;
    JLabel login = new JLabel();
    JMenuBar pasek = new JMenuBar();
    JButton refresh = new JButton("Refresh");
    JButton delete = new JButton("Usuń pozycję");
    JButton edit = new JButton("Edytuj pozycje");
    String[] kolumny = {"ID", "Imie", "Nazwisko", "Email", "Pesel", "PKK","Saldo"};
    JTable kursanciTable = new JTable();
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
            kursanci = rc2.getByInstruktor(current.getInstruktorId());
        for (int i = 0; i < kursanci.size(); i++) {
            String[] zawartosc = {Integer.toString((int) kursanci.get(i).getKursantId()),
                    kursanci.get(i).getImie(), kursanci.get(i).getNazwisko(), kursanci.get(i).getEmail(), kursanci.get(i).getPesel(),
                    kursanci.get(i).getPkk(),String.valueOf(kc.getSaldo(kursanci.get(i)))};
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
        this.add(add);
        this.add(edit);
        this.add(delete);
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
            long ID = Integer.parseInt((String) kursanciTable.getValueAt(kursanciTable.getSelectedRow(), 0));
            kc.deleteByID(ID);
            refreshList();
        }
        if (source == edit) {
            long ID = Integer.parseInt((String) kursanciTable.getValueAt(kursanciTable.getSelectedRow(), 0));
            new EditKursanciFrame(ID,this);
        }
        if (source == add) {
            new RegisterFrame();
            refreshList();
        }
    }

}