import java.util.Random;

public class TuileDomino extends Tuile {
    @SuppressWarnings("unchecked")
    public TuileDomino(){
        tabG = new Integer [3];
        tabD = new Integer [3];
        tabH = new Integer [3];
        tabB = new Integer [3];
        Random r = new Random();
        for(int i=0;i<3;i++){
            tabG[i] = r.nextInt(4);
            tabD[i] = r.nextInt(4);
            tabH[i] = r.nextInt(4);
            tabB[i] = r.nextInt(4);
        }
    }
    @Override
    @SuppressWarnings("unchecked")
    public void tourner(){                      // Fonction permettant de tourner une tuile de domino
        Object [] temp = this.tabD;
        this.tabD = this.tabH;
        this.tabH = this.tabG;
            Object t = this.tabH[0];
            this.tabH[0] = this.tabH[2];
            this.tabH[2] = t;
        this.tabG = this.tabB;
        this.tabB = temp;
            t = this.tabB[0];
                this.tabB[0] = this.tabB[2];
                this.tabB[2] = t;
    }

    public String toString (){
        String s ="";
        s+=" ";
        for (int i=0; i<3; i++) {
            s += tabH[i] + " ";
        }
        s+=" \n";
        for (int i=0; i<3; i++) {
            s+=tabG[i] + "      " + tabD[i] + "\n";
        }
        s+=" ";
        for (int i=0; i<3; i++) {
            s += tabB[i] + " ";
        }
        s+=" ";
        return s;
    }
}