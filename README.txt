Jeu de Dominos et Carcassonne développés par Asso ALI MULLUD et Félix OLLIVIER en vue du projet de Programmation Orientée Objet.

Pour lancer le jeu, lancez dans le terminal la commande 'javac Menu.java && java Menu'

Tout d'abord, il vous faudra choisir le nombre de joueurs ainsi que le nombre d'IA PARMI les joueurs.
Puis il suffit de cliquer sur le jeu souhaité afin de lancer une partie.

Le premier joueur est toujours Joueur 1, mais après lui l'ordre est aléatoire.
Le premier joueur doit toujours commencer par piocher une tuile (ou abandonner), puis il faut rentrer dans le champ de texte le numéro
de la case que l'on souhaite remplir. S'il y a une erreur, le jeu nous le fera savoir en nous affichant une fenêtre nous précisant l'erreur.
Le jeu passe automatiquement au joueur prochain si le premier joueur a posé sa tuile.
Si la partie comprend des IA, ils jouent automatiquement dès que c'est leur tour, et un message nous indique si l'IA a réussi a poser sa tuile
ou non. La façon de procéder pour l'IA est la suivante :
	L'IA possède de base 50 chances (modifiable à souhait) de poser sa tuile. Elle essaye de la poser sur une case aléatoire, tournée dans
	une position aléatoire.	Si au bout de ces 50 essais elle n'a toujours pas trouvé de position adéquate, elle défausse sa tuile et passe
	au joueur suivant.
Une fois que le sac ne possède plus de tuile, ou qu'il ne reste qu'un seul joueur dans la partie (le ou les autres ayant abandonné), la partie
se termine en affichant le vainqueur ainsi que son nombre de points.