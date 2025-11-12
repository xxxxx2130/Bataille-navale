package main;

import javax.swing.*;
import java.awt.*;
import model.*;
import view.*;

public class Main {
    public static void main(String[] args) {
        // Mode console
        System.out.println("======= Démarrage du jeu en mode console ======");
        Game game = new Game();
        Object[] options = {"Mode Graphique ", "Mode Console "};
        int choix  = showCustomOptionDialog("Choisissez ici le mode du jeu", "Choix du mode du jeu", options,500, 150);

        if (choix == 1){  
           game.initialiserJeu(demanderNomJoueur(),demanderEntier("Entrez la taille de la mer entre 5 et 26 ", 5, 26),demanderChoix());
           game.jouer();
           System.out.println("======= Fin du jeu en mode console. ======");
        }else{
            game.initialiserJeu(demanderNomJoueur(),demanderEntier("Entrez la taille de la mer entre 5 et 26 ", 5, 26),demanderChoix());
            SwingUtilities.invokeLater(() -> {
                GameView mainWindow = new GameView(game);
                mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // à ajouter
                System.out.println("Avant affichage graphique");
                mainWindow.setVisible(true);
            });
            
        }
        
    }

    private static String demanderNomJoueur() {
        String nomJoueur = showCustomSizedInputDialog("Entrez votre nom ici :", 500, 150);
        while (nomJoueur == null || nomJoueur.trim().isEmpty()) {
            nomJoueur = showCustomSizedInputDialog("Le nom ne peut être vide. Veillez réesseyer :", 500, 150);
        }
        return nomJoueur;
    }

    private static int demanderEntier(String message, int min, int max) {
        Integer valeur = null;
        while (valeur == null || valeur < min || valeur > max) {
            try {
                String input = showCustomSizedInputDialog(message, 500, 150);
                if (input == null || input.trim().isEmpty()) {
                    showCustomMessageDialog("Aucune valeur saisie. Fin du programme.",
                                            "Entrée vide", 500, 150, JOptionPane.WARNING_MESSAGE);
                    System.exit(0);
                }
    
                valeur = Integer.parseInt(input.trim());
                if (valeur < min || valeur > max) {
                    showCustomMessageDialog("Veuillez entrer un nombre entre " + min + " et " + max + ".",
                                            "Valeur hors limites", 500, 150, JOptionPane.WARNING_MESSAGE);
                    valeur = null;
                }
            } catch (NumberFormatException e) {
                showCustomMessageDialog("Veuillez entrer un nombre valide.",
                                        "Erreur de saisie", 500, 150, JOptionPane.ERROR_MESSAGE);
            }
        }
        return valeur;
    }
    

    private static int demanderChoix(){
        Object[] options = { "IA intelligente", "Stratégie aléatoire" };
        int choixStrategie = showCustomOptionDialog("Choisissez la stratégie de l'ordinateur","Choix de stratégie", options,500,150);
        return choixStrategie;
    }

    private static void showCustomMessageDialog(String message, String title, int width, int height, int messageType) {
        JLabel label = new JLabel(message, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 17));
    
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.CENTER);
    
        JOptionPane optionPane = new JOptionPane(panel, messageType, JOptionPane.DEFAULT_OPTION);
        JDialog dialog = optionPane.createDialog(null, title);
        dialog.setSize(width, height);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    
    private static String showCustomSizedInputDialog(String message, int width, int height) {
        JPanel panel = new JPanel(new BorderLayout());
    
        JLabel label = new JLabel(message);
        label.setFont(new Font("Arial", Font.BOLD, 17)); // Taille du texte du message
        panel.add(label, BorderLayout.NORTH);
    
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 17)); // Taille du texte du champ de saisie
        textField.setPreferredSize(new Dimension(width - 50, 40)); // Largeur personnalisée du champ
        panel.add(textField, BorderLayout.CENTER);
    
        JOptionPane optionPane = new JOptionPane(panel, JOptionPane.QUESTION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
        JDialog dialog = optionPane.createDialog(null, "Entrée requise");
        dialog.setSize(width, height);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    
        Object selectedValue = optionPane.getValue();
        if (selectedValue == null || !(selectedValue instanceof Integer) || (int) selectedValue != JOptionPane.OK_OPTION) {
            return null;
        }
    
        return textField.getText();
    }
    
    
    private static int showCustomOptionDialog(String message, String title, Object[] options, int width, int height) {
        // Panel principal
        JPanel panel = new JPanel(new BorderLayout(10, 10));
    
        // Message avec police personnalisée
        JLabel messageLabel = new JLabel(message, SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 20));  // ← Taille du message
        panel.add(messageLabel, BorderLayout.NORTH);
    
        // Panel pour les boutons
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        JButton[] buttons = new JButton[options.length];
        final int[] selectedIndex = {-1};
    
        for (int i = 0; i < options.length; i++) {
            final int index = i;
            buttons[i] = new JButton(options[i].toString());
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 18));
            buttons[i].setPreferredSize(new Dimension(200, 30));
            buttons[i].addActionListener(e -> {
                selectedIndex[0] = index;
                SwingUtilities.getWindowAncestor(buttons[index]).dispose();
            });
            buttonsPanel.add(Box.createHorizontalStrut(45));
            buttonsPanel.add(buttons[i]);
        }        
    
        panel.add(buttonsPanel, BorderLayout.CENTER);
    
        // Boîte de dialogue
        JDialog dialog = new JDialog((JFrame) null, title, true);
        dialog.setContentPane(panel);
        dialog.setSize(width, height);
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    
        return selectedIndex[0];
    }
    
    
}
