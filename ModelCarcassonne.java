import java.util.Collections;

public class ModelCarcassonne extends Model {
    public Pion [] pionPlaces = new Pion[40];
    public ModelCarcassonne(int nbJoueurs){
        super(nbJoueurs);
        sac.clear();
        for(int i=0;i<2;i++){
            sac.add(new TuileCarcassonne("pre", "route", "pre", "pre", "abbaye", "images/chemin-abbaye.png"));
        }
        for(int i=0;i<4;i++){
            sac.add(new TuileCarcassonne("pre", "pre", "pre", "pre", "abbaye", "images/pre-abbaye.png"));
        }
        sac.add(new TuileCarcassonne("ville", "ville", "ville", "ville", "ville", "images/forteresse.png"));
        for(int i=0;i<4;i++){
            sac.add(new TuileCarcassonne("route", "route", "pre", "ville", "route", "images/route-forteresse.png"));
        }
        for(int i=0;i<5;i++){
            sac.add(new TuileCarcassonne("ville", "pre", "pre", "pre", "pre", "images/pre-forteresse.png"));
        }
        for(int i=0;i<3;i++){
            sac.add(new TuileCarcassonne("pre", "pre", "ville", "ville", "ville", "images/pre-forteresse-pre.png"));
        }
        for(int i=0;i<3;i++){
            sac.add(new TuileCarcassonne("pre", "pre", "ville", "ville", "pre", "images/forteresse-pre-forteresse.png"));
        }
        for(int i=0;i<2;i++){
            sac.add(new TuileCarcassonne("pre", "ville", "pre", "ville", "pre", "images/pre2-forteressecoin2.png"));
        }
        for(int i=0;i<3;i++){
            sac.add(new TuileCarcassonne("ville", "route", "pre", "route", "pre", "images/coinroute-forteresse.png"));
        }
        for(int i=0;i<3;i++){
            sac.add(new TuileCarcassonne("route", "pre", "route", "ville", "pre", "images/coinroute2-forteresse.png"));
        }
        for(int i=0;i<3;i++){
            sac.add(new TuileCarcassonne("route", "route", "route", "ville", "route", "images/croisement-forteresse.png"));
        }
        for(int i=0;i<5;i++){
            sac.add(new TuileCarcassonne("ville", "pre", "ville", "pre", "pre", "images/pre2-forteresse2.png"));
        }
        for(int i=0;i<5;i++){
            sac.add(new TuileCarcassonne("ville", "route", "ville", "route", "pre", "images/forteresse2-route.png"));
        }
        for(int i=0;i<4;i++){
            sac.add(new TuileCarcassonne("ville", "pre", "ville", "ville", "route", "images/forteresse3-pre.png"));
        }
        for(int i=0;i<3;i++){
            sac.add(new TuileCarcassonne("ville", "route", "ville", "ville", "ville", "images/forteresse-route.png"));
        }
        for(int i=0;i<8;i++){
            sac.add(new TuileCarcassonne("route", "route", "pre", "pre", "route", "images/route.png"));
        }
        for(int i=0;i<9;i++){
            sac.add(new TuileCarcassonne("pre", "route", "route", "pre", "route", "images/coinroute.png"));
        }
        for(int i=0;i<4;i++){
            sac.add(new TuileCarcassonne("pre", "route", "route", "route", "route", "images/croisement.png"));
        }
        sac.add(new TuileCarcassonne("route", "route", "route", "route", "route", "images/croisement2.png"));
        Collections.shuffle(sac);
    }
    @Override
    public boolean ajouterOK(Case c){                                              // Fonction permettant de vérifier si une tuile peut être ajoutée à une case
        if (c.gauche !=null && c.gauche.tuile==null && c.droite!=null && c.droite.tuile==null && c.haut!=null && c.haut.tuile==null && c.bas!=null && c.bas.tuile==null) return false;
        if(c.haut!=null && c.haut.tuile!=null){
            if(!tabEgaux(c.tuile.tabH,c.haut.tuile.tabB)) {
                return false;
            }
        }
        if(c.bas!=null && c.bas.tuile!=null){
            if(!tabEgaux(c.tuile.tabB,c.bas.tuile.tabH)) {
                return false;
            }
        }
        if(c.gauche!=null && c.gauche.tuile!=null){
            if(!tabEgaux(c.tuile.tabG,c.gauche.tuile.tabD)) {
                return false;
            }
        }
        if(c.droite!=null && c.droite.tuile!=null){
            if(!tabEgaux(c.tuile.tabD,c.droite.tuile.tabG)) {
                return false;
            }
        }
        nextJoueur();
        return true;
    }
    public void ajouterPionPlace(Pion p){
        for(int i=0;i<pionPlaces.length;i++){
            if(pionPlaces[i]==null) {
                pionPlaces[i]=p;
                return;
            }
        }
    }
}
