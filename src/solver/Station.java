package solver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * classe qui represente la station dans son ensemble
 */
public class Station implements Graphe {

    /**
     * En secondes
     */
    static final double MAX_WAITING = 1800.0;

    /**
     * Option sur l'utilisation du temps reel ou non
     */
    public static boolean TEMPS_REEL = false;

    /**
     * L'ensemble des points de la station
     */
    private List<Point> points;

    /**
     * L'ensemble des transitions de la station
     */
    private Map<Point, List<Transition>> transitions;

    public Station(){
        points = new ArrayList<Point>();
        transitions = new HashMap<Point, List<Transition>>();
    }

    @Override
    public List<Point> getPoints() {
        return points;
    }

    @Override
    public List<Transition> getTransition(Point point) {
        return transitions.get(point);
    }

    public Point getPoint(int x, int y) throws Solver.NoPointException {
        for (Point p : points){
            if (p.getX() == x && p.getY()==y)
                return p;
        }
        throw new Solver.NoPointException();
    }

    public Point getPoint (int ident) throws Solver.NoPointException {
        for (Point p : points){
            if (p.getNumero() == ident )
                return p;
        }
        throw new Solver.NoPointException();
    }

    /**
     * Ajoute un point à la station
     * @param p le point à ajouter à la station
     */
    public void addPoint(Point p) throws Solver.PointAlreadyExistException {
        if (points.contains(p)) {
            throw new Solver.PointAlreadyExistException();
        } else {
            points.add(p);
            transitions.put(p, new ArrayList<Transition>());
        }
    }

    /**
     * Ajoute une transition à la station
     * @param t la transition à ajouter
     */
    public void addTransition(Transition t) throws Solver.NoPointException {
        if (!points.contains(t.getDepart()) || !points.contains(t.getArrivee()))
            throw new Solver.NoPointException();
        transitions.get(t.getDepart()).add(t);
    }

}
