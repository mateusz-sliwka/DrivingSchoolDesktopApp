package UI;

import Controlers.*;
import Entities.KursanciEntity;
import Entities.PlatnosciEntity;
import Entities.RezerwacjeEntity;
import Entities.UslugiEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.List;

public class FinanseKursantPanel extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1;
    private KursanciEntity current;
    UslugiControler uc=new UslugiControler();
    RezerwacjeControler rc= new RezerwacjeControler();
    JLabel login = new JLabel();
    String[] kolumny = {"Data", "Kwota"};
    JTable platnosciTable = new JTable();
    DefaultTableModel model = new DefaultTableModel(kolumny, 0);
    JScrollPane pane = new JScrollPane(platnosciTable);
    JButton refresh=new JButton("Refresh");
    JLabel saldo= new JLabel();
    long saldoint=0;

    public void RefreshTable(){
        model.setRowCount(0);

        List<PlatnosciEntity> platnosci;
        platnosci = (List<PlatnosciEntity>) current.getPlatnoscisByKursantId();

        List<RezerwacjeEntity> rezerwacje=rc.getByKursant(current.getKursantId());

        List<UslugiEntity>uslugi= new ArrayList<>();
        for(int i=0; i<rezerwacje.size();i++)
            uslugi.add(uc.getByID(rezerwacje.get(i).getUslugaId()));


        for (int i = 0; i < platnosci.size(); i++) {
            String[] zawartosc = {String.valueOf(platnosci.get(i).getDataPlatnosci()),
                    String.valueOf(platnosci.get(i).getKwota())};
            saldoint+=platnosci.get(i).getKwota();
            model.addRow(zawartosc);
        }
        for (int i = 0; i < rezerwacje.size(); i++) {
            long cena=uslugi.get(i).getCena();
            String[] zawartosc = {String.valueOf(rezerwacje.get(i).getDataRezerwacji()),
                    String.valueOf(cena-2*cena)};
            saldoint-=cena;
            model.addRow(zawartosc);
            saldo.setText("Saldo: "+ saldoint);
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
        this.add(saldo);
        RefreshTable();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source= e.getSource();
                if(source==refresh) RefreshTable();
    }
}
