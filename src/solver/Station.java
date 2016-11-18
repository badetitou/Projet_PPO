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

    private List<Point> points = new ArrayList<>();
    private Map<Point, List<Transition>> transitions;




    private void initDijkstra(Map<Point, Boolean> mark, Map<Point, Double> potentiel, Map<Point, Point> pere){
        for (Point p : points) {
            mark.put(p, false);
            potentiel.put(p, Double.MAX_VALUE);
            pere.put(p, null);
        }
    }

    /**
     *
     * @param a Le point de depart
     * @param b Le point d'arrivee
     * @return le temps pour aller du point a au point b
     */
    public double calculTemps(Point a, Point b){
        Map<Point, Boolean> mark = new HashMap<>();
        Map<Point, Double> potentiel = new HashMap<>();
        Map<Point, Point> pere = new HashMap<>();
        initDijkstra(mark, potentiel, pere);

        Point courant;
        boolean end = false;
        potentiel.put(a,0.0);
        pere.put(a, a);

        while(!end){
            end = true;
            double potentielCourant = Double.MAX_VALUE;
            courant = null;
            for (Point p : points){
                if (!mark.get(p) && potentiel.get(p) < potentielCourant){
                    courant = p;
                    potentielCourant = potentiel.get(p);
                }
            }
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
        return potentiel.get(b);
    }
}
