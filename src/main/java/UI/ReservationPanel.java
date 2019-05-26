package UI;

import Controlers.InstruktorzyControler;
import Controlers.KursanciControler;
import Controlers.RezerwacjeControler;
import Controlers.UslugiControler;
import Entities.InstruktorzyEntity;
import Entities.RezerwacjeEntity;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ReservationPanel extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1;
    private InstruktorzyEntity current;
    JLabel login = new JLabel();
    JMenuBar pasek = new JMenuBar();
    JButton refresh = new JButton("Refresh");
    JButton delete = new JButton("Usuń pozycję");
    JButton edit = new JButton("Edytuj pozycje");
    JMenuItem menu = new JMenuItem("Zarządzaj rezerwacjami");
    String[] kolumny = {"ID", "Usługa", "Kursant", "Instruktor", "Data dodania"};
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
                    rezerwacje.get(i).getKursanciByKursantId().getImieNazwisko(),
                   rezerwacje.get(i).getInstruktorzyByInstruktorId().getImieNazwisko(),
                    (rezerwacje.get(i).getDataDodania().toString())};
            model.addRow(zawartosc);
        }
    }

    ReservationPanel(InstruktorzyEntity current) {
        edit.addActionListener(this);

        add.addActionListener(this);
        refresh.addActionListener(this);
        rezerwacjeTable.setFillsViewportHeight(true);
        rezerwacjeTable.setModel(model);
        rezerwacjeTable.setAutoCreateRowSorter(true);
        pasek.add(menu);
        System.out.println(current.getImie());
        login.setText("Zarządzanie rezerwacjami");
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
            new AddReservationFrame(current.getCzyAdmin(), this);
        }
    }

}