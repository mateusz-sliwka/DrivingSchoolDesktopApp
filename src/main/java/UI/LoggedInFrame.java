package UI;

import Entities.InstruktorzyEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoggedInFrame extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    InstruktorzyEntity current;
    JMenuBar bar = new JMenuBar();
    JMenuItem rezerwacje = new JMenuItem("Rezerwacje");
    JMenuItem kursanci = new JMenuItem("Kursanci");
    JMenuItem instruktorzy = new JMenuItem("Instruktorzy");
    JMenuItem platnosc = new JMenuItem("Płatności");
    JMenuItem uslugi = new JMenuItem("Usługi");
    JMenuItem konto = new JMenuItem("Twoje konto");
    JMenu program = new JMenu("Program");
    JMenuItem zamknij = new JMenuItem("Zamknij program");
    JMenuItem wyloguj = new JMenuItem("Wyloguj");

    ReservationPanel panel1;


    public LoggedInFrame(InstruktorzyEntity current) {

        super("Panel administratora - uzytkownik " + current.getImieNazwisko());
        if (current.getCzyAdmin() == 0)
            this.setTitle("Panel instruktora - uzytkownik " + current.getImieNazwisko());
        instruktorzy.addActionListener(this);
        kursanci.addActionListener(this);
        platnosc.addActionListener(this);
        uslugi.addActionListener(this);
        wyloguj.addActionListener(this);
        zamknij.addActionListener(this);
        rezerwacje.addActionListener(this);
        konto.addActionListener(this);
        bar.add(rezerwacje);
        if (current.getCzyAdmin() == 1) {
            bar.add(kursanci);
            bar.add(instruktorzy);
            bar.add(platnosc);
            bar.add(uslugi);
        }
        program.add(zamknij);
        program.add(wyloguj);

        bar.add(konto);
        bar.add(program);
        this.setJMenuBar(bar);
        this.current = current;
        panel1 = new ReservationPanel(current);
        this.setSize(new Dimension(800, 500));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(panel1);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == rezerwacje) {
            this.setContentPane(panel1);
            panel1.refreshList();
        } else if (source == kursanci) {
            JPanel panel2 = new KursanciPanel(current);
            this.setContentPane(panel2);
            this.setVisible(true);
        } else if (source == platnosc) {
            JPanel panel2 = new PlatnosciPanel(current);
            this.setContentPane(panel2);
            this.setVisible(true);
        } else if (source == uslugi) {
            JPanel panel2 = new UslugiPanel(current);
            this.setContentPane(panel2);
            this.setVisible(true);
        } else if (source == instruktorzy) {
            JPanel panel2 = new InstruktorzyPanel(current);
            this.setContentPane(panel2);
            this.setVisible(true);
        } else if (source == konto) {
            JPanel panel2 = new MojeKontoPanel(current);
            this.setContentPane(panel2);
            this.setVisible(true);
        }if (source == wyloguj) {
            int decyzja = JOptionPane.showConfirmDialog(this, "Czy na pewno chcesz się wylogowac?", "Potwierdź wylogowanie", JOptionPane.YES_NO_OPTION);
            if (decyzja == 0) {
                new LoginFrame();
                this.dispose();
            }
        } else if (source == zamknij ){
            int decyzja = JOptionPane.showConfirmDialog(this, "Czy na pewno chcesz zamknąć program?", "Potwierdź zamykanie", JOptionPane.YES_NO_OPTION);
            if (decyzja == 0) {
                this.dispose();
            }
        }
    }
}