package model;

import java.util.*;

/**
* Gère le déroulement du jeu, l'initialisation, les tours des joueurs et la vérification de la fin du jeu.
*/
public class Game extends AbstractModelEcoutable {
    private AbstractPlayer joueurHumain;
    private AbstractPlayer joueurOrdinateur;
    private AbstractPlayer joueurCourant;
    private Scanner scanner = new Scanner(System.in);

    /**
     * Un tour de jeu, en gérant les tirs des joueurs et en vérifiant la fin du jeu.
     * @param pos La position du tir du joueur humain à l'interface graphique
     */
    public void jouer(Position pos) {
        if (estTermine())
            return;

        if (joueurCourant == joueurHumain) {
            try {
                // Tour du joueur humain
                Tir tir = joueurOrdinateur.getMer().tirer(pos);
                joueurHumain.recevoirResultatTir(tir);
                fireChange();

                if (joueurOrdinateur.getMer().estTermine()) {
                    return;
                }

                // Passage au tour de l'ordinateur
                joueurCourant = joueurOrdinateur;
                fireChange();
                // L'ordinateur joue après un court délai
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        Position posOrdinateur = joueurOrdinateur.choisirPosition();
                        Tir tirOrdinateur = joueurHumain.getMer().tirer(posOrdinateur);
                        joueurOrdinateur.recevoirResultatTir(tirOrdinateur);

                        if (!joueurHumain.getMer().estTermine()) {
                            joueurCourant = joueurHumain;
                        }

                        fireChange();
                    }
                };

                timer.schedule(task, 700);

            } catch (IllegalStateException ex) {

                fireChange();
            }
        }
    }

    public void initialiserJeu(String nom, int taille , int choix) {
     try {
            System.out.println("Début de l'initialisation du jeu...");

            initialiserJoueurs(nom, taille,choix);
            System.out.println("Joueurs initialisés.");

            placerNaviresAutomatiquement();
            System.out.println("Navires placés.");

            System.out.println("Initialisation du jeu terminée.");
            fireChange();
           
        } catch (Exception e) {
            System.err.println("Erreur lors de l'initialisation du jeu : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public AbstractPlayer getJoueurCourant() {
        return joueurCourant;
    }

    public AbstractPlayer getJoueurHumain() {
        return joueurHumain;
    }

    public AbstractPlayer getJoueurOrdinateur() {
        return joueurOrdinateur;
    }

    /**
     * Place les navires des joueurs automatiquement sur leurs grilles respectives.
    */
    private void placerNaviresAutomatiquement() {
        // Liste de navires non ordonnée
        int[] taillesNavires = {3, 2, 5, 4, 4}; 

        // Trier les tailles des navires dans l'ordre décroissant
        Arrays.sort(taillesNavires);
        for (int i = 0; i < taillesNavires.length / 2; i++) {
            int tmp = taillesNavires[i];
            taillesNavires[i] = taillesNavires[taillesNavires.length - 1 - i];
            taillesNavires[taillesNavires.length - 1 - i] = tmp;
        }

        // Placer les navires dans l'ordre trié
        for (int taille : taillesNavires) {
            placerNavire(joueurOrdinateur.getMer(), taille);
            placerNavire(joueurHumain.getMer(), taille);
        }
    }

    /**
     * Initialise les joueurs en demandant leur nom et en choisissant la stratégie de l'ordinateur.
    */
    private void initialiserJoueurs(String nomJoueur ,int tailleMer, int choixStrategie ){
        Strategie strategie;

        if (choixStrategie == 0) {
            strategie = new IAStrategie(tailleMer);
        } else {
            strategie = new RandomStrategie(tailleMer);
        }

        this.joueurOrdinateur = new Ordinateur("Ordinateur", tailleMer, strategie);
        this.joueurHumain = new Humain(nomJoueur, tailleMer);
        this.joueurCourant = joueurHumain;
    }

    /**
     * Place un navire sur la grille de la mer.
     *
     * @param mer    La mer où placer le navire.
     * @param taille La taille du navire à placer.
    */
    private void placerNavire(Mer mer, int taille) {
        Navire navire = new Navire(taille);
        boolean place = false;
        int tentatives = 0;
        while (!place && tentatives < 100) {
            int ligne = (int) (Math.random() * mer.getTaille());
            int colonne = (int) (Math.random() * mer.getTaille());
            boolean horizontal = Math.random() < 0.5;
            place = mer.placerNavire(navire, new Position(ligne, colonne), horizontal);
            tentatives++;
        }
        if (!place) {
            System.err.println("Erreur : Impossible de placer un navire de taille " + taille + ".");
        }
    }

    /**
     * Vérifie si le jeu est terminé.
     * @return true si le jeu est terminé, false sinon.
     */
    public boolean estTermine() {
        return joueurHumain.getMer().estTermine() || joueurOrdinateur.getMer().estTermine();
    }


    public void jouer() {
        System.out.println("Début de la partie...");

        while (!joueurHumain.getMer().estTermine() && !joueurOrdinateur.getMer().estTermine()) {
            
                 boolean isTirValide = false;
                  System.out.println("\n--- Tour du " + joueurCourant.getNom() + " ---");
                  afficherGrilles(joueurHumain.getMer(), joueurOrdinateur.getMer());
                  while (!isTirValide) {
                      Position position = joueurCourant.choisirPosition();
                      System.out.println("Position choisie par  " + joueurCourant.getNom() + " : " + position);
     
                      try {
                        AbstractPlayer opponent = (joueurCourant==joueurOrdinateur)?joueurHumain:joueurOrdinateur;
                         Tir tir = opponent.getMer().tirer(position);
                         if (tir != null) {
                         joueurCourant.recevoirResultatTir(tir);
                         isTirValide = true; // tir réussi, on passe au tour suivant
                         joueurCourant = (joueurCourant==joueurHumain)?joueurOrdinateur:joueurHumain;
                         }
                        }  catch (IllegalStateException e) {
                         System.out.println(e.getMessage()); //  si la case a déjà été tirée
                         System.out.println("Réessayez."); // rejouer
                        }
                  }
        } 
        System.out.println(joueurCourant.getNom()+"à ganger");
        afficherGrilles(joueurHumain.getMer(), joueurOrdinateur.getMer());
        
    }
    
    private void afficherGrilles(Mer merHumain, Mer merOrdinateur) {
        System.out.println("Grille du Joueur Humain\t\t\tGrille de l'Ordinateur");
        afficherEnTetesGrilles(merHumain.getTaille());

        for (int i = 0; i < merHumain.getTaille(); i++) {
            afficherLigneGrille(merHumain, i, (i + 1) + " ",true);
            System.out.print("\t\t");
            afficherLigneGrille(merOrdinateur, i, (i + 1) + " ",false);
            System.out.println();
        }
    }

    private void afficherEnTetesGrilles(int taille) {
        System.out.print("  ");
        for (int i = 0; i < taille; i++) {
            System.out.print((char) ('A' + i) + " ");
        }
        System.out.print("\t\t  ");
        for (int i = 0; i < taille; i++) {
            System.out.print((char) ('A' + i) + " ");
        }
        System.out.println();
    }

    private void afficherLigneGrille(Mer mer, int ligne, String prefixe , Boolean afficher) {
        System.out.print(prefixe);
        for (int j = 0; j < mer.getTaille(); j++) {
            Position position = new Position(ligne, j);
            Tir tir = mer.getGrilleTirs()[ligne][j];
            if (tir != null) {
                System.out.print(tir.estTouche() ? "X " : "! ");
            } else if (mer.estOccupee(position) && afficher) {
                System.out.print("N "); // Navire non touché
            } else {
                System.out.print(". ");
            }
        }
    }


}