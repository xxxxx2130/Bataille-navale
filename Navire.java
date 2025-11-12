package model;

import java.util.*;

/**
 * Représente un navire dans le jeu de bataille navale.
*/
public class Navire {
    private List<Position> positions;
    private List<Position> positionsTouchees;
    private int taille;

    public Navire(int taille) {
        this.taille = taille;
        this.positions = new ArrayList<>();
        this.positionsTouchees = new ArrayList<>();
    }

    public int getTaille() {
        return taille;
    }
    
    public void setTaille(int taille) {
        this.taille = taille;
    }
    
    /**
    * @return La liste des positions du navire.
    */
    public List<Position> getPositions() {
        return positions;
    }

    /**
     * @return La liste des positions touchées du navire.
     */
    public List<Position> getPositionsTouchees() {
        return positionsTouchees;
    }

    /**
     * Ajoute une position dans la liste
     * @param position  La position à ajouter.
    */
    public void ajouterPosition(Position position) {
        if (positions.size() < taille) {
            positions.add(position);
        }
    }

    /**
     * Vérifie si le navire contient la position.
     *
     * @param position La position à vérifier.
     * @return true si le navire contient la position, false sinon.
    */
    public boolean contientPosition(Position position) {
        return positions.contains(position);
    }

    /**
     * 
     * @param position la position touchée
    */
    public void toucher(Position position) {
        if (contientPosition(position) && !positionsTouchees.contains(position)) {
            positionsTouchees.add(position);
        }
    }

    /**
     * Verifie si un navire est coulé ou non
     * @return true, si le navire est coulé, false sinon
     */
    public boolean estCoule() {
        return positionsTouchees.size() == taille;
    }

    /**
     * Vérifie si le navire est horizontal
     * @return true si le navire est horizontal, false sinon
    */
    public boolean estHorizontal() {
        if (positions.size() < 2) return true; // Si le navire a une seule case, on considère horizontal
        return positions.get(0).getLigne() == positions.get(1).getLigne();
    }
    
}


