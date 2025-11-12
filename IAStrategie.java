package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette fait l'implementation de la stratégie de l'IA pour le jeu
 * Utilise une stratégie basée sur les tirs réussis précédents pour choisir les positions de tir.
 */
public class IAStrategie implements Strategie {
    private Position dernierTirReussi = null;
    private boolean[][] positionsNonTirees; 

    /**
     * Crée une nouvelle stratégie d'IA
     * @param tailleMer La taille de la mer.
    */
    public IAStrategie(int tailleMer) {
        this.positionsNonTirees = new boolean[tailleMer][tailleMer];
        // Initialiser toutes les positions comme non tirées
             for (int i = 0; i < tailleMer; i++) {
            for (int j = 0; j < tailleMer; j++) {
                positionsNonTirees[i][j] = true;
            }
        }
    }
 
    /**
     * Choisit une position pour tirer en fonction de la stratégie de l'IA de façon intelligente
     *
     * @param mer la mer.
     * @return La position choisie pour le tir.
     */
    @Override
    public Position choisirPosition(Mer mer) {
        if (dernierTirReussi != null) {
            //position adjacente
            int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            for (int[] dir : directions) {
                int ligne = dernierTirReussi.getLigne() + dir[0];
                int colonne = dernierTirReussi.getColonne() + dir[1];
                if (ligne >= 0 && ligne < mer.getTaille() && colonne >= 0 && colonne < mer.getTaille()) {
                    if (positionsNonTirees[ligne][colonne]) { // Vérifie si la case n'a pas deja eté tirée
                        return new Position(ligne, colonne);
                    }
                }
            }
        }

        //  aucun tir réussi ou aucune position adjacente disponible, choisir aléatoirement
        List<Position> positionsPossibles = new ArrayList<>();
        for (int i = 0; i < mer.getTaille(); i++) {
            for (int j = 0; j < mer.getTaille(); j++) {
                if (positionsNonTirees[i][j]) { //que les cases non tirées
                    positionsPossibles.add(new Position(i, j));
                }
            }
        }
        if (!positionsPossibles.isEmpty()) {
            return positionsPossibles.get((int) (Math.random() * positionsPossibles.size()));
        }
        return null; //aucune pos
    }


    /**
     * Mette à jour la stratégie de l'IA en fonction du résultat du tir.
     *
     * @param tir Le résultat du tir.
     */
    @Override
    public void mettreAJourStrategie(Tir tir) {
        int ligne = tir.getPosition().getLigne();
        int colonne = tir.getPosition().getColonne();
        positionsNonTirees[ligne][colonne] = false; // la case : tirée

        if (tir.estTouche()) {
            setDernierTirReussi(tir.getPosition()); // Enregistre le dernier tir 
        } else {
            reinitialiserRechercheAdjacente(); // Réinitialise la recherche adjacente si le tir est raté
        }
    }

    /**
     * Définit la position du dernier tir réussi.
     * @param position la position .
    */
    public void setDernierTirReussi(Position position) {
        this.dernierTirReussi = position;
    }

    public void reinitialiserRechercheAdjacente() {
        this.dernierTirReussi = null; // Réinitialise la recherche adjacente
    }
}