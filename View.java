import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.util.Collections;
import java.util.LinkedList;


public class View extends JFrame {
    public JPanel plateau, stats, jeu, panel2, panel7, panel10;
    public Model model;
    public JButton piocher, poser, tourner, surrend, passer;
    public Joueur vainqueur;
    public Tuile tuilePiochee;
    public Controler controler;
    public GridLayout grid;
    public boolean b = false;
    public JRadioButton haut, bas, gauche, droite;
    public JCheckBox pion;

    public View(int player, int bot, Model m){
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int)dimension.getHeight()-30;
        int width  = (int)dimension.getWidth();
        this.setTitle("PPOO");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(width,height);
        model = m;
        controler = new Controler(model, this);
        grid = new GridLayout(10,11);
        plateau = new JPanel(grid);
        plateau.setBackground(Color.GRAY);
        plateau.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        for(int i=1;i<model.plateau.length-1;i++){
            for(int j=1;j<model.plateau[i].length-1;j++){
                Case t = model.plateau[i][j];
                plateau.add(t.panel);
            }
        }
        stats = new JPanel(new BorderLayout());
        panel2 = new JPanel();
        JPanel panel3 = new JPanel(new BorderLayout());
        JPanel panel4 = new JPanel(new FlowLayout());
        JPanel panel5 = new JPanel(new GridLayout(2,1));
        panel7 = new JPanel(new BorderLayout());
        JPanel panel8 = new JPanel(new FlowLayout());
        JPanel panel9 = new JPanel(new FlowLayout());
        panel10 = new JPanel(new GridLayout(10, 1));
        JLabel restants = new JLabel("Nombre de tuiles restantes : "+model.sac.size());
        for(int i=0;i<player-bot;i++){
            Joueur p = new Joueur();
            p.setNom("Joueur "+(i+1));
            JLabel joueur = new JLabel(p.getNom()+" : "+p.getPoints());
            panel2.add(joueur);
            model.joueurs[i] = p;
        }
        for(int i=0;i<bot;i++){
            Joueur p = new Joueur();
            p.setNom("Bot "+(i+1));
            JLabel joueur = new JLabel(p.getNom()+" : "+p.getPoints());
            panel2.add(joueur);
            model.joueurs[player-bot+i] = p;
        }
        LinkedList<Joueur> joueurs = new LinkedList<Joueur>();
        for(int i=1;i<model.joueurs.length;i++){
            joueurs.add(model.joueurs[i]);
        }
        Collections.shuffle(joueurs);
        for(int i=1;i<model.joueurs.length;i++){
            model.joueurs[i] = joueurs.get(i-1);
        }
        model.joueurCourant = model.joueurs[0];
        stats.add(panel2, BorderLayout.NORTH);
        piocher = new JButton("Piocher");
        tourner = new JButton("Tourner");
        passer = new JButton("Passer");
        tourner.setEnabled(false);
        piocher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if (model.jeuFini() || model.plateaurempli()) {
                    finJeu();
                }
                tuilePiochee = model.sac.removeLast();
                JPanel panel6 = new JPanel();
                panel9.add(afficherTuile(panel6));
                panel3.add(panel5, BorderLayout.CENTER);
                panel3.revalidate();
                panel3.repaint();
                piocher.setEnabled(false);
                restants.setText("Nombre de tuiles restantes : "+model.sac.size());
                tourner.setEnabled(true);
                JLabel ecrireCase = new JLabel("Entrez le numéro de la case que vous souhaitez remplir : ");
                JTextField numCase = new JTextField(3);
                if(model instanceof ModelCarcassonne){
                    ButtonGroup group = new ButtonGroup();
                    pion = new JCheckBox("Voulez-vous placer un pion ?");
                    haut = new JRadioButton("Haut");
                    bas = new JRadioButton("Bas");
                    gauche = new JRadioButton("Gauche");
                    droite = new JRadioButton("Droite");
                    group.add(haut);
                    group.add(bas);
                    group.add(gauche);
                    group.add(droite);
                    panel8.add(pion);
                    panel8.add(haut);
                    panel8.add(bas);
                    panel8.add(gauche);
                    panel8.add(droite);
                }
                JButton valider = new JButton("Valider");
                valider.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        int x = 0;
                        try{
                            x = Integer.parseInt(numCase.getText());
                            
                        }catch(NumberFormatException ex){}
                        if((x<11 || x>110)){
                            JOptionPane.showMessageDialog(null, "Veuillez entrer un numéro de case valide.");
                        }else{
                            int i = x/10;
                            int j;
                            if(x%10==0) {
                                j = 10;
                                i-=1;
                            }
                            else j = x%10;
                            if (model.plateau[i][j].tuile != null) JOptionPane.showMessageDialog(null, "Il y a déjà un domino sur cette case.");
                            else {
                                model.plateau[i][j].tuile = tuilePiochee;
                                Joueur joueurCour = model.joueurCourant;
                                if(controler.valider(i,j)){
                                    piocher.setEnabled(true);
                                    tourner.setEnabled(false);
                                    panel5.removeAll();
                                    panel7.removeAll();
                                    panel8.removeAll();
                                    panel9.removeAll();
                                    panel10.removeAll();
                                    panel3.revalidate();
                                    panel3.repaint();
                                    panel2.removeAll();
                                    if(model instanceof ModelCarcassonne){
                                        if(pion.isSelected()){
                                            if(model.joueurCourant.getPion()==0){
                                                JOptionPane.showMessageDialog(null, "Vous n'avez plus de pions.");
                                            }else{
                                                if(!haut.isSelected() && !bas.isSelected() && !gauche.isSelected() && !droite.isSelected()){
                                                    JOptionPane.showMessageDialog(null, "Vous n'avez pas choisi de direction, le pion ne s'est pas placé.");
                                                }else{
                                                    TuileCarcassonne t = (TuileCarcassonne) model.plateau[i][j].tuile;
                                                    ModelCarcassonne m = (ModelCarcassonne) model;
                                                    if(haut.isSelected()){
                                                        Pion p = new Pion("haut", joueurCour, x);
                                                        t.addPion(p);
                                                        m.ajouterPionPlace(p);
                                                        model.joueurCourant.removePion();
                                                    }
                                                    if(droite.isSelected()){
                                                        Pion p = new Pion("droite", joueurCour, x);
                                                        t.addPion(p);
                                                        m.ajouterPionPlace(p);
                                                        model.joueurCourant.removePion();
                                                    }
                                                    if(bas.isSelected()){
                                                        Pion p = new Pion("bas", joueurCour, x);
                                                        t.addPion(p);
                                                        m.ajouterPionPlace(p);
                                                        model.joueurCourant.removePion();
                                                    }
                                                    if(gauche.isSelected()){
                                                        Pion p = new Pion("gauche", joueurCour, x);
                                                        t.addPion(p);
                                                        m.ajouterPionPlace(p);
                                                        model.joueurCourant.removePion();
                                                    }
                                                }
                                            }
                                            
                                        }
                                    }
                                    if (model.jeuFini() || model.plateaurempli()) {
                                        finJeu();
                                    }
                                    for(int a=0;a<model.joueurs.length;a++){
                                        JLabel joueur = new JLabel(model.joueurs[a].getNom()+" : "+model.joueurs[a].getPoints());
                                        panel2.add(joueur);
                                    }
                                    if(model instanceof ModelCarcassonne){
                                        ModelCarcassonne m = (ModelCarcassonne) model;
                                        for(int a=0;a<m.pionPlaces.length;a++){
                                            if(m.pionPlaces[a]!=null && m.pionPlaces[a].joueur.equals(model.joueurCourant)){
                                                JLabel pion = new JLabel("Pion "+m.pionPlaces[a].emplacement+" en case "+m.pionPlaces[a].numeroCase);
                                                panel10.add(pion);
                                            }
                                        }
                                    }
                                    while(model.joueurCourant.getNom().contains("Bot")){
                                        botJouer();
                                    }
                                }else{
                                    model.plateau[i][j].tuile = null;
                                    JOptionPane.showMessageDialog(null, "La tuile ne peut pas être posée à cette case.");
                                }
                            }
                        }
                }
                });
                JButton passer = new JButton("Passer");
                passer.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        piocher.setEnabled(true);
                        tourner.setEnabled(false);
                        panel5.removeAll();
                        panel7.removeAll();
                        panel8.removeAll();
                        panel9.removeAll();
                        panel10.removeAll();
                        panel3.revalidate();
                        panel3.repaint();
                        if (model.jeuFini() || model.plateaurempli()) {
                            finJeu();
                        }
                        model.nextJoueur();
                        while(model.joueurCourant.getNom().contains("Bot")){
                            botJouer();
                        }
                    }
                });
                panel8.add(ecrireCase);
                panel8.add(numCase);
                panel8.add(valider);
                panel8.add(passer);
                panel7.add(new JLabel(model.joueurCourant.getNom()+", à ton tour de jouer !", SwingConstants.CENTER), BorderLayout.CENTER);
                panel7.add(panel9, BorderLayout.SOUTH);
                panel5.add(panel7);
                panel5.add(panel8);
                panel3.add(panel10, BorderLayout.WEST);
            }
        });
        tourner.addActionListener((event) -> {
            try{
                b = true;
                tuilePiochee.tourner();
                panel5.removeAll();
                panel7.removeAll();
                panel8.removeAll();
                panel9.removeAll();
                JPanel panel6 = new JPanel();
                panel9.add(afficherTuile(panel6));
                panel3.add(panel5, BorderLayout.CENTER);
                panel3.revalidate();
                panel3.repaint();
                piocher.setEnabled(false);
                restants.setText("Nombre de tuiles restantes : "+model.sac.size());
                tourner.setEnabled(true);
                JLabel ecrireCase = new JLabel("Entrez le numéro de la case que vous souhaitez remplir : ");
                JTextField numCase = new JTextField(3);
                if(model instanceof ModelCarcassonne){
                    ButtonGroup group = new ButtonGroup();
                    pion = new JCheckBox("Voulez-vous placer un pion ?");
                    haut = new JRadioButton("Haut");
                    bas = new JRadioButton("Bas");
                    gauche = new JRadioButton("Gauche");
                    droite = new JRadioButton("Droite");
                    group.add(haut);
                    group.add(bas);
                    group.add(gauche);
                    group.add(droite);
                    panel8.add(pion);
                    panel8.add(haut);
                    panel8.add(bas);
                    panel8.add(gauche);
                    panel8.add(droite);
                }
                JButton valider = new JButton("Valider");
                valider.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        int x=0;
                        try{
                            x = Integer.parseInt(numCase.getText());
                        }catch(NumberFormatException ex){}
                        if(x<11 || x>110){
                            JOptionPane.showMessageDialog(null, "Veuillez entrer un numéro de case valide.");
                        }else{
                            int i = x/10;
                            int j;
                            if(x%10==0) {
                                j = 10;
                                i-=1;
                            }
                            else j = x%10;
                            if (model.plateau[i][j].tuile != null)
                                JOptionPane.showMessageDialog(null, "Il y a déjà un domino sur cette case.");
                            else {
                            model.plateau[i][j].tuile = tuilePiochee;
                            Joueur joueurCour = model.joueurCourant;
                            if(controler.valider(i,j)){
                                piocher.setEnabled(true);
                                tourner.setEnabled(false);
                                panel5.removeAll();
                                panel7.removeAll();
                                panel8.removeAll();
                                panel9.removeAll();
                                panel10.removeAll();
                                panel3.revalidate();
                                panel3.repaint();
                                panel2.removeAll();
                                if(model instanceof ModelCarcassonne){
                                    if(pion.isSelected()){
                                        if(model.joueurCourant.getPion()==0){
                                            JOptionPane.showMessageDialog(null, "Vous n'avez plus de pions.");
                                        }else{
                                            if(!haut.isSelected() && !bas.isSelected() && !gauche.isSelected() && !droite.isSelected()){
                                                JOptionPane.showMessageDialog(null, "Vous n'avez pas choisi de direction, le pion ne s'est pas placé.");
                                            }else{
                                                TuileCarcassonne t = (TuileCarcassonne) model.plateau[i][j].tuile;
                                                ModelCarcassonne m = (ModelCarcassonne) model;
                                                if(haut.isSelected()){
                                                    Pion p = new Pion("haut", joueurCour, x);
                                                    t.addPion(p);
                                                    m.ajouterPionPlace(p);
                                                    model.joueurCourant.removePion();
                                                }
                                                if(droite.isSelected()){
                                                    Pion p = new Pion("droite", joueurCour, x);
                                                    t.addPion(p);
                                                    m.ajouterPionPlace(p);
                                                    model.joueurCourant.removePion();
                                                }
                                                if(bas.isSelected()){
                                                    Pion p = new Pion("bas", joueurCour, x);
                                                    t.addPion(p);
                                                    m.ajouterPionPlace(p);
                                                    model.joueurCourant.removePion();
                                                }
                                                if(gauche.isSelected()){
                                                    Pion p = new Pion("gauche", joueurCour, x);
                                                    t.addPion(p);
                                                    m.ajouterPionPlace(p);
                                                    model.joueurCourant.removePion();
                                                }
                                            }
                                        }
                                    }
                                }
                                if (model.jeuFini() || model.plateaurempli()) {
                                    finJeu();
                                }
                                for(int a=0;a<model.joueurs.length;a++){
                                    JLabel joueur = new JLabel(model.joueurs[a].getNom()+" : "+model.joueurs[a].getPoints());
                                    panel2.add(joueur);
                                }
                                if(model instanceof ModelCarcassonne){
                                    ModelCarcassonne m = (ModelCarcassonne) model;
                                    for(int a=0;a<m.pionPlaces.length;a++){
                                        if(m.pionPlaces[a]!=null && m.pionPlaces[a].joueur.equals(model.joueurCourant)){
                                            JLabel pion = new JLabel("Pion "+m.pionPlaces[a].emplacement+" en case "+m.pionPlaces[a].numeroCase);
                                            panel10.add(pion);
                                        }
                                    }
                                }
                                while(model.joueurCourant.getNom().contains("Bot")){
                                    botJouer();
                                }
                            }else{
                                model.plateau[i][j].tuile = null;
                                JOptionPane.showMessageDialog(null, "La tuile ne peut pas être posée à cette case.");
                            }
                        }
                    }
                }
                });
                JButton passer = new JButton("Passer");
                passer.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        piocher.setEnabled(true);
                        tourner.setEnabled(false);
                        panel5.removeAll();
                        panel7.removeAll();
                        panel8.removeAll();
                        panel9.removeAll();
                        panel10.removeAll();
                        panel3.revalidate();
                        panel3.repaint();
                        if (model.jeuFini() || model.plateaurempli()) {
                            finJeu();
                        }
                        model.nextJoueur();
                        while(model.joueurCourant.getNom().contains("Bot")){
                            botJouer();
                        }
                    }
                });
                panel8.add(ecrireCase);
                panel8.add(numCase);
                panel8.add(valider);
                panel8.add(passer);
                panel7.add(panel9, BorderLayout.SOUTH);
                panel7.add(new JLabel(model.joueurCourant.getNom()+", à ton tour de jouer !", SwingConstants.CENTER), BorderLayout.CENTER);
                panel5.add(panel7);
                panel5.add(panel8);
                panel3.add(panel10, BorderLayout.WEST);
            }catch(NullPointerException e){}
        });
        panel4.add(tourner);
        surrend = new JButton("Abandonner");
        surrend.addActionListener((event) -> {
            Abandonner a = new Abandonner(this);
            a.setVisible(true);
        });
        panel4.add(piocher);
        panel4.add(surrend);
        stats.add(restants, BorderLayout.SOUTH);
        panel3.add(panel4, BorderLayout.NORTH);
        stats.add(panel3, BorderLayout.CENTER);

        jeu = new JPanel(new GridLayout());
        jeu.add(stats, BorderLayout.WEST);
        jeu.add(plateau, BorderLayout.CENTER);
        
        getContentPane().add(jeu);
    }
    public JPanel afficherTuile(JPanel j){
        if(tuilePiochee instanceof TuileDomino){
            j = new JPanel(new BorderLayout());
            JLabel gaucheAff = new JLabel(), droiteAff = new JLabel(), hautAff = new JLabel(), basAff = new JLabel();
            hautAff = new JLabel(String.valueOf("       "+tuilePiochee.tabH[0])+"   "+String.valueOf(tuilePiochee.tabH[1])+"  "+String.valueOf(tuilePiochee.tabH[2])+"       ");
            gaucheAff = new JLabel("<html>"+String.valueOf(tuilePiochee.tabG[0])+"<br>"+String.valueOf(tuilePiochee.tabG[1])+"<br>"+String.valueOf(tuilePiochee.tabG[2])+"</html>");
            droiteAff = new JLabel("<html>"+String.valueOf(tuilePiochee.tabD[0])+"<br>"+String.valueOf(tuilePiochee.tabD[1])+"<br>"+String.valueOf(tuilePiochee.tabD[2])+"</html>");
            basAff = new JLabel(String.valueOf("       "+tuilePiochee.tabB[0])+"   "+String.valueOf(tuilePiochee.tabB[1])+"  "+String.valueOf(tuilePiochee.tabB[2])+"       ");
            j.add(hautAff,BorderLayout.NORTH);
            hautAff.setHorizontalAlignment(JLabel.CENTER);
            j.add(droiteAff,BorderLayout.EAST);
            j.add(gaucheAff,BorderLayout.WEST);
            j.add(basAff,BorderLayout.SOUTH);
            basAff.setHorizontalAlignment(JLabel.CENTER);
            j.setBackground(Color.WHITE);
            j.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            return j;
        }else if(tuilePiochee instanceof TuileCarcassonne){
            TuileCarcassonne test = (TuileCarcassonne) tuilePiochee;
            j = new JPanel(new BorderLayout());
            ImageIcon image = new ImageIcon(test.image);
            if(b){
                AffineTransform transformer = new AffineTransform();
    		    transformer.rotate(Math.PI/2, image.getIconWidth()/2, image.getIconHeight()/2);
                AffineTransformOp op = new AffineTransformOp(transformer, AffineTransformOp.TYPE_BILINEAR);
                test.image = op.filter(test.image, null);
                image = new ImageIcon(test.image);
                b = false;
            }
            j.add(new JLabel(image), BorderLayout.CENTER);
        }
        return j;
    }
    public void miseAJour(){
        panel2.removeAll();
        for (int i=0;i<model.joueurs.length;i++){
            JLabel joueur = new JLabel(model.joueurs[i].getNom()+" : "+model.joueurs[i].getPoints());
            panel2.add(joueur);
        }
        panel7.removeAll();
        panel7.add(new JLabel(model.joueurCourant.getNom()+", à ton tour de jouer !", SwingConstants.CENTER), BorderLayout.CENTER);
        panel2.revalidate();
        panel2.repaint();
        panel7.revalidate();
        panel7.repaint();
    }
    public void finJeu(){
        int max = 0;
        int[] points = new int[model.joueurs.length];
        for(int i=0;i<model.joueurs.length;i++){
            points[i]=model.joueurs[i].getPoints();
        }
        for(int i=0;i<points.length;i++){
            if(points[i]>max){
                max=points[i];
            }
        }
        for(int i=0;i<points.length;i++){
            if(points[i]==max){
                JOptionPane.showMessageDialog(null, "Le "+model.joueurs[i].getNom()+" a gagné avec "+max+" points !");
            }
        }
        System.exit(0);
    }
    public void botJouer(){
        if(model.sac.isEmpty()){
            finJeu();
        }
        if(model.joueurCourant.getNom().contains("Bot")){
            tuilePiochee = model.sac.removeLast();
            boolean bool = false;
            String nom = model.joueurCourant.getNom();
            int x = 1000;
            for(int a=0;a<x;a++){
                int z = new Random().nextInt(1,11);
                int y = new Random().nextInt(1,11);
                int t = new Random().nextInt(1,6);
                for(int o=1;o<t;o++){
                    tuilePiochee.tourner();
                    if(tuilePiochee instanceof TuileCarcassonne){
                        b = true;
                        TuileCarcassonne test = (TuileCarcassonne) tuilePiochee;
                        ImageIcon image = new ImageIcon(test.image);
                        if(b){
                            AffineTransform transformer = new AffineTransform();
                            transformer.rotate(Math.PI/2, test.image.getWidth() / 2, test.image.getHeight() / 2);
                            AffineTransformOp op = new AffineTransformOp(transformer, AffineTransformOp.TYPE_BILINEAR);
                            test.image = op.filter(test.image, null);
                            image = new ImageIcon(test.image);
                            b = false;
                        }
                    }
                }
                if(model.plateau[z][y].tuile != null){
                    continue;
                }else{
                    model.plateau[z][y].tuile = tuilePiochee;
                    if(controler.valider(z,y)){
                        model.plateau[z][y].addTuile(tuilePiochee);
                        JOptionPane.showMessageDialog(null, nom+" a placé sa tuile.");
                        a=x;
                        bool = true;
                    }else{
                        model.plateau[z][y].tuile = null;
                    }
                }
            }
            if(!bool){
                JOptionPane.showMessageDialog(null, nom+" n'a pas réussi à placer sa tuile.");
                model.nextJoueur();
            }
        }
    }
}
