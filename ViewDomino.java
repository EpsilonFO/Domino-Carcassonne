import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class ViewDomino extends View {

    public ViewDomino(int player, int bot, Model m){
        super(player, bot, m);
        ImageIcon img = new ImageIcon("images/domino.png");
        setIconImage(img.getImage());
        setTitle("Dominos");
        grid.setHgap(5);
        grid.setVgap(5);
        Random r = new Random();
        model.plateau[r.nextInt(1,11)][r.nextInt(1,11)].addTuile(model.sac.removeLast());
    }
    @Override
    public JPanel afficherTuile(JPanel j){
        j = new JPanel(new BorderLayout());
        tuilePiochee = (TuileDomino) super.tuilePiochee;
        JLabel gaucheAff = new JLabel(), droiteAff = new JLabel(), hautAff = new JLabel(), basAff = new JLabel();
        hautAff = new JLabel(String.valueOf("       "+tuilePiochee.tabH[0])+"   "+String.valueOf(tuilePiochee.tabH[1])+"  "+String.valueOf(tuilePiochee.tabH[2])+"       ");
        gaucheAff = new JLabel("<html>"+String.valueOf(tuilePiochee.tabG[0])+"<br>"+String.valueOf(tuilePiochee.tabG[1])+"<br>"+String.valueOf(tuilePiochee.tabG[2])+"</html>");
        droiteAff = new JLabel("<html>"+String.valueOf(tuilePiochee.tabD[0])+"<br>"+String.valueOf(tuilePiochee.tabD[1])+"<br>"+String.valueOf(tuilePiochee.tabD[2])+"</html>");
        basAff = new JLabel(String.valueOf("       "+tuilePiochee.tabB[0])+"   "+String.valueOf(tuilePiochee.tabB[1])+"  "+String.valueOf(tuilePiochee.tabB[2])+"       ");
        j.add(hautAff,BorderLayout.NORTH);
        hautAff.setHorizontalAlignment(JLabel.CENTER);
        j.add(droiteAff,BorderLayout.EAST);
        j.add(gaucheAff,BorderLayout.WEST);
        j.add(basAff,BorderLayout.SOUTH);
        basAff.setHorizontalAlignment(JLabel.CENTER);
        j.setBackground(Color.WHITE);
        j.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        return j;
    }

}
