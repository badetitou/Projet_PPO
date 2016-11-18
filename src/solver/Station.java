package solver;

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

    private Map<Point, List<Transition>> transitionsPoint;
    private Map<Point, Boolean> mark;
    private Map<Point, Double> poids;
    private Map<Point, Point> pere;

    /**
     *
     * @param a Le point de depart
     * @param b Le point d'arrivee
     * @return le temps pour aller du point a au point b
     */
    public double calculTemps(Point a, Point b){
        return 0;
    }
}
