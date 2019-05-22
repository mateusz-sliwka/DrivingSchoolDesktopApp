package UI;

import Controlers.InstruktorzyControler;
import Controlers.KursanciControler;
import Entities.InstruktorzyEntity;
import Entities.KursanciEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

class RegisterPanel extends JPanel implements ActionListener {

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
    JButton register = new JButton("Zarejestruj");
    JButton cancel = new JButton("Anuluj");

    RegisterPanel() {

        register.addActionListener(this);
        cancel.addActionListener(this);
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
        komunikat.setVisible(false);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source==cancel){
            Window win=SwingUtilities.getWindowAncestor(this);
            ((Window) win).dispose();
        }
        if(source==register){
            boolean mozna=true;
            String[] napisy={login.getText(),haslo.getText(),imie.getText(),nazwisko.getText(),pesel.getText(),pkk.getText()};
            for(int i=0;i<napisy.length;i++)
                if(napisy[i].length()==0||napisy[i]==null||napisy[i]==""){
                mozna=false;
                niezarejestrowano();
                }
             if(mozna==true)
            zarejestrowano();

        }

    }
    void zarejestrowano(){
        Window win=SwingUtilities.getWindowAncestor(this);
        ((Window) win).dispose();
        KursanciControler kc = new KursanciControler();
        boolean flaga = kc.add(imie.getText(),nazwisko.getText(),login.getText(),haslo.getText(),pkk.getText(),pesel.getText());
        if(flaga==true){
            JOptionPane.showMessageDialog(this,"Twoje konto zostalo utworzone! Mozesz sie zalogowac");
        }
        else
            JOptionPane.showMessageDialog(this,"Wystapil blad. Sprobuj ponownie");
    }
    void niezarejestrowano(){
        komunikat.setVisible(true);
    }
}