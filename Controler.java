public class Controler {
    Model model;
    View view;
    public Controler(Model m, View v){
        model=m;
        view=v;
    }
    public boolean valider(int i, int j){
        if(view.tuilePiochee!=null && model.ajouterOK(model.plateau[i][j])){
            model.plateau[i][j].addTuile(view.tuilePiochee);
            view.miseAJour();
            return true;
        }
        return false;
    }
}
