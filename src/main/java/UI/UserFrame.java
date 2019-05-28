package UI;

import Entities.KursanciEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserFrame extends JFrame implements ActionListener{
    private static final long serialVersionUID = 1L;
    KursanciEntity current;

    JMenuBar bar = new JMenuBar();
    JMenuItem rezerwacje = new JMenuItem("Rezerwacje");
    JMenuItem finanse = new JMenuItem("Finanse");
    JMenuItem konto = new JMenuItem("Konto");
    JMenuItem wyloguj = new JMenuItem("Wyloguj");
    RezerwacjeKursantPanel panel1;



    public UserFrame(KursanciEntity current) {
        super("Panel kursanta");
        rezerwacje.addActionListener(this);
        finanse.addActionListener(this);
        konto.addActionListener(this);
        wyloguj.addActionListener(this);
        bar.add(rezerwacje);
        bar.add(finanse);
        bar.add(konto);
        bar.add(wyloguj);
        this.setJMenuBar(bar);
        this.current = current;
        panel1=new RezerwacjeKursantPanel(current);
        this.setSize(new Dimension(800, 600));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(panel1);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source=e.getSource();

                if(source==rezerwacje)
                {
                    this.setContentPane(panel1);
                    this.setSize(new Dimension(800, 600));
                }
                else if(source==finanse)
                {
                    FinanseKursantPanel panel2= new FinanseKursantPanel(current);
                    this.setContentPane(panel2);
                    this.setSize(new Dimension(800, 500));
                    this.setVisible(true);
                }
                else if(source==konto)
                {
                    UserPanel panel3= new UserPanel(current);
                    this.setContentPane(panel3);
                    this.setSize(new Dimension(400, 500));
                    this.setVisible(true);
                }
                else if(source==wyloguj)
                {
                    int decyzja = JOptionPane.showConfirmDialog(this, "Czy na pewno chcesz się wylogowac?", "Potwierdź wylogowanie", JOptionPane.YES_NO_OPTION);
                    if (decyzja == 0) {
                        new LoginFrame();
                        this.dispose();
                    }
                }
    }
}