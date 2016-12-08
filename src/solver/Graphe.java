package solver;

import java.util.List;

public abstract class Graphe {

    public abstract List<Point> getPoints();
    public abstract List<Transition> getTransition(Point depart);

}
