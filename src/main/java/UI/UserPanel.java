package UI;

import Entities.KursanciEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class UserPanel extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1;
    private KursanciEntity current;
    JLabel login = new JLabel();
    JButton logout = new JButton("Wyloguj się");
    JButton close = new JButton("Zamknij program");

    UserPanel(KursanciEntity current) {
        this.current = current;
        close.addActionListener(this);
        System.out.println(current.getImie());
        logout.addActionListener(this);
        login.setText("Witaj: " + current.getImie() + " " + current.getNazwisko());
        this.add(login);
        this.add(logout);
        this.add(close);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
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

    }
}