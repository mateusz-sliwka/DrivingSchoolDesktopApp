package UI;

import Controlers.*;
import Entities.KursanciEntity;
import Entities.PlatnosciEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

public class FinanseKursantPanel extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1;
    private KursanciEntity current;
    JLabel login = new JLabel();
    String[] kolumny = {"Data", "Kwota"};
    JTable platnosciTable = new JTable();
    DefaultTableModel model = new DefaultTableModel(kolumny, 0);
    JScrollPane pane = new JScrollPane(platnosciTable);
    JButton refresh=new JButton("Refresh");

    public void RefreshTable(){
        model.setRowCount(0);
        List<PlatnosciEntity> platnosci;
        platnosci = (List<PlatnosciEntity>) current.getPlatnoscisByKursantId();
        for (int i = 0; i < platnosci.size(); i++) {
            String[] zawartosc = {String.valueOf(platnosci.get(i).getDataPlatnosci()),
                    String.valueOf(platnosci.get(i).getKwota())};
            model.addRow(zawartosc);
        }
    }

    FinanseKursantPanel(KursanciEntity current) {
        this.current = current;
        login.setText("Witaj " + current.getImie() + " " + current.getNazwisko()+" w zakładce finansow");
        this.add(login);
        refresh.addActionListener(this);
        platnosciTable.setFillsViewportHeight(true);
        platnosciTable.setModel(model);
        platnosciTable.setAutoCreateRowSorter(true);
        this.add(pane);
        this.add(refresh);
        RefreshTable();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source= e.getSource();
                if(source==refresh) RefreshTable();
    }
}
