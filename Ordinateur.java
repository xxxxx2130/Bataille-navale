package model;

public class Ordinateur extends AbstractPlayer {
    private Strategie strategie; // la strategie utilisé parl'ordinateur.
    
    /**
     * Crée un nouvel ordinateur avec un nom, une taille de mer et une stratégie.
     *
     * @param nom       Le nom de l'ordinateur.
     * @param tailleMer La taille de la mer pour le jeu.
     * @param strategie La stratégie utilisée par l'ordinateur.
     */
    public Ordinateur(String nom, int tailleMer, Strategie strategie) {
        super(nom, tailleMer);
        this.strategie = strategie;
    }

     /**
     * Choisit une position en utilisant la stratégie.
     *
     * @return La position choisie par l'ordinateur.
     */
    @Override
    public Position choisirPosition() {
        return strategie.choisirPosition(mer);
    }

    /**
     * Reçoit le résultat d'un tir et met à jour la stratégie de l'ordinateur.
     *
     * @param tir Le résultat du tir à recevoir.
     */
    @Override
    public void recevoirResultatTir(Tir tir) {
        super.afficherResultatTir(tir); 
        strategie.mettreAJourStrategie(tir);
    }
}

