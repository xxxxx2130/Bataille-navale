package view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import model.Game;
import model.Position;

public class GameView extends JFrame {

 
    private MerView boardplayer2;
    private MerView boardplayer1;
    private JPanel mainboard;
    private StatusPanel statusPanel;
    private Game game;

    /**
     * Crée une nouvelle vue du jeu avec le modèle Game.
     *
     * @param game Le modèle du jeu à utiliser.
    */
    public GameView(Game game) {
        this.game = game;
        boardplayer1 = new MerView(game.getJoueurOrdinateur().getMer(), true);
        boardplayer2 = new MerView(game.getJoueurHumain().getMer(), true);
        statusPanel = new StatusPanel(game.getJoueurHumain(), game.getJoueurOrdinateur());
        mainboard = new JPanel();

        mainboard.setLayout(new GridLayout(1, 2));
        mainboard.add(boardplayer1);
        mainboard.add(boardplayer2);
        add(mainboard, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.NORTH);
        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Ajout d'un seul MouseListener
        MouseAdapter sharedMouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MerView clickedView = (MerView) e.getSource(); // Obtenir la MerView cliquée
                Position pos = clickedView.getCellCoordinates(e);
                if (pos != null && game.getJoueurCourant() == game.getJoueurHumain() && clickedView == boardplayer1) {
                    game.jouer(pos);
                }
                
                if (game.estTermine()) {
                    String gagnant = game.getJoueurCourant().getNom();
                    statusPanel.setCurrentState(gagnant);
        
                    // Afficher une boîte de dialogue avec le gagnant
                    JOptionPane.showMessageDialog(null, "\n \n Félicitation " + gagnant +" vous avez coulé tous les navires de l'adversaire \n \n", 
                                                  "Fin du jeu Bataille Navale", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        };

        boardplayer1.addMouseListenerToPanel(sharedMouseListener);
        boardplayer2.addMouseListenerToPanel(sharedMouseListener);
    }

    public MerView getBoardplayer1() {
        return boardplayer1;
    }

    public MerView getBoardplayer2() {
        return boardplayer2;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}