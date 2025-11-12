package model;

public class Tir {
    private Position position;
    private boolean touche;

    public Tir(Position position, boolean touche) {
        this.position = position;
        this.touche = touche;
    }

    public Position getPosition() {
        return position;
    }

    public boolean estTouche() {
        return touche;
    }

    @Override
    public String toString() {
        return "à la position " + position + " : " + (touche ? "Touché" : "Raté");
    }
}