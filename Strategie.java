package model;

/**
 * Définit l'interface pour les stratégies utilisées par l'ordinateur pour choisir 
 * les positions de tir. Les stratégies doivent implémenter ces méthodes pour 
 * définir comment l'ordinateur choisit ses tirs.
*/
public interface Strategie {
    /**
     * @param mer L'état actuel de la mer.
     * @return La position choisie pour le tir.
    */
    public Position choisirPosition(Mer mer);
    /**
     * Met à jour la stratégie en fonction du résultat d'un tir.
     * @param tir Le résultat du tir 
     */
    public void mettreAJourStrategie(Tir tir);
}

