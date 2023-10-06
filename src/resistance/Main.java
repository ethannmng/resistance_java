package resistance;

import static java.lang.Math.pow;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * TP03 - Exercice 7
 * Exercices Java avec Netbeans
 * @author Ethan
 * @date 05.10.2023
 * @version 1.0
 */
public class Ex07 {
    private static Scanner sc = new Scanner(System.in); // Initialisation du service Scanner
    private static List<String> colors = new ArrayList<>(); // Création du tableau des couleurs
    public static void main(String[] args) {
        // Initilisation de la variable choise (int)
        int choice;
        
        // Initilisation du tableau des couleurs
        colors.add("Noir");
        colors.add("Marron");
        colors.add("Rouge");
        colors.add("Orange");
        colors.add("Jaune");
        colors.add("Vert");
        colors.add("Bleu");
        colors.add("Violet");
        colors.add("Gris");
        colors.add("Blanc");
        
        // Initilisation des variables colorA, colorB, colorC, ohmValue (int)
        int colorA = 0, colorB = 0, colorC = 0;
        int ohmValue = 0;
        
        // Cartouche du programme
        System.out.println("TP03");
        System.out.println("Ethan");
        System.out.println("05/10/2023");
        System.out.println("Exercices Java avec Netbeans");
        System.out.println("-----------------------------------");
        // * Information sur l'exercice
        System.out.println("Exercice 07: Couleurs des résistances");
        System.out.println("***********************************");
        //
        
        try {
            // Affichage du menu de sélection et récupération du choix
            choice = afficherChoix();
            
            // Selon le choix, on pose les questions nécessaires pour la conversion
            // Si la valeur entrée est incorrect, on redirige vers le menu de sélection
            if(choice == 1) {
                System.out.println("Conversion COULEUR ==> OHM");
                System.out.println("==========================");
                for(int i=0; i<=colors.size()-1; i++) {
                    System.out.print(i+":"+colors.get(i));
                    if(i != colors.size()-1) {
                        System.out.print(", ");
                    } else {
                        System.out.println("");
                    }
                }
                                
                System.out.println("Entrer la valeur du 1er anneau (1er chiffre signification (0-9): ");
                colorA = sc.nextInt();
                if(colorA < 0 || colorA > 9) {throw new Exception("Valeur incorrect");}
                
                System.out.println("Entrer la valeur du 2eme anneau (2eme chiffre signification (0-9): ");
                colorB = sc.nextInt();
                if(colorB < 0 || colorB > 9) {throw new Exception("Valeur incorrect");}
                
                System.out.println("Entrer la valeur du 3eme anneau (multiplicateur (0-9): ");
                colorC = sc.nextInt();
                if(colorC < 0 || colorC > 9) {throw new Exception("Valeur incorrect");}
                
                System.out.println("Couleurs["+colors.get(colorA)+"]["+colors.get(colorB)+"]["+colors.get(colorC)+"] = " + convertirCouleursOhms(colorA, colorB, colorC) + "Ω");
            } else if(choice == 2) {
                System.out.println("Conversion OHM ==> COULEUR");
                System.out.println("==========================");
                System.out.println("Entrer votre valeur en Ohm à convertir: ");
                ohmValue = sc.nextInt();
                
                System.out.println("Couleurs de la résistance pour "+ohmValue+" Ohms --> "+convertirOhmsCouleurs(ohmValue));

            } else {
                throw new Exception("Choix du menu incorrect !");
            }
            
        } catch(Exception e) {
            System.out.println("\u001B[31m" + e.getMessage()+"\n\n");
            main(null);
        }
    }
    
    /**
    * Fonction qui affiche un menu de sélection
    * @return Le choix de sélection
    */
    public static int afficherChoix() {
        int choice = 0; // Défintion du choix
        
        // Affichage des propositions
        System.out.println(""
                + "========================"
                + "\n"
                + "1 - Conversion COULEUR ==> Ohm"
                + "\n"
                + "2 - Conversion Ohm ==> COULEUR"
                + "\n"
                + "Saisir votre choix (0 pour quitter): ");
        
        // Récupération du choix du menu
        try {
            choice = sc.nextInt();
            if(choice < 1 || choice > 2) {
                if(choice == 0) {
                    exit(0);
                }
                throw new Exception("Choix du menu incorrect !");
            }
        } catch(Exception e) {
            System.out.println("\u001B[31m" + e.getMessage()+"\n");
            afficherChoix();
        }
        return choice;
    }
        
    /**
    * Fonction qui récupère la valeur en Ohm d'une résistance selon des couleurs
    * @param colorA La première couleur de la résistance
    * @param colorB La deuxième couleur de la résistance
    * @param colorC La troisième couleur de la résistance
    * @return La valeur en Ohm
    */
    public static double convertirCouleursOhms(int colorA, int colorB, int colorC) {        
        return ((colorA*10)+colorB) * pow(10, colorC);
    }
    
    /**
    * Fonction qui donne le nombre de chiffres d'une valeur
    * @param nb Le nombre qu'on veut récupérer le nombre de caractères
    * @return Le nombre de caractères de nb
    */
    public static int nbChiffres(long nb) {
        int a=0;
        while(nb!=0){nb=nb/10;++a;}
        return a;
    }
      
    /**
    * Fonction qui donne les couleurs d'une résistance
    * @param ohmValue La valeur en ohm
    * @return Les couleurs de la résistance
    */
    public static String convertirOhmsCouleurs(int ohmValue) {
        int nbDigit = nbChiffres(ohmValue);
        int val1 = ohmValue / (int)pow(10, nbDigit -2);
        
        int anneau1 = val1 / 10;
        int anneau2 = val1 - (anneau1*10);
        
        String ohm = colors.get(anneau1) + ", " + colors.get(anneau2) + ", " + colors.get(nbDigit -2);
        return ohm;
    }
}
