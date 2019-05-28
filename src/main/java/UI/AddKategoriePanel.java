package UI;

import Controlers.*;
import Entities.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

class AddKategoriePanel extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1;
    JLabel kwotaLabel = new JLabel("Wybierz kategorie: ");
    JComboBox<String> kategoria = new JComboBox<String>();
    JLabel kursantLabel = new JLabel("Wybierz instruktora");
    JComboBox<String> instructor = new JComboBox<String>();
    JLabel komunikat = new JLabel("<html><font color='red'>Podaj wszystkie dane.</html>");
    JButton register = new JButton("Zarejestruj");
    JButton cancel = new JButton("Anuluj");
    KategoriePanel panel;

    AddKategoriePanel(KategoriePanel panel) {
        this.panel = panel;
        register.addActionListener(this);
        cancel.addActionListener(this);
        InstruktorzyControler kc = new InstruktorzyControler();
        List<InstruktorzyEntity> instruktorzy = kc.getAll();
        for (InstruktorzyEntity i : instruktorzy) {
            instructor.addItem(i.getImieNazwisko());
        }
        this.add(kwotaLabel);
        this.add(kategoria);
        KategorieControler kc2 = new KategorieControler();
        List<KategorieEntity> instruktorzy2 = kc2.getAll();
        for (KategorieEntity i : instruktorzy2) {
            kategoria.addItem(i.getSymbol());
        }
        this.add(kursantLabel);
        this.add(instructor);
        this.add(register);
        this.add(cancel);
        this.add(komunikat);
        komunikat.setVisible(false);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == register) {
            KategorieInstruktorowEntity kic = new KategorieInstruktorowEntity();
            KategorieControler kc = new KategorieControler();
            KategorieInstruktorowControler kic2 = new KategorieInstruktorowControler();
            InstruktorzyControler ic = new InstruktorzyControler();
            System.out.println("DODAJE " + kc.getIDbyname(kategoria.getSelectedItem().toString()) + " \n" + ic.getByFS(instructor.getSelectedItem().toString()).getInstruktorId());
            kic.setKategoriaId(kc.getIDbyname(kategoria.getSelectedItem().toString()));
            kic.setInstructorId(ic.getByFS(instructor.getSelectedItem().toString()).getInstruktorId());
            kic2.add(kic);
            JOptionPane.showMessageDialog(this, "Kategoria została dodana!");
            panel.refreshList();
            Window win = SwingUtilities.getWindowAncestor(this);
            ((Window) win).dispose();
        } else if (source == cancel) {
            Window win = SwingUtilities.getWindowAncestor(this);
            ((Window) win).dispose();

        }


    }

}

