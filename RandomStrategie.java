package model;

import java.util.ArrayList;
import java.util.List;

public class RandomStrategie implements Strategie {
    private boolean[][] dejaTirees;

    public RandomStrategie(int taille) {
        dejaTirees = new boolean[taille][taille];
    }

    /**
     * Permet à la strategie random de choisir une position pour tirer.
     * @return La position choisie.
    */  
    @Override
    public Position choisirPosition(Mer mer) {
        List<Position> positionsPossibles = new ArrayList<>();
        for (int i = 0; i < mer.getTaille(); i++) {
            for (int j = 0; j < mer.getTaille(); j++) {
                if (!dejaTirees[i][j]) {
                    positionsPossibles.add(new Position(i, j));
                }
            }
        }
        if (!positionsPossibles.isEmpty()) {
            Position p = positionsPossibles.get((int) (Math.random() * positionsPossibles.size()));
            dejaTirees[p.getLigne()][p.getColonne()] = true;
            return p;
        }
        return null;
    }

    /**
     * La stratégie aléatoire ne met pas à jour son état en fonction du résultat du tir.
    */
    @Override
    public void mettreAJourStrategie(Tir tir) {
        // La stratégie aléatoire n'a pas besoin de réagir aux tirs
    }
}