package view;

import java.awt.*;
import javax.swing.*;
import model.AbstractPlayer;

public class StatusPanel extends JPanel {
    private JLabel stateLabel;
    private JLabel advairsaireLabel;
    private JLabel playerLabel;
    
    private String state = " Persone n'a gagné pour l'instant";
    private AbstractPlayer player;
    private AbstractPlayer adversaire;

    /**
     * Cette fonction nous montre l'etat du panel
     * @param adversaire
     * @param player
     */
    public StatusPanel(AbstractPlayer adversaire, AbstractPlayer player) {
        this.player = player ;
        this.adversaire = adversaire ;

        // Définition d'une police plus grande
        Font labelFont = new Font("Arial", Font.BOLD, 20); 

        playerLabel = new JLabel("Mer de l'" + this.player.getNom(), SwingConstants.CENTER);
        advairsaireLabel = new JLabel("Mer de " + this.adversaire.getNom(), SwingConstants.CENTER);
        stateLabel = new JLabel("La gagant est : " + state, SwingConstants.CENTER);

        // Appliquer la police aux labels
        playerLabel.setFont(labelFont);
        stateLabel.setFont(labelFont);
        advairsaireLabel.setFont(labelFont);

        setLayout(new GridLayout(1, 3));

        add(playerLabel);
        add(stateLabel);
        add(advairsaireLabel);

        setBackground(Color.WHITE);
        setVisible(true);
    }

    public void setCurrentState(String newstate) {
        this.state = newstate;
        updateStateLabel();
    }

    private void updateStateLabel() {
        stateLabel.setText("La gagant est : " + state);
        repaint();
    }
}