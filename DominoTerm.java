import java.util.Random;
import java.util.Scanner;

public class DominoTerm {
    public ModelDomino model;
    public Tuile tuilePiochee;

    public DominoTerm(int player, int bot, ModelDomino model) {
        this.model = model;
        model.joueurs = new Joueur[player];
        for (int i = 0; i < player-bot; i++) {
            Joueur p = new Joueur();
            p.setNom("Joueur "+(i+1));
            model.joueurs[i] = p;
        }
        for (int i=0;i<bot;i++) {
            Joueur b = new Joueur();
            b.setNom("Bot "+(i+1));
            model.joueurs[player-bot+i] = b;
        }
        model.joueurCourant = model.joueurs[0];
        Random r = new Random();
        model.plateau[r.nextInt(1,11)][r.nextInt(1,11)].tuile = model.sac.removeLast();
        afficherPlateau();
        while(!(model.jeuFini()) || !(model.plateaurempli())) {
            if (model.joueurCourant.getNom().contains("Bot")) {
                botJouer();
            }
            else {
                System.out.println(model.joueurCourant.getNom()+ " : " + model.joueurCourant.getPoints() +" points; souhaitez-vous <piocher> ou <abandonner> ?");
                Scanner sc = new Scanner(System.in);
                String s = sc.next();
                if (s.equals("abandonner")) {
                    model.deleteJoueur(model.joueurCourant);
                    if (model.joueurs.length==1) {
                        Joueur vainqueur = model.joueurs[0];
                        System.out.println("Le "+vainqueur.getNom()+" a gagné avec "+ vainqueur.getPoints() + " point(s) !");
                        System.exit(0);
                        }
                        else {
                            model.joueurCourant = model.joueurs[0];
                        }
                }
                else if (s.equals("piocher")) {
                    piocher();
                    boolean b = false;
                    while (!b) {
                        System.out.println("Voulez-vous <placer>, <tourner> ou <passer> ?");
                        String ss = sc.next();
                        if (ss.equals("passer")) {
                            model.nextJoueur();
                            b = true;
                        }
                        else if (ss.equals("tourner")) {
                            tuilePiochee.tourner();
                            System.out.println("Tuile tournée : \n"+tuilePiochee);
                        }
                        else if (ss.equals("placer")) {
                            System.out.println("Sur quelle case voulez-vous placer la tuile ? (x,y)");
                            int x = sc.nextInt();
                            int y = sc.nextInt();
                            if (model.plateau[x][y].tuile!=null) {
                                System.out.println("Cette case est déjà occupée !");
                            }
                            else {
                                model.plateau[x][y].tuile = tuilePiochee;
                                if (model.ajouterOK(model.plateau[x][y])) {
                                    b = true;
                                }
                                else {
                                    model.plateau[x][y].tuile=null;
                                    System.out.println("Tuile non plaçable !");
                                }
                            }
                        }
                        else {
                            System.out.println("Commande non reconnue !");
                        }
                    }
                }
                else {
                    System.out.println("Commande non reconnue !");
                }
                afficherPlateau();
            }
        }
        finJeu();
    }

    public void piocher() {
        tuilePiochee = model.sac.removeLast();
        System.out.println("Tuile piochée : \n"+tuilePiochee);
    }

    public void afficherPlateau() {
        for (int i=1; i<model.plateau.length-1; i++) {
            for (int j=1; j<model.plateau[0].length-1; j++) {
                if (model.plateau[i][j].tuile==null) {
                    System.out.print("          ");
                }
                else {
                    System.out.print(" "+model.plateau[i][j].tuile.tabH[0]+"  "+model.plateau[i][j].tuile.tabH[1]+"   "+model.plateau[i][j].tuile.tabH[2]+" ");
                }
            }
            System.out.println();
            for (int j=1; j<model.plateau[0].length-1; j++) {
                if (model.plateau[i][j].tuile==null) {
                    System.out.print("          ");
                }
                else {
                    System.out.print(""+model.plateau[i][j].tuile.tabG[2]+"        "+model.plateau[i][j].tuile.tabD[0]+"");
                }
            }
            System.out.println();
            for (int j=1; j<model.plateau[0].length-1; j++) {
                Case c = model.plateau[i][j];
                int num = c.num;
                if (model.plateau[i][j].tuile==null) {
                    if (num<100) System.out.print("    " +num+"    ");
                    else System.out.print("   " +num+"    ");
                }
                else {
                    System.out.print(model.plateau[i][j].tuile.tabG[1]+"        "+model.plateau[i][j].tuile.tabD[1]);
                }
            }
            System.out.println();
            for (int j=1; j<model.plateau[0].length-1; j++) {
                if (model.plateau[i][j].tuile==null) {
                    System.out.print("          ");
                }
                else {
                    System.out.print(""+model.plateau[i][j].tuile.tabG[0]+"        "+model.plateau[i][j].tuile.tabD[2]+"");
                }
            }
            System.out.println();
            for (int j=1; j<model.plateau[0].length-1; j++) {
                if (model.plateau[i][j].tuile==null) {
                    System.out.print("          ");
                }
                else {
                    System.out.print(" "+model.plateau[i][j].tuile.tabB[2]+"  "+model.plateau[i][j].tuile.tabB[1]+"   "+model.plateau[i][j].tuile.tabB[0]+" ");
                }
            }
            System.out.println();                      
        }
    }
    public void botJouer(){
        if(model.sac.isEmpty()){
            finJeu();
        }else {
            tuilePiochee = (TuileDomino) model.sac.removeLast();
            boolean bool = false;
            String nom = model.joueurCourant.getNom();
            int x = 1000;
            for(int a=0;a<x;a++){
                int z = new Random().nextInt(1,11);
                int y = new Random().nextInt(1,11);
                int t = new Random().nextInt(1,6);
                for(int b=1;b<t;b++){
                    tuilePiochee.tourner();
                }
                if(model.plateau[z][y].tuile != null){
                    continue;
                }else{
                    model.plateau[z][y].tuile = tuilePiochee;
                    if(model.ajouterOK(model.plateau[z][y])){
                        afficherPlateau();
                        System.out.println(nom+" a placé sa tuile.");
                        a=x;
                        bool = true;
                    }else{
                        model.plateau[z][y].tuile = null;
                    }
                }
            }
            if(!bool){
                System.out.println(nom+" n'a pas réussi à placer sa tuile.");
                model.nextJoueur();
            }
        }
    }

    public void finJeu() {
        System.out.println("Fin du jeu !");
        int max = 0;
        for (Joueur j : model.joueurs) {
            if (j.getPoints() > max) {
                max = j.getPoints();
            }
        }
        for (Joueur j : model.joueurs) {
            if (j.getPoints() == max) {
                System.out.println("Le gagnant est "+j.getNom()+" avec "+j.getPoints()+" points !");
            }
        }
        System.exit(0);
    }
}
