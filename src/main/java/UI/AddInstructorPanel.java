package UI;

import Controlers.InstruktorzyControler;
import Controlers.KursanciControler;
import Entities.InstruktorzyEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

class AddInstructorPanel extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1;
    JLabel mailLabel = new JLabel("Podaj e-mail:");
    JTextField mail = new JTextField(20);
    JLabel hasloLabel = new JLabel("Podaj haslo:");
    JTextField haslo = new JPasswordField(20);
    JLabel imieLabel = new JLabel("Podaj imię:");
    JTextField imie = new JTextField(20);
    JLabel nazwiskoLabel = new JLabel("Podaj nazwisko:");
    JTextField nazwisko = new JTextField(20);
    JLabel grLabel = new JLabel("Podaj godz rozpoczecia:");
    JTextField gr = new JTextField(20);
    JLabel gzLabel = new JLabel("Podaj godz zakonczenia:");
    JTextField gz = new JTextField(20);
    JLabel komunikat = new JLabel("<html><font color='red'>Podaj wszystkie dane.</html>");
    JButton register = new JButton("Zarejestruj");
    JButton cancel = new JButton("Anuluj");
    InstruktorzyPanel panel;

    AddInstructorPanel(InstruktorzyPanel panel) {
        this.panel = panel;
        register.addActionListener(this);
        cancel.addActionListener(this);
        this.add(mailLabel);
        this.add(mail);
        this.add(hasloLabel);
        this.add(haslo);
        this.add(imieLabel);
        this.add(imie);
        this.add(nazwiskoLabel);
        this.add(nazwisko);

        this.add(grLabel);
        this.add(gr);
        this.add(gzLabel);
        this.add(gz);
        this.add(register);
        this.add(cancel);
        this.add(komunikat);
        komunikat.setVisible(false);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == cancel) {
            Window win = SwingUtilities.getWindowAncestor(this);
            ((Window) win).dispose();
        }
        if (source == register) {
            boolean mozna = true;
            String[] napisy = {mail.getText(), haslo.getText(), imie.getText(), nazwisko.getText(), gr.getText(), gz.getText()};
            for (int i = 0; i < napisy.length; i++)
                if (napisy[i].length() == 0 || napisy[i] == null || napisy[i] == "") {
                    mozna = false;
                    niezarejestrowano();
                }
            if (mozna == true)
                zarejestrowano();

        }

    }

    void zarejestrowano() {

        InstruktorzyControler ic = new InstruktorzyControler();
        InstruktorzyEntity ie = new InstruktorzyEntity();
        ie.setImie(imie.getText());
        ie.setNazwisko(nazwisko.getText());
        ie.setEmail(mail.getText());
        ie.setHaslo(haslo.getText());
        ie.setGodzRozpoczecia(gr.getText());
        ie.setGodzZakonczenia(gz.getText());
        ie.setCzyAdmin(0);
        ie.setDataDodania(new Date(System.currentTimeMillis()));
        ic.add(ie);
        JOptionPane.showMessageDialog(this, "Konto zostało utworzone!");
        Window win = SwingUtilities.getWindowAncestor(this);
        ((Window) win).dispose();
        panel.refreshList();

    }

    void niezarejestrowano() {
        komunikat.setVisible(true);
    }
}