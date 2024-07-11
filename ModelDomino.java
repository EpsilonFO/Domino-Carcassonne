public class ModelDomino extends Model {
    public ModelDomino(int nbJoueurs){
        super(nbJoueurs);
        sac.clear();
        for(int i=0;i<20;i++){
            sac.add(new TuileDomino());
        }
    }
    @Override
    public boolean ajouterOK(Case c){                                              // Fonction permettant de vérifier si une tuile peut être ajoutée à une case
        int points = 0;
        if (c.gauche !=null && c.gauche.tuile==null && c.droite!=null && c.droite.tuile==null && c.haut!=null && c.haut.tuile==null && c.bas!=null && c.bas.tuile==null) return false;
        if(c.haut!=null && c.haut.tuile!=null){
            if(!tabEgaux(c.tuile.tabH,c.haut.tuile.tabB)) {
                return false;
            }else{
                for(int i=0;i<3;i++){
                    points+=Integer.parseInt((String.valueOf(c.tuile.tabH[i])));
                }
            }
        }
        if(c.bas!=null && c.bas.tuile!=null){
            if(!tabEgaux(c.tuile.tabB,c.bas.tuile.tabH)) {
                return false;
            }else{
                for(int i=0;i<3;i++){
                    points+=Integer.parseInt((String.valueOf(c.tuile.tabB[i])));
                }
            }
        }
        if(c.gauche!=null && c.gauche.tuile!=null){
            if(!tabEgaux(c.tuile.tabG,c.gauche.tuile.tabD)) {
                return false;
            }else{
                for(int i=0;i<3;i++){
                    points+=Integer.parseInt((String.valueOf(c.tuile.tabG[i])));
                }
            }
        }
        if(c.droite!=null && c.droite.tuile!=null){
            if(!tabEgaux(c.tuile.tabD,c.droite.tuile.tabG)) {
                return false;
            }else{
                for(int i=0;i<3;i++){
                    points+=Integer.parseInt((String.valueOf(c.tuile.tabD[i])));
                }
            }
        }
        joueurCourant.addPoints(points);
        nextJoueur();
        return true;
    }
}
