package UI;

import Controlers.KursanciControler;
import Entities.KursanciEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class UserPanel extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1;
    private KursanciEntity current;
    KursanciControler kc = new KursanciControler();

    JLabel info= new JLabel();
    JLabel currentName= new JLabel();
    JLabel currentSurname=new JLabel();
    JLabel currentEmail= new JLabel();
    JLabel currentPKK=new JLabel();
    JLabel currentPesel=new JLabel();
    JLabel currentName2= new JLabel();
    JLabel currentSurname2=new JLabel();
    JLabel currentEmail2= new JLabel();
    JLabel currentPKK2=new JLabel();
    JLabel currentPesel2=new JLabel();
    JTextField newName= new JTextField(20);
    JTextField newSurname= new JTextField(20);
    JTextField newEmail= new JTextField(20);
    JTextField newPKK= new JTextField(20);
    JTextField newPesel= new JTextField(20);
    JButton submit= new JButton("Zapisz");


    UserPanel(KursanciEntity current) {
        this.current = current;
        submit.addActionListener(this);
        info.setText("Zakładka edycji danych użytkownika");
        info.setFont(new Font("Serif", Font.PLAIN, 20));
        currentName.setText("Aktualne imie: " + current.getImie());
        currentName.setFont(new Font("Serif", Font.PLAIN, 15));
        currentSurname.setText("Aktualne nazwisko: " + current.getNazwisko());
        currentSurname.setFont(new Font("Serif", Font.PLAIN, 15));
        currentEmail.setText("Aktualny email: " + current.getEmail());
        currentEmail.setFont(new Font("Serif", Font.PLAIN, 15));
        currentPKK.setText("Aktualne PKK: " + current.getPkk());
        currentPKK.setFont(new Font("Serif", Font.PLAIN, 15));
        currentPesel.setText("Aktualny Pesel: " + current.getPesel());
        currentPesel.setFont(new Font("Serif", Font.PLAIN, 15));
        currentName2.setText("nowe imie:  ");
        currentName2.setFont(new Font("Serif", Font.PLAIN, 15));
        currentSurname2.setText("nowe nazwisko: ");
        currentSurname2.setFont(new Font("Serif", Font.PLAIN, 15));
        currentEmail2.setText("nowy email: ");
        currentEmail2.setFont(new Font("Serif", Font.PLAIN, 15));
        currentPKK2.setText("nowe PKK: " );
        currentPKK2.setFont(new Font("Serif", Font.PLAIN, 15));
        currentPesel2.setText("nowy Pesel ");
        currentPesel2.setFont(new Font("Serif", Font.PLAIN, 15));
        newName.setEditable(true);
        newSurname.setEditable(true);
        newEmail.setEditable(true);
        newPKK.setEditable(true);

        this.add(info);
        this.add(currentName);
        this.add(currentName2);
        this.add(newName);
        this.add(currentSurname);
        this.add(currentSurname2);
        this.add(newSurname);
        this.add(currentEmail);
        this.add(currentEmail2);
        this.add(newEmail);
        this.add(currentPKK);
        this.add(currentPKK2);
        this.add(newPKK);
        this.add(currentPesel);
        this.add(currentPesel2);
        this.add(newPesel);
        this.add(submit);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source==submit){
            JPasswordField pwd = new JPasswordField(10);
            int action = JOptionPane.showConfirmDialog(this, pwd,"Podaj hasło",JOptionPane.OK_CANCEL_OPTION);
            if((String.valueOf(pwd.getPassword()).equals(current.getHaslo()))&& action==0){


                KursanciEntity re= new KursanciEntity();
                re.setImie(newName.getText());
                re.setNazwisko(newSurname.getText());
                re.setEmail(newEmail.getText());
                re.setPkk(newPKK.getText());
                re.setPesel(newPesel.getText());
                re.setHaslo(current.getHaslo());
                re.setDataRejestracji(current.getDataRejestracji());
                kc.update(re,current.getKursantId());
                JOptionPane.showMessageDialog(this, "Zmiany zostały zapisane, będą widoczne po ponownym zalogowaniu");
            }
            else if(action==0) JOptionPane.showMessageDialog(this, "Błędne hasło");
        }
    }
}