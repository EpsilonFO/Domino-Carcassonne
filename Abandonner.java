import javax.swing.*;
import java.awt.*;

public class Abandonner extends JFrame {
    public JButton oui, non;
    public JPanel abandonner, boutons, buttonPanel;
    public View view;
    public Abandonner(View v){
        abandonner = new JPanel(new BorderLayout());
        buttonPanel = new JPanel(new FlowLayout());
        this.setTitle("Abandonner");
        this.setSize(700, 200);
        oui = new JButton("Oui");
        view = v;
        oui.addActionListener((event) -> {
            view.model.deleteJoueur(view.model.joueurCourant);
            if (view.model.joueurs.length==1) {
            view.vainqueur = view.model.joueurs[0];
            JOptionPane.showMessageDialog(null, "Le "+view.vainqueur.getNom()+" a gagné avec "+ view.vainqueur.getPoints() + " point(s) !");
            System.exit(0);
            }
            else {
                view.model.joueurCourant = view.model.joueurs[0];
                view.miseAJour();
            }
            this.setVisible(false);
        });
        non = new JButton("Non");
        non.addActionListener((event) -> {
            this.setVisible(false);
        });
        boutons = new JPanel(new BorderLayout());
        buttonPanel.add(oui);
        buttonPanel.add(non);
        abandonner.add(new JLabel(v.model.joueurCourant.getNom()+ ", voulez-vous vraiment abandonner ?", SwingConstants.CENTER), BorderLayout.NORTH);
        abandonner.add(buttonPanel, BorderLayout.SOUTH);
        getContentPane().add(abandonner);
    }
}
