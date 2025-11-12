package model;

import java.util.*;
import view.*;

/**
 * Classe abstraite implémentant l'interface Ecoutable.
 * Fournit une base pour les modèles qui peuvent être écoutés par des écouteurs.
 * Gère aussi l'ajout, la suppression et la notification des écouteurs.
 */
public abstract class AbstractModelEcoutable implements Ecoutable {
    private List<Ecouteur> ecouteurs; 

    /**
     * Crée une nouvelle instance et initialise la liste des écouteurs.
    */
    public AbstractModelEcoutable() {
        this.ecouteurs = new ArrayList<>();
    }

    /**
     * Ajoute un écouteur à la liste des écouteurs.
     * @param e L'écouteur à ajouter.
    */
    @Override
    public void ajouterEcouteur(Ecouteur e){
        ecouteurs.add(e);

    }
    
    /**
     * Retire un écouteur de la liste des écouteurs.
     * @param e L'écouteur à retirer.
    */
    @Override
    public void retirerEcouteur(Ecouteur e) {
        ecouteurs.remove(e);
    }
    
     /**
     * Envoi des notifications à tous les écouteurs enregistrés que le modèle a été mis à jour.
     * @note en supposant que 'this' est une instance de Mer.
     */
    protected void fireChange() {
        for (Ecouteur e : ecouteurs) {
            e.modeleMisAJour((Mer) this);
        }
    }
}