package solver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * classe qui represente la station dans son ensemble
 */
public class Station {

    /**
     * En secondes
     */
    static final double MAX_WAITING = 10.0;

    /**
     * Option sur l'utilisation du temps reel ou non
     */
    static boolean TEMPS_REEL = false;

    /**
     * L'ensemble des points de la station
     */
    private List<Point> points;
    /**
     * L'ensemble des transitions de la station
     */
    private Map<Point, List<Transition>> transitions;

    public Station(){
        points = new ArrayList<>();
        transitions = new HashMap<>();
    }

    /**
     * Ajoute un point à la station
     * @param p le point à ajouter à la station
     */
    public void addPoint(Point p){
        /*if (points.contains(p)) Demander si verification de l'ajout d'un meme point
            throw*/
        points.add(p);
        transitions.put(p, new ArrayList<Transition>());
    }

    /**
     * Ajoute une transition à la station
     * @param t la transition à ajouter
     */
    public void addTransition(Transition t){
        transitions.get(t.getDepart()).add(t);
    }

    /**
     * Initialise les varaibles neccessaire l'algorithme de Dijkstra utilise dans calculTemps
     * @param mark Map de booleen, initialise a faux
     * @param potentiel Map de point vers potentiel initialise a Double.MAX_VALUE
     * @param pere Map de point vers point, initialise a null
     */
    private void initDijkstra(Map<Point, Boolean> mark, Map<Point, Double> potentiel, Map<Point, Point> pere){
        for (Point p : points) {
            mark.put(p, false);
            potentiel.put(p, Double.MAX_VALUE);
            pere.put(p, null);
        }
    }

    /**
     * utilise l'algorithme de Dijkstra pour trouver le plus court chemin entre le point a et b
     * @param a Le point de depart
     * @param b Le point d'arrivee
     * @return le temps pour aller du point a au point b, s'il n'y a pas de chemin entre a et b, la valeur retourner sera Double.MAX_VALUE
     */
    public double calculTemps(Point a, Point b){
        // Declaration
        Point courant;
        boolean end = false;
        Map<Point, Boolean> mark = new HashMap<>();
        Map<Point, Double> potentiel = new HashMap<>();
        Map<Point, Point> pere = new HashMap<>();

        //initialisation
        initDijkstra(mark, potentiel, pere);
        potentiel.put(a,0.0);
        pere.put(a, a);

        while(!end){
            // recherche du nouveau point a explorer
            end = true;
            double potentielCourant = Double.MAX_VALUE;
            courant = null;
            for (Point p : points){
                if (!mark.get(p) && potentiel.get(p) < potentielCourant){
                    courant = p;
                    potentielCourant = potentiel.get(p);
                }
            }
            // Si nouveau point trouve, recherche des nouveaux potentiels
            if (courant != null){
                end = false;
                mark.put(courant, true);
                for(Transition t : transitions.get(courant)){
                    if (potentiel.get(courant) + t.temps() < potentiel.get(t.getArrivee())){
                        potentiel.put(t.getArrivee(), potentiel.get(courant) + t.temps());
                        pere.put(t.getArrivee(), courant);
                    }
                }
            }
        }
        /* le potentiel minimum entre a et b est stockee dans potentiel b
         */
        return potentiel.get(b);
    }
}
