package applications;

import parseur.CParser;
import solver.*;

import java.util.List;
import java.util.Scanner;

public class Consol {

    Scanner scanner = new Scanner(System.in);

    public static void main(String args[]){
        /**
         * declaration des variables
         */
        Station station = new Station();
        CParser cparser = new CParser(station);
        Consol c = new Consol();
        /**
         * Chargement de la station
         */
        cparser.run("ressources/station.xml");

        /**
         * Chargement du menu
         */
        c.start(station);
    }


    private void menuReel(){
        boolean continu = true;
        while (continu) {
            System.out.println("Voulez vous traiter le probleme en temps reel (y/n) ?");
            String response = scanner.next();
            if (response.equals("y")) {
                Station.TEMPS_REEL = true;
                continu = false;
            } else if (response.equals("n")) {
                Station.TEMPS_REEL = false;
                continu = false;
            } else {
                System.out.println("Notez bien 'y' ou 'n'");
            }
        }
    }

    private void menuCoordonee(Station station){
        System.out.println("Entrez les coordonnees du point de depart");
        Point depart =  secureEntryPoint(station);
        System.out.println("Entrez les coordonnees du point d'arrivee");
        Point arrivee = secureEntryPoint(station);
        Solver solver = new Solver();

        ResultCalculTemps resultCalculTemps = solver.calculTemps(depart, arrivee, station);
        if (resultCalculTemps.getTypeResult() == TypeResult.NoWayFound){
            System.out.println("Il n'y a pas de chemin entre les deux points");
        } else if (resultCalculTemps.getTypeResult() == TypeResult.SamePoints){
            System.out.println("Vous etes deja arrive");
        } else {
            afficheResult(resultCalculTemps.getTransitionList(), depart, arrivee);
        }
    }

    private void afficheResult(List<Transition> transitions, Point depart, Point arrivee){
        double temps = 0.0;
        double denivele = 0.0;
        System.out.println("Plus court chemin entre les points " + depart +
                " et " + arrivee + "\n");
        for (Transition transition : transitions) {
            System.out.println("Transition numero : " + transition.getNumero() + " nom : " + transition.getNom() +
                    " depuis : " + transition.getDepart() + " vers : " + transition.getArrivee() + " cost : " + transition.temps() + " \n");
            temps += transition.temps();
            denivele += transition.denivele();
        }
        System.out.println("Duree du trajet : " + temps + " secondes, soit " + ((int) (temps / 3600)) + "h" + ((int) (temps % 3600) / 60)
                + "mn" + ((int) (temps % 3600) % 60) + "s\n");
        System.out.println("Cumul des deniveles : " + denivele + "m");

    }

    public void start(Station station){
        System.out.println("menu");
        String choix;
        boolean continu = true;
        while(continu) {
            System.out.println("1. calcul d un plus court chemin\n2. Quitter le menu");
            choix = scanner.next();
            if (choix.equals("1")) {
                menuReel();
                menuCoordonee(station);
            }
            else if (choix.equals("2"))
                continu = false;
            else
                System.out.println("Veuillez entrer un nombre valide");
        }
    }




    private int secureEntryInt(String message, String messageErr){
        String choixI;
        while(true) {
            System.out.print(message);
            choixI = scanner.next();
            try {
                return Integer.parseInt(choixI);
            } catch( Exception e){
                System.out.println(messageErr);
            }
        }
    }

    private Point secureEntryPoint(Station station){
        int x;
        int y;
        while (true) {
            x = secureEntryInt("X : ", "Entrez un nombre correct");
            y = secureEntryInt("Y : ", "Entrez un nombre correct");
            try {
                return station.getPoint(x, y);
            } catch (Solver.NoPointException npe) {
                System.out.println("Ce point n'exite pas !!! ");
            }
        }
    }
}
