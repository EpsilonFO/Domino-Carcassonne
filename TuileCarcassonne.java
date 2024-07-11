import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class TuileCarcassonne extends Tuile {
    public BufferedImage image;
    public Pion pion;
    public String [] tabC;
    @SuppressWarnings("unchecked")
    public TuileCarcassonne(String haut, String bas, String gauche, String droite, String centre, String image){
        super();
        this.tabC = new String[1];
        this.tabH = new String[1];
        this.tabB = new String[1];
        this.tabG = new String[1];
        this.tabD = new String[1];
        this.tabH[0] = haut;
        this.tabB[0] = bas;
        this.tabG[0] = gauche;
        this.tabD[0] = droite;
        this.tabC[0] = centre;
        try{
            this.image = ImageIO.read(new File(image));
        }catch(IOException e){
            System.out.println("Erreur de lecture de l'image");
        }
        pion = null;
    }
    public void addPion(Pion p){
        this.pion = p;
    }
}
