package model;

public abstract class AbstractPlayer implements Player {
    protected String nom;
    protected Mer mer;

    public AbstractPlayer(String nom, int tailleMer) {
        this.nom = nom;
        this.mer = new Mer(tailleMer); 
    }

    public Mer getMer() {
        return mer;
    }
    public String getNom() {
        return nom;
    }
    
    public void afficherResultatTir(Tir tir) {
        System.out.println(nom + " a reçu le résultat du tir : " + tir);
    }

}