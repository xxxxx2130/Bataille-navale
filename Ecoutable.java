package view;

/**
 * Définit l'interface pour les objets qui peuvent être écoutés par des écouteurs.
 * Les classes qui implémentent cette interface peuvent ajouter et retirer des écouteurs.
 */
public interface Ecoutable {
    /**
     * Ajoute un écouteur à l'objet.
     * @param e L'écouteur à ajouter.
     */
    void ajouterEcouteur(Ecouteur e);
     /**
     * Retire un écouteur de l'objet.
     * @param e L'écouteur à retirer.
     */
    void retirerEcouteur(Ecouteur e);
}
