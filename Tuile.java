import javax.swing.*;

public class Tuile<T> {
    public T [] tabG, tabD, tabH, tabB;
    public JPanel tuileAff;
    public void tourner(){                      // Fonction permettant de tourner une tuile
        T [] temp = this.tabD;
        this.tabD = this.tabH;
        this.tabH = this.tabG;
        this.tabG = this.tabB;
        this.tabB = temp;
    }
}