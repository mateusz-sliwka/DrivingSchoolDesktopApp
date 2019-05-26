package UI;

import Controlers.*;
import Entities.InstruktorzyEntity;
import Entities.PlatnosciEntity;
import Entities.UslugiEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

class PlatnosciPanel extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1;
    private InstruktorzyEntity current;
    JLabel login = new JLabel();

    JMenuBar pasek = new JMenuBar();
    JButton refresh = new JButton("Refresh");
    JButton delete = new JButton("Usuń pozycję");
    JButton edit = new JButton("Edytuj pozycje");
    String[] kolumny = {"ID", "Rezerwacja ID", "Kwota", "Data zaksiegowania"};
    JTable rezerwacjeTable = new JTable();
    JButton add = new JButton("Zaksieguj wplate");
    DefaultTableModel model = new DefaultTableModel(kolumny, 0);
    JScrollPane pane = new JScrollPane(rezerwacjeTable);
    InstruktorzyControler ic = new InstruktorzyControler();
    RezerwacjeControler rc = new RezerwacjeControler();
    KursanciControler kc = new KursanciControler();
    UslugiControler uc = new UslugiControler();

    public void refreshList() {
        model.setRowCount(0);
        PlatnosciControler rc2 = new PlatnosciControler();
        List<PlatnosciEntity> kursanci = rc2.getAll();
        for (int i = 0; i < kursanci.size(); i++) {
            String[] zawartosc = {Integer.toString((int) kursanci.get(i).getPlatnoscId()),
                    kursanci.get(i).getNazwa(),Integer.toString((int)kursanci.get(i).getCena())};
            model.addRow(zawartosc);
        }
    }

    PlatnosciPanel(InstruktorzyEntity current) {
        edit.addActionListener(this);
        add.addActionListener(this);
        refresh.addActionListener(this);
        rezerwacjeTable.setFillsViewportHeight(true);
        rezerwacjeTable.setModel(model);
        rezerwacjeTable.setAutoCreateRowSorter(true);
        System.out.println(current.getImie());
        login.setText("Zarządzanie płatnościami");
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
            long ID = Integer.parseInt((String) rezerwacjeTable.getValueAt(rezerwacjeTable.getSelectedRow(), 0));
            rc.deleteByID(ID);
            refreshList();
        }
        if (source == edit) {
            long ID = Integer.parseInt((String) rezerwacjeTable.getValueAt(rezerwacjeTable.getSelectedRow(), 0));
            new EditReservationFrame(ID, current.getCzyAdmin());
        }
        if (source == add) {

        }
    }

}