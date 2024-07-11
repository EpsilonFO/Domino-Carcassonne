public class Pion {
    public String emplacement;
    public Joueur joueur;
    public int numeroCase;
    public Pion(String x, Joueur nomJoueur, int numeroCase){
        this.emplacement = x;
        this.joueur = nomJoueur;
        this.numeroCase = numeroCase;
    }
}
