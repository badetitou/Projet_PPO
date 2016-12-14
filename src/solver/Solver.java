package solver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solver {

    /**
     * Initialise les varaibles neccessaire l'algorithme de Dijkstra utilise dans calculTemps
     * @param mark Map de booleen, initialise a faux
     * @param potentiel Map de point vers potentiel initialise a Double.MAX_VALUE
     * @param pere Map de point vers point, initialise a null
     * @param graphe Le graphe sur lequel on travail
     */
    private void initDijkstra(Map<Point, Boolean> mark, Map<Point, Double> potentiel, Map<Point, Transition> pere, Graphe graphe){
        for (Point p : graphe.getPoints()) {
            mark.put(p, false);
            potentiel.put(p, Double.MAX_VALUE);
            pere.put(p, null);
        }
    }

    private void plusCourtChemin(Map<Point, Boolean> mark, Map<Point, Double> potentiel, Map<Point, Transition> pere, boolean end, Graphe graphe){
        while(!end) {
            Point courant = null;
            end = true;
            double potentielCourant = Double.MAX_VALUE;
            courant = null;
            for (Point p : graphe.getPoints()) {
                if (!mark.get(p) && potentiel.get(p) < potentielCourant) {
                    courant = p;
                    potentielCourant = potentiel.get(p);
                }
            }
            // Si nouveau point trouve, recherche des nouveaux potentiels
            if (courant != null) {
                end = false;
                mark.put(courant, true);
                for (Transition t : graphe.getTransition(courant)) {
                    if (potentiel.get(courant) + t.temps() < potentiel.get(t.getArrivee())) {
                        potentiel.put(t.getArrivee(), potentiel.get(courant) + t.temps());
                        pere.put(t.getArrivee(), t);
                    }
                }
            }
        }
    }

    private List<Transition> selectTransitions(Map<Point, Transition> pere, Point a, Point b) throws NullPointerException {
        List<Transition> result = new ArrayList<Transition>();
        Point tmp = b;
        while(!pere.get(tmp).getDepart().equals(a)) {
            result.add(0,pere.get(tmp));
            tmp = pere.get(tmp).getDepart();
        }
        result.add(0, pere.get(tmp));
        return result;
    }


    /**
     * utilise l'algorithme de Dijkstra pour trouver le plus court chemin entre le point a et b
     * @param a Le point de depart
     * @param b Le point d'arrivee
     * @return le temps pour aller du point a au point b, s'il n'y a pas de chemin entre a et b, la valeur retourner sera Double.MAX_VALUE
     */
    public ResultCalculTemps calculTemps(Point a, Point b, Graphe graphe) {
        if(a.equals(b)){
            return new ResultCalculTemps(null, TypeResult.SamePoints);
        }
        // Declaration
        boolean end = false;
        Map<Point, Boolean> mark = new HashMap<Point, Boolean>();
        Map<Point, Double> potentiel = new HashMap<Point, Double>();
        Map<Point, Transition> pere = new HashMap<Point, Transition>();
        //initialisation
        initDijkstra(mark, potentiel, pere, graphe);
        potentiel.put(a,0.0);
        pere.put(a, null);
        // recherche du nouveau point a explorer
        plusCourtChemin(mark,potentiel,pere,end, graphe);
        /* On cree une liste de transition, qui regroupe de toutes les transition de a vers b
        Celle-ci est stockée dans result
         */
        List<Transition> transitionList;
        try {
            transitionList = selectTransitions(pere, a, b);
        } catch (NullPointerException e){
            return new ResultCalculTemps(null, TypeResult.NoWayFound);
        }
        return new ResultCalculTemps(transitionList, TypeResult.WayFound);
    }

    public static class NoPointException extends Exception {
        @Override
        public void printStackTrace(){
            super.printStackTrace();
            System.err.println("The point doesn't exist");
        }
    }

    public static class PointAlreadyExistException extends Exception {
        @Override
        public void printStackTrace(){
            super.printStackTrace();
            System.err.println("The point already exist");
        }
    }
}
