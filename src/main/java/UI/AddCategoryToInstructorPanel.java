package UI;

import Controlers.InstruktorzyControler;
import Controlers.KategorieControler;
import Controlers.KategorieInstruktorowControler;
import Entities.InstruktorzyEntity;
import Entities.KategorieEntity;
import Entities.KategorieInstruktorowEntity;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

class AddCategoryToInstructorPanel extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1;
    JLabel wyborLabel = new JLabel("Wybierz kategorie:");
    JList wybor = new JList();
    JButton register = new JButton("Dodaj");
    JButton cancel = new JButton("Anuluj");
    InstruktorzyEntity current;
    InstruktorzyPanel panel;
    int[] wybrane = new int[10];
    AddCategoryToInstructorPanel(Long ID, InstruktorzyPanel panel) {
        this.panel = panel;

        InstruktorzyControler ic = new InstruktorzyControler();
        current = ic.getByID(ID);
        register.addActionListener(this);
        KategorieControler kc = new KategorieControler();
        cancel.addActionListener(this);
        List<KategorieEntity> kategorie = kc.getAll();
        String[] pozycje=new String[kategorie.size()];
        for (int i=0;i<kategorie.size();i++)
        {
           pozycje[i]=(kategorie.get(i).getSymbol());
        }
        wybor=new JList(pozycje);

            this.add(wyborLabel);
        this.add(wybor);
        this.add(register);
        this.add(cancel);

        int x=0;
        Collection<KategorieInstruktorowEntity> kolekcja = current.getKategorieInstruktorowsByInstruktorId();

        for(KategorieInstruktorowEntity kie: kolekcja) {
            kc.getByID(kie.getKategoriaId()).getSymbol();
            for (int i = 0; i < pozycje.length; i++)
                if (pozycje[i].equals(kc.getByID(kie.getKategoriaId()).getSymbol())) {
                    wybrane[x] = i;
                    x++;
                }
        }
        wybor.setSelectedIndices(wybrane);



        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == cancel) {
            Window win = SwingUtilities.getWindowAncestor(this);
            ((Window) win).dispose();
        }
        boolean mozna = true;
        if (source == register) {
            List<String> wybrane2 = wybor.getSelectedValuesList();
            for(int i=0;i<wybrane2.size();i++){
                mozna=true;
                for(int j=0;j<wybrane.length;j++){
                    if(wybrane[j]==wybrane[i])
                        mozna=false;
                }
                if(mozna){
                KategorieInstruktorowControler kic = new KategorieInstruktorowControler();
                KategorieInstruktorowEntity kie = new KategorieInstruktorowEntity();
                KategorieControler kc = new KategorieControler();
                kie.setInstructorId(current.getInstruktorId());
                System.out.println(kc.getIDbyname(wybrane2.get(i)));
                kie.setKategoriaId(kc.getIDbyname(wybrane2.get(i)));
                kic.add(kie);}
            }
            JOptionPane.showMessageDialog(this,"Kategorie zostaly dopisane");
            panel.refreshList();
            Window win = SwingUtilities.getWindowAncestor(this);
            ((Window) win).dispose();

            }



    }
}