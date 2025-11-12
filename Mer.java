package model;

import java.util.*;

/**
 * Représente la mer, contenant les navires et les tirs.
 * Gère le placement des navires, les tirs et vérifie si le jeu est terminé.
*/
public class Mer extends AbstractModelEcoutable {
    private int taille;
    private Navire[][] grilleNavires; 
    private Tir[][] grilleTirs;      

     /**
     * Crée une nouvelle mer avec la taille.
     * @param taille La taille de la mer une qui est grille carrée.
     */
    public Mer(int taille)  {
        super();
        this.taille = taille;
        this.grilleNavires = new Navire[taille][taille];
        this.grilleTirs = new Tir[taille][taille];
    }


    public int getTaille() {
        return taille;
    }

    public Tir[][] getGrilleTirs() {
        return grilleTirs;
    }

    public void setGrilleTirs(Tir[][] grilleTirs) {
        this.grilleTirs = grilleTirs;
    }
    
    public Navire[][] getGrilleNavires() {
        return grilleNavires;
    }

    public void setGrilleNavires(Navire[][] grilleNavires) {
        this.grilleNavires = grilleNavires;
    }

    /**
     * Placer un navire sur la grille à une position donnée, horizontalement ou verticalement.
     *
     * @param navire     Le navire à placer.
     * @param position   La position du navire.
     * @param horizontal verifie si le navire doit être placé horizontalement ou verticalement.
     * @return true si le navire est placé avec succès, false sinon.
    */
    public boolean placerNavire(Navire navire, Position position, boolean horizontal) {
        int ligne = position.getLigne();
        int colonne = position.getColonne();

        // Vérifie si le navire peut être placé
        for (int i = 0; i < navire.getTaille(); i++) {
            Position nouvellePosition;
            if (horizontal) {
                nouvellePosition = new Position(ligne, colonne + i);
            } else {
                nouvellePosition = new Position(ligne + i, colonne);
            }
            if (nouvellePosition.getLigne() >= taille || nouvellePosition.getColonne() >= taille ||
                grilleNavires[nouvellePosition.getLigne()][nouvellePosition.getColonne()] != null) {
                return false; // Position invalide ou  position non vide
            }
        }

        // Place le navire sur la grille
        for (int i = 0; i < navire.getTaille(); i++) {
            Position nouvellePosition;
            if (horizontal) {
                nouvellePosition = new Position(ligne, colonne + i);
            } else {
                nouvellePosition = new Position(ligne + i, colonne);
            }
            grilleNavires[nouvellePosition.getLigne()][nouvellePosition.getColonne()] = navire;
            navire.ajouterPosition(nouvellePosition);
        }
        return true;
    }

    /**
     * Vérifie si une position est occupée par un navire.
     * @param position position à vérifier.
     * @return true si la position est occupée, false sinon.
     */
    public boolean estOccupee(Position position) {
        return grilleNavires[position.getLigne()][position.getColonne()] != null;
    }
    
    /**
     * Effectue un tir à la position donnée et retourne le résultat du tir.
     *
     * @param position La position du tir.
     * @return Le résultat du tir.
     * @throws IllegalArgumentException Si la position est hors limites.
     * @throws IllegalStateException    Si la case a déjà été tirée.
    */
    public Tir tirer(Position position) {
  
        if (position.getLigne() < 0 || position.getLigne() >= grilleNavires.length ||
            position.getColonne() < 0 || position.getColonne() >= grilleNavires.length) {
            throw new IllegalArgumentException("Position hors limites.");
        }
    
    
        if (grilleTirs[position.getLigne()][position.getColonne()] != null) {
            throw new IllegalStateException("Cette case a déjà été tirée.");
        }
    
        // Vérifier si un navire est présent à la position
        Navire navire = grilleNavires[position.getLigne()][position.getColonne()];
        boolean touche = (navire != null);
        
        if (touche && navire != null) {
            navire.toucher(position); 
        }
    
        Tir tir = new Tir(position, touche);
        grilleTirs[position.getLigne()][position.getColonne()] = tir;
    
        fireChange();
        return tir;
    }

    /**
     * Vérifie si tous les navires sont coulés donc le jeu est terminé
     *
     * @return true si tous les navires sont coulés, false sinon.
     */
    public boolean estTermine() {
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                Navire navire = grilleNavires[i][j];
                if (navire != null && !navire.estCoule()) {
                    return false; // Il reste au moins un navire non coulé
                }
            }
        }
        return true;
    }   

    /**
     * Contient la liste de tous les navires présents sur la mer.
     * @return La liste des navires.
     */
    public List<Navire> getNavires() {
        List<Navire> navires = new ArrayList<>();
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                Navire navire = grilleNavires[i][j];
                if (navire != null && !navires.contains(navire)) {
                    navires.add(navire);  // Ajouter le navire s'il n'est pas déjà dans la liste
                }
            }
        }
        return navires;
    }
    


}