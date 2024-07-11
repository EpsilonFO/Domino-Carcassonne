import java.util.Random;
import javax.swing.*;
import java.awt.*;

public class ViewCarcassonne extends View {
    public ViewCarcassonne(int players, int bot, Model m){
        super(players, bot, m);
        ImageIcon img = new ImageIcon("images/carcassonneImg.png");
        setIconImage(img.getImage());
        setTitle("Carcassonne");
        Random r = new Random();
        model.plateau[r.nextInt(1,11)][r.nextInt(1,11)].addTuile(model.sac.removeLast());
        plateau.setBackground(Color.WHITE);
    }
    @Override
    public void miseAJour(){
        panel2.removeAll();
        for (int i=0;i<model.joueurs.length;i++){
            JLabel joueur = new JLabel(model.joueurs[i].getNom()+" : "+model.joueurs[i].getPoints());
            panel2.add(joueur);
        }
        ModelCarcassonne m = (ModelCarcassonne) model;
        for(int a=0;a<m.pionPlaces.length;a++){
            if(m.pionPlaces[a]!=null && m.pionPlaces[a].joueur.equals(model.joueurCourant)){
                JLabel pion = new JLabel("Pion "+m.pionPlaces[a].emplacement+" en case "+m.pionPlaces[a].numeroCase);
                panel10.add(pion, BorderLayout.WEST);
            }
        }
        panel7.removeAll();
        panel7.add(new JLabel(model.joueurCourant.getNom()+", à ton tour de jouer !", SwingConstants.CENTER), BorderLayout.CENTER);
        panel2.revalidate();
        panel2.repaint();
        panel7.revalidate();
        panel7.repaint();
    }
}