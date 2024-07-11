import java.util.LinkedList;

public class Model {
    public LinkedList<Tuile> sac;
    public Case [][] plateau;
    public Joueur [] joueurs;
    public Joueur joueurCourant;
    public Model(int nbJoueur){
        sac = new LinkedList<Tuile>();
        joueurs = new Joueur[nbJoueur];
        plateau = new Case[12][12];
        for(int i=0;i<plateau.length;i++){
            for(int j=0;j<plateau[i].length;j++){
                int number = 0;
                if (j%10!=0) {
                String num = String.valueOf(i)+String.valueOf(j);
                number = Integer.parseInt(num);
                }else {
                String num = String.valueOf(i)+String.valueOf(9);
                number = Integer.parseInt(num)+1;
            }
                plateau[i][j] = new Case(number);
            }
        }
        for(int i=1;i<plateau.length-1;i++){
            for(int j=1;j<plateau[i].length-1;j++){
                if(plateau[i][j+1]!=null){
                    plateau[i][j].addCaseDroite(plateau[i][j+1]);
                }
                if(plateau[i+1][j]!=null){
                    plateau[i][j].addCaseBas(plateau[i+1][j]);
                }
                if(plateau[i-1][j]!=null){
                    plateau[i][j].addCaseHaut(plateau[i-1][j]);
                }
                if(plateau[i][j-1]!=null){
                    plateau[i][j].addCaseGauche(plateau[i][j-1]);
                }
            }
        }
        for(int i=0;i<20;i++){
            sac.add(new TuileDomino());
        }
    }
    public boolean tabEgaux(Object []t1, Object [] t2){                           // Fonction permettant de comparer deux tableaux
        if(t1.length!=t2.length) return false;
        for(int i=0;i<t1.length;i++){
            if(!(t1[i].equals(t2[i]))) {
                return false;
            }
        }
        return true;
    }
    public boolean ajouterOK(Case c){                             // Fonction permettant de vérifier si une tuile peut être ajoutée à une case
        return false;
    }
    public void nextJoueur() {                                  // Fonction permettant de passer au joueur suivant
        if(joueurCourant==joueurs[joueurs.length-1]){
            joueurCourant=joueurs[0];
        }else{
            for(int i=0;i<joueurs.length-1;i++){
                if(joueurCourant==joueurs[i]){
                    joueurCourant=joueurs[i+1];
                    break;
                }
            }
        }
    }
    public void deleteJoueur(Joueur j) {                        // Fonction permettant de supprimer un joueur du tableau de joueur
        int x=0; 
        for (int i=0; i<joueurs.length; i++) {
            if (joueurs[i]==j) {
                joueurs[i]=null;
            }
        }
        for (int i=0; i<joueurs.length; i++) {
            if (joueurs[i]!=null) {
                x++;
            }
        }
        Joueur [] joueurs2 = new Joueur[x];
        int y=0;
        for (int i=0; i<joueurs.length; i++) {
            if (joueurs[i]!=null) {
                joueurs2[y]=joueurs[i];
                y++;
            }
        }
        joueurs=joueurs2;
    }

    public boolean plateaurempli() {                                // Fonction permettant de savoir si le plateau est rempli de tuiles
        for(int i=1;i<plateau.length-1;i++){
            for(int j=1;j<plateau[i].length-1;j++){
                if(plateau[i][j].tuile==null) return false;
            }
        }
        return true;
    }
    public boolean jeuFini(){                                           // Fonction permettant de savoir si le jeu est fini
        return sac.isEmpty();
    }
}
