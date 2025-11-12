package model;

/**
 * Représente une position dans une grille, avec une ligne et une colonne.
 * Utilisé pour localiser des éléments dans le jeu de la bataille navale.
 */
public class Position {
    private int ligne;
    private int colonne;

    /**
     * Crée une nouvelle position avec les coordonnées de ligne et de colonne spécifiées.
     *
     * @param ligne  la ligne.
     * @param colonne colonne.
     */
    public Position(int ligne, int colonne) {
        this.ligne = ligne;
        this.colonne = colonne;
    }

    /**
     * Retourne la ligne
     * @return la ligne.
     */
    public int getLigne() {
        return ligne;
    }

    /**
     * Retourne la colonne
     * @return la colonne.
     */
    public int getColonne() {
        return colonne;
    }

    /**
     * Compare cette position avec un autre objet pour l'égalité.
     * 
     * @param obj L'objet à comparer avec cette position.
     * @return true si les objets sont égaux, false sinon.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Position position = (Position) obj;
        return ligne == position.ligne && colonne == position.colonne;
    }

    /**
     * @return Une chaîne représentant cette position.
     */
    @Override
    public String toString() {
        return "(" + (ligne + 1) + ", " + (colonne + 1) + ")";
    }
}