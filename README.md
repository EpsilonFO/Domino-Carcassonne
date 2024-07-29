
# Jeu de Dominos et Carcassonne

Développés par Asso ALI MULLUD et Félix OLLIVIER en vue du projet de Programmation Orientée Objet.

## Lancer le jeu

Pour lancer le jeu, exécutez dans le terminal la commande suivante :
```bash
javac Menu.java && java Menu
```

## Instructions de jeu

1. Tout d'abord, choisissez le nombre de joueurs ainsi que le nombre d'IA parmi les joueurs.
2. Cliquez sur le jeu souhaité afin de lancer une partie.

### Déroulement de la partie

- Le premier joueur est toujours Joueur 1, mais après lui, l'ordre est aléatoire.
- Le premier joueur doit commencer par piocher une tuile (ou abandonner), puis entrer dans le champ de texte le numéro de la case que l'on souhaite remplir.
- En cas d'erreur, une fenêtre s'affiche pour préciser l'erreur.
- Le jeu passe automatiquement au joueur suivant si le premier joueur a posé sa tuile.
- Si la partie comprend des IA, elles jouent automatiquement dès que c'est leur tour, et un message indique si l'IA a réussi à poser sa tuile ou non. La façon de procéder pour l'IA est la suivante :
  - L'IA possède de base 50 chances (modifiable à souhait) de poser sa tuile. Elle essaye de la poser sur une case aléatoire, tournée dans une position aléatoire.
  - Si au bout de ces 50 essais elle n'a toujours pas trouvé de position adéquate, elle défausse sa tuile et passe au joueur suivant.
- Une fois que le sac ne possède plus de tuile, ou qu'il ne reste qu'un seul joueur dans la partie (le ou les autres ayant abandonné), la partie se termine en affichant le vainqueur ainsi que son nombre de points.
