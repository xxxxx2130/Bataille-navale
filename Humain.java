package model;

import java.util.Scanner;

public class Humain extends AbstractPlayer {
    private Scanner scanner = new Scanner(System.in);

    /**
     * Crée un nouveau joueur humain avec un nom et une taille de mer.
     *
     * @param nom       Le nom du joueur humain.
     * @param tailleMer La taille de la mer.
    */
    public Humain(String nom, int tailleMer) {
        super(nom, tailleMer);
    }


    /**
     * Permet au joueur humain de choisir une position pour tirer.
     * @return La position choisie par le joueur humain.
     */
    @Override
    public Position choisirPosition() {
        while (true) {
            System.out.println("\n" + nom + ", entrez une position (ex: A1, B12) : ");
            String input = scanner.nextLine().trim().toUpperCase();

            // Vérifie qu'il y a au moins 2 caractères (ex : A1, B12, etc.)
            if (input.length() < 2 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Position invalide. Réessayez.");
                continue;
            }

            try {
                int colonne = input.charAt(0) - 'A'; // Colonne à partir de la lettre
                int ligne = Integer.parseInt(input.substring(1)) - 1; // Le reste est la ligne

                if (ligne < 0 || ligne >= mer.getTaille() || colonne < 0 || colonne >= mer.getTaille()) {
                    System.out.println("Position hors de la grille. Réessayez.");
                    continue;
                }

                return new Position(ligne, colonne);

            } catch (NumberFormatException e) {
                System.out.println("Numéro de ligne invalide. Réessayez.");
            }
        }
    }


    /**
     * Reçoit et affiche le résultat d'un tir.
     * @param tir Le résultat reçu
    */
    @Override
    public void recevoirResultatTir(Tir tir) {
        super.afficherResultatTir(tir);
    }
}
