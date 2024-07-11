import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.*;

public class Case {
    public Case haut,bas,gauche,droite;
    public Tuile tuile;
    public JPanel panel;
    public int num;
    public static int count = 1;

    public Case(int num) {
        this.haut=null;
        this.bas=null;
        this.gauche=null;
        this.droite=null;
        this.tuile=null;
        this.num=num;
        count++;
        panel = new JPanel(new BorderLayout());
        JPanel numero = new JPanel(new FlowLayout());
        JLabel affNum = new JLabel(String.valueOf(num));
        affNum.setForeground(Color.WHITE);
        numero.add(affNum);
        numero.setBackground(Color.BLACK);
        panel.add(numero, BorderLayout.CENTER);
    }
    public void addTuile(Tuile t){
        if(t instanceof TuileDomino){
            panel.removeAll();
            this.tuile=t;
            JLabel gaucheAff = new JLabel(), droiteAff = new JLabel(), hautAff = new JLabel(), basAff = new JLabel(), centerAff = new JLabel();
            hautAff = new JLabel(String.valueOf(t.tabH[0])+" "+String.valueOf(t.tabH[1])+" "+String.valueOf(t.tabH[2]));
            gaucheAff = new JLabel("<html>"+String.valueOf(t.tabG[0])+"<br>"+String.valueOf(t.tabG[1])+"<br>"+String.valueOf(t.tabG[2])+"</html>");
            droiteAff = new JLabel("<html>"+String.valueOf(t.tabD[0])+"<br>"+String.valueOf(t.tabD[1])+"<br>"+String.valueOf(t.tabD[2])+"</html>");
            basAff = new JLabel(String.valueOf(t.tabB[0])+" "+String.valueOf(t.tabB[1])+" "+String.valueOf(t.tabB[2]));
            panel.add(hautAff,BorderLayout.NORTH);
            hautAff.setHorizontalAlignment(JLabel.CENTER);
            panel.add(droiteAff,BorderLayout.EAST);
            panel.add(gaucheAff,BorderLayout.WEST);
            panel.add(basAff,BorderLayout.SOUTH);
            panel.add(centerAff, BorderLayout.CENTER);
            basAff.setHorizontalAlignment(JLabel.CENTER);
            panel.setBackground(Color.WHITE);
        }else if(t instanceof TuileCarcassonne){
            panel.removeAll();
            TuileCarcassonne test = (TuileCarcassonne) t;
            this.tuile=t;
            ImageIcon image = new ImageIcon(test.image);
            panel.add(new JLabel(image));
        }
    }

    public void addCaseHaut(Case haut) {
        this.haut=haut;
    }

    public void addCaseBas(Case bas) {
        this.bas=bas;
    }

    public void addCaseGauche(Case gauche) {
        this.gauche=gauche;
    }

    public void addCaseDroite(Case droite) {
        this.droite=droite;
    }
    
}
