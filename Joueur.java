public class Joueur {
    private String nom;
    private int points;
    public static int count = 1;
    private int pion;
    public Joueur(){
        this.nom = "Joueur"+count;
        count++;
        points = 0;
        pion = 2;
    }
    public void setNom(String nom){
        this.nom = nom;
    }
    public String getNom(){
        return this.nom;
    }
    public int getPoints(){
        return this.points;
    }
    public void addPoints(int points){
        this.points += points;
    }
    public int getPion(){
        return this.pion;
    }
    public void removePion(){
        this.pion--;
    }
    public String toString(){
        return this.nom;
    }
}
