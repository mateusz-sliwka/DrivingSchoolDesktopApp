package UI;

import Controlers.KursanciControler;
import Entities.KursanciEntity;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class EditKursanciPanel extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1;
    JLabel loginLabel = new JLabel("Podaj e-mail:");
    JTextField login = new JTextField(20);
    JLabel hasloLabel = new JLabel("Podaj haslo:");
    JTextField haslo = new JPasswordField(20);
    JLabel imieLabel = new JLabel("Podaj imię:");
    JTextField imie = new JTextField(20);
    JLabel nazwiskoLabel = new JLabel("Podaj nazwisko:");
    JTextField nazwisko = new JTextField(20);
    JLabel peselLabel = new JLabel("Podaj PESEL:");
    JTextField pesel = new JTextField(20);
    JLabel pkkLabel = new JLabel("Podaj PKK:");
    JTextField pkk = new JTextField(20);
    JLabel komunikat = new JLabel("<html><font color='red'>Podaj wszystkie dane.</html>");
    JButton register = new JButton("Aktualizuj");
    JButton cancel = new JButton("Anuluj");
    KursanciEntity current;
    KursanciPanel kp;

    EditKursanciPanel(long ID, KursanciPanel panel) {
        kp = panel;
        KursanciControler kc = new KursanciControler();
        register.addActionListener(this);
        cancel.addActionListener(this);
        current = kc.getByID(ID);
        this.add(loginLabel);
        this.add(login);
        this.add(hasloLabel);
        this.add(haslo);
        this.add(imieLabel);
        this.add(imie);
        this.add(nazwiskoLabel);
        this.add(nazwisko);
        this.add(peselLabel);
        this.add(pesel);
        this.add(pkkLabel);
        this.add(pkk);
        this.add(register);
        this.add(cancel);
        this.add(komunikat);
        imie.setText(current.getImie());
        nazwisko.setText(current.getNazwisko());
        login.setText(current.getEmail());
        haslo.setText(current.getHaslo());
        pesel.setText(current.getPesel());
        pkk.setText(current.getPkk());
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
            KursanciControler kc = new KursanciControler();
            KursanciEntity ke = new KursanciEntity();
            ke.setImie(imie.getText());
            ke.setNazwisko(nazwisko.getText());
            ke.setEmail(login.getText());
            ke.setHaslo(haslo.getText());
            ke.setPesel(pesel.getText());
            ke.setPkk(pkk.getText());
            kc.update(ke, current.getKursantId());
            JOptionPane.showMessageDialog(this, "Zaktulizowano konto kursanta");
            kp.refreshList();
            Window win = SwingUtilities.getWindowAncestor(this);
            ((Window) win).dispose();
        }

    }

    void zarejestrowano() {
        Window win = SwingUtilities.getWindowAncestor(this);
        ((Window) win).dispose();
        KursanciControler kc = new KursanciControler();
        boolean flaga = kc.add(imie.getText(), nazwisko.getText(), login.getText(), haslo.getText(), pkk.getText(), pesel.getText());
        if (flaga == true) {
            JOptionPane.showMessageDialog(this, "Konto zostało utworzone!");
        } else
            JOptionPane.showMessageDialog(this, "Wystapil blad. Sprobuj ponownie");
    }

    void niezarejestrowano() {
        komunikat.setVisible(true);
    }
}