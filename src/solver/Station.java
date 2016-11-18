package solver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by badetitou on 16/11/16.
 */
public class Station {

    /**
     * En secondes
     */
    public static final double MAX_WAITING = 10.0;

    /**
     * Option sur l'utilisation du temps reel ou non
     */
    public static boolean TEMPS_REEL = false;

    private List<Point> points;
    private Map<Point, List<Transition>> transitions;

    public Station(){
        points = new ArrayList<>();
        transitions = new HashMap<>();
    }

    public void addPoint(Point p){
        /*if (points.contains(p)) Demander si verification de l'ajout d'un meme point
            throw*/
        points.add(p);
        transitions.put(p, new ArrayList<>());
    }

    public void addTransitionToPoint(Transition t, Point p){
        transitions.get(p).add(t);
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
