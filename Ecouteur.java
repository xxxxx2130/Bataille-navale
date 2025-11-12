package view;

import model.*;

/**
 * Définit l'interface pour les objets qui écoutent les changements dans un modèle.
 * Les écouteurs sont notifiés lorsque le modèle est mis à jour.
 */
public interface Ecouteur {
    /**
     * Notifie l'écouteur que le modèle a été mis à jour.
     * @param mer Le modèle mis à jour.
     */
    public void modeleMisAJour(Mer mer);
}