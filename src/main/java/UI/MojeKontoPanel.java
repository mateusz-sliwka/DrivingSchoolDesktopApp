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

class MojeKontoPanel extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1;
    private InstruktorzyEntity current;
    JLabel login = new JLabel();

    JMenuBar pasek = new JMenuBar();
    JLabel imieLabel = new JLabel("Imię: ");
    JTextField imieField = new JTextField("Podaj swoje imię");
    JLabel nazwiskoLabel = new JLabel("Nazwisko: ");
    JTextField nazwiskoField = new JTextField("Podaj swoje nazwisko");
    JLabel emailLabel = new JLabel("Adres e-mail: ");
    JTextField emailField = new JTextField("Podaj swoj mail");
    JLabel hasloLabel = new JLabel("Hasło: ");
    JTextField hasloField = new JPasswordField("Podaj swoje haslo");
    JButton aktualizuj = new JButton("Aktualizuj dane konta");
    InstruktorzyControler ic = new InstruktorzyControler();
    RezerwacjeControler rc = new RezerwacjeControler();
    KursanciControler kc = new KursanciControler();
    UslugiControler uc = new UslugiControler();

    void refreshFields() {
        ic = new InstruktorzyControler();
        current = ic.getByID(current.getInstruktorId());
        imieField.setText(current.getImie());
        imieField.setColumns(20);
        nazwiskoField.setText(current.getNazwisko());
        nazwiskoField.setColumns(20);
        emailField.setText(current.getEmail());
        emailField.setColumns(20);
        hasloField.setText(current.getHaslo());
        hasloField.setColumns(20);
    }

    MojeKontoPanel(InstruktorzyEntity current) {

        aktualizuj.addActionListener(this);

        login.setText("Zarządzanie Twoim kontem");
        this.current = current;
        refreshFields();
        this.add(login);
        this.add(imieLabel);
        this.add(imieField);
        this.add(nazwiskoLabel);
        this.add(nazwiskoField);
        this.add(emailLabel);
        this.add(emailField);
        this.add(hasloLabel);
        this.add(hasloField);
        this.add(aktualizuj);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

        if (source == aktualizuj) {
            InstruktorzyEntity ie = new InstruktorzyEntity();
            if (current.getCzyAdmin() == 1)
                ie.setCzyAdmin(1);
            ie.setImie(imieField.getText());
            ie.setNazwisko(nazwiskoField.getText());
            ie.setEmail(emailField.getText());
            ie.setHaslo(hasloField.getText());
            ic.update(ie, current.getInstruktorId());
            JOptionPane.showMessageDialog(this, "Dane Twojego konta zostaly zaktualizowane");
            refreshFields();
        }
    }

}