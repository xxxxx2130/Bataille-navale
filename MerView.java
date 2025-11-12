package view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import model.*;

public class MerView extends JPanel implements Ecouteur {

    private Mer mer;
    private int dim;
    private boolean estJoueurHumain;
    private MouseAdapter mouseListener;

     /**
     * Crée une nouvelle vue de la mer avec le modèle de mer et le type de joueur.
     *
     * @param mer             Le modèle de la mer.
     * @param estJoueurHumain Indique si cette vue est pour le joueur humain.
     */
    public MerView(Mer mer, boolean estJoueurHumain) {
        this.mer = mer;
        this.dim = mer.getTaille();
        this.estJoueurHumain = estJoueurHumain;
        setPreferredSize(new Dimension(340, 340));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        mer.ajouterEcouteur(this);
    }

    /**
     * Dessine la grille de la mer, les navires et les tirs.
     *
     * @param g L'objet pour le dessin.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int cellSize = Math.min(getWidth() / (dim + 1), getHeight() / (dim + 1));
        int offsetX = (getWidth() - dim * cellSize) / 2;
        int offsetY = (getHeight() - dim * cellSize) / 2;

        int ovalSize = (int) (cellSize * 0.4);
        int ovalOffset = (cellSize - ovalSize) / 2;
        g2d.setFont(new Font("Arial", Font.BOLD, 12));
        g2d.setColor(Color.BLACK);

        for (int col = 0; col < dim; col++) {
            char colLabel = (char) ('A' + col);
            int labelX = offsetX + col * cellSize + cellSize / 2;
            int labelY = offsetY - 20;
            g2d.drawString(String.valueOf(colLabel), labelX, labelY);
        }

        for (int row = 0; row < dim; row++) {
            int rowLabel = row + 1;
            int labelX = offsetX - 30;
            int labelY = offsetY + row * cellSize + cellSize / 2;
            g2d.drawString(String.valueOf(rowLabel), labelX, labelY);
        }

        for (int row = 0; row < dim; row++) {
            for (int col = 0; col < dim; col++) {
                Position pos = new Position(row, col);
                Tir tir = mer.getGrilleTirs()[row][col];
                if (tir != null) {
                    g2d.setColor(tir.estTouche() ? Color.RED : Color.GREEN);
                } else if (mer.estOccupee(pos) && estJoueurHumain) {
                    g2d.setColor(Color.GRAY);
                } else {
                    g2d.setColor(Color.WHITE);
                }
                g2d.fillOval(offsetX + col * cellSize + ovalOffset, offsetY + row * cellSize + ovalOffset, ovalSize, ovalSize);
            }
        }

        // Tracer le contour des navires coulés (adversaire uniquement)
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2)); // Ligne fine pour le contour
        List<Navire> naviresHumain = mer.getNavires();

        for (Navire navire : naviresHumain) { 
            if (estJoueurHumain || navire.estCoule()) {
                tracerContourNavire(g2d, navire, offsetX, offsetY, cellSize);
            }
        }     

        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(Color.BLACK);
        g2d.drawRect(offsetX - 1, offsetY - 1, dim * cellSize + 2, dim * cellSize + 2);
    }

    /**
     * Trace le contour d'un navire sur la grille.
     *
     * @param g2d       L'objet pour le dessin.
     * @param navire    Le navire à tracer.
     * @param offsetX   Le décalage en X pour le dessin.
     * @param offsetY   Le décalage en Y pour le dessin.
     * @param cellSize  La taille d'une cellule de la grille.
     */
    private void tracerContourNavire(Graphics2D g2d, Navire navire, int offsetX, int offsetY, int cellSize) {
        Position debut = navire.getPositions().get(0);
        int taille = navire.getPositions().size();

        int x = offsetX + debut.getColonne() * cellSize;
        int y = offsetY + debut.getLigne() * cellSize;

        if (navire.estHorizontal()) {
            int dY = y + (int) (cellSize * 0.2); 
            g2d.drawRoundRect(x, dY, taille * cellSize, (int) (cellSize * 0.6), 40, 40);
        } else {
            int dX = x + (int) (cellSize * 0.2); 
            g2d.drawRoundRect(dX, y, (int) (cellSize * 0.6), taille * cellSize, 40, 40);
        }
    }
    
     /**
     * Récupère la positon de la cellule cliquée à partir des coordonnées de la souris.
     *
     * @param e L'événement MouseEvent contenant les coordonnées de la souris.
     * @return La position de la cellule cliquée, ou null si le clic est en dehors de la grille.
     */
    public Position getCellCoordinates(MouseEvent e) {
        int cellSize = Math.min(getWidth() / dim, getHeight() / dim);
        int offsetX = (getWidth() - dim * cellSize) / 2;
        int offsetY = (getHeight() - dim * cellSize) / 2;

        int col = (e.getX() - offsetX) / cellSize;
        int row = (e.getY() - offsetY) / cellSize;

        if (row >= 0 && row < dim && col >= 0 && col < dim) {
            return new Position(row, col);
        }
        return null;
    }

    /**
     * Met à jour la vue lorsque le modèle de la mer est modifié.
     *
     * @param mer La mer mis à jour.
    */
    @Override
    public void modeleMisAJour(Mer mer) {
        repaint();
    }

    /**
     * Ajoute un écouteur de souris à la grille.
     *
     * @param listener L'écouteur à ajouter.
    */
    public void addMouseListenerToPanel(MouseAdapter listener) {
        if (mouseListener != null) {
            removeMouseListener(mouseListener);
        }
        this.mouseListener = listener;
        addMouseListener(mouseListener);
    }
}