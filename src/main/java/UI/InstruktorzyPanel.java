package UI;

import Controlers.InstruktorzyControler;
import Controlers.KursanciControler;
import Controlers.RezerwacjeControler;
import Controlers.UslugiControler;
import Entities.InstruktorzyEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class InstruktorzyPanel extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1;
    private InstruktorzyEntity current;
    JLabel login = new JLabel();
    JButton logout = new JButton("Wyloguj się");
    JButton close = new JButton("Zamknij program");
    JMenuBar pasek = new JMenuBar();
    JButton refresh = new JButton("Refresh");
    JButton delete = new JButton("Usuń pozycję");
    JButton edit = new JButton("Edytuj pozycje");
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

    }

    InstruktorzyPanel(InstruktorzyEntity current) {
        edit.addActionListener(this);
        logout.addActionListener(this);
        close.addActionListener(this);
        add.addActionListener(this);
        refresh.addActionListener(this);
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
        this.add(logout);
        this.add(close);
        if(current.getCzyAdmin()==0)
        {
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
        if (source == logout) {
            int decyzja = JOptionPane.showConfirmDialog(this, "Czy na pewno chcesz się wylogowac?", "Potwierdź wylogowanie", JOptionPane.YES_NO_OPTION);
            if (decyzja == 0) {
                new LoginFrame();
                Window win = SwingUtilities.getWindowAncestor(this);
                ((Window) win).dispose();
            }
        } else if (source == close) {
            int decyzja = JOptionPane.showConfirmDialog(this, "Czy na pewno chcesz zamknąć program?", "Potwierdź zamykanie", JOptionPane.YES_NO_OPTION);
            if (decyzja == 0) {
                Window win = SwingUtilities.getWindowAncestor(this);
                ((Window) win).dispose();
            }
        }
        if (source == delete) {
            long ID = Integer.parseInt((String) rezerwacjeTable.getValueAt(rezerwacjeTable.getSelectedRow(), 0));
            rc.deleteByID(ID);
            refreshList();
        }
        if (source == edit) {
            long ID = Integer.parseInt((String) rezerwacjeTable.getValueAt(rezerwacjeTable.getSelectedRow(), 0));
            new EditReservationFrame(ID,current.getCzyAdmin());
        }
        if (source == add) {
            new AddReservationFrame(current.getCzyAdmin());
        }
    }

}