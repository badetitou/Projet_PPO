package solver;

import java.util.List;

public interface Graphe {

    List<Point> getPoints();
    List<Transition> getTransition(Point depart);

}
