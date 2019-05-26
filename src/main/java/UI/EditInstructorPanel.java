package UI;

import Controlers.InstruktorzyControler;
import Controlers.KursanciControler;
import Entities.InstruktorzyEntity;
import Entities.KursanciEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class EditInstructorPanel extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1;
    JLabel loginLabel = new JLabel("Podaj e-mail:");
    JTextField login = new JTextField(20);
    JLabel hasloLabel = new JLabel("Podaj haslo:");
    JTextField haslo = new JPasswordField(20);
    JLabel imieLabel = new JLabel("Podaj imię:");
    JTextField imie = new JTextField(20);
    JLabel nazwiskoLabel = new JLabel("Podaj nazwisko:");
    JTextField nazwisko = new JTextField(20);

    JLabel komunikat = new JLabel("<html><font color='red'>Podaj wszystkie dane.</html>");
    JButton register = new JButton("Aktualizuj");
    JButton cancel = new JButton("Anuluj");
    InstruktorzyEntity current;
    InstruktorzyPanel kp;

    EditInstructorPanel(long ID, InstruktorzyPanel panel) {
        kp = panel;
        InstruktorzyControler ic = new InstruktorzyControler();
        register.addActionListener(this);
        cancel.addActionListener(this);
        current = ic.getByID(ID);
        this.add(loginLabel);
        this.add(login);
        this.add(hasloLabel);
        this.add(haslo);
        this.add(imieLabel);
        this.add(imie);
        this.add(nazwiskoLabel);
        this.add(nazwisko);

        this.add(register);
        this.add(cancel);
        this.add(komunikat);
        imie.setText(current.getImie());
        nazwisko.setText(current.getNazwisko());
        login.setText(current.getEmail());
        haslo.setText(current.getHaslo());

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
            InstruktorzyControler ic = new InstruktorzyControler();
            InstruktorzyEntity ie = new InstruktorzyEntity();
            ie.setImie(imie.getText());
            ie.setNazwisko(nazwisko.getText());
            ie.setEmail(login.getText());
            ie.setHaslo(haslo.getText());
            ic.update(ie, current.getInstruktorId());
            JOptionPane.showMessageDialog(this, "Zaktulizowano konto instruktora");
            kp.refreshList();
            Window win = SwingUtilities.getWindowAncestor(this);
            ((Window) win).dispose();
        }

    }
}