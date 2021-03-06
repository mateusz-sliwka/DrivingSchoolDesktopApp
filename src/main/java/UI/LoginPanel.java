package UI;

import Controlers.InstruktorzyControler;
import Controlers.KursanciControler;
import Entities.InstruktorzyEntity;
import Entities.KursanciEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class LoginPanel extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1;
    JLabel title = new JLabel("Logowanie do systemu szkoły jazdy");
    JLabel loginLabel = new JLabel("Podaj e-mail:");
    JTextField login = new JTextField(20);
    JLabel hasloLabel = new JLabel("Podaj haslo:");
    JTextField haslo = new JPasswordField(20);
    String[] typy = {"Kursant", "Instruktor"};
    JLabel typ = new JLabel("Wybierz typ konta:");
    JComboBox<String> typBox = new JComboBox<String>(typy);
    JLabel komunikat = new JLabel("<html><font color='red'>Podane dane logowania są nieprawidłowe.</html>");
    JButton register = new JButton("Załóż konto");
    JButton close = new JButton("Zamknij program");
    JButton zaloguj = new JButton("Zaloguj");

    LoginPanel() {
        this.setFocusable(true);
        zaloguj.addActionListener(this);
        register.addActionListener(this);
        close.addActionListener(this);
        this.add(title);
        this.add(loginLabel);
        this.add(login);
        this.add(hasloLabel);
        this.add(haslo);
        this.add(typ);
        this.add(typBox);
        this.add(zaloguj);
        this.add(register);
        this.add(close);
        this.add(komunikat);
        komunikat.setVisible(false);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == register) {
            new RegisterFrame();
        }
        if (source == zaloguj) {
            if (typBox.getSelectedItem().toString() == "Kursant") {
                KursanciEntity kursant = new KursanciControler().login(login.getText(), haslo.getText());
                if (kursant == null) {
                    niezalogowano();
                } else {
                    zalogowano();
                    new UserFrame(kursant);
                }
            } else if (typBox.getSelectedItem().toString() == "Instruktor") {
                InstruktorzyEntity instruktor = new InstruktorzyControler().login((login.getText()), haslo.getText());
                if (instruktor == null)
                    niezalogowano();
                else {
                    zalogowano();
                    new LoggedInFrame(instruktor);
                }
            }

        } else if (source == close) {
            int decyzja = JOptionPane.showConfirmDialog(this, "Czy na pewno chcesz zamknąć program?", "Potwierdź zamykanie", JOptionPane.YES_NO_OPTION);
            if (decyzja == 0) {
                Window win = SwingUtilities.getWindowAncestor(this);
                ((Window) win).dispose();
            }
        }
    }

    void zalogowano() {
        Window win = SwingUtilities.getWindowAncestor(this);
        ((Window) win).dispose();
    }

    void niezalogowano() {
        komunikat.setVisible(true);
        haslo.setText("");
    }
}