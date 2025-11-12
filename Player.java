package model;

/**
 * Représente un joueur qui est implementé par le la classe AbstractPlayer
 * Et implement egalement les methodes qui seront definies après.
 */
public interface Player {
    public Position choisirPosition();
    public void recevoirResultatTir(Tir tir);
}