package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import solver.Point;
import solver.Station;
import solver.Station.PointAlreadyExistException;
import solver.Station.NoPointException;
import solver.Transition;

import static org.junit.jupiter.api.Assertions.fail;

class StationTest {
    Station station;
    Point a = new Point(1, "patate", 200, 42,42);
    Point b = new Point(2, "canard", 200, 18,18);

    @BeforeEach
    void setUp() {
         station = new Station();
        try {
            station.addPoint(a);
            station.addPoint(b);
        } catch (PointAlreadyExistException e) {}
    }

    @Test
    void getPointXY() {
        try {
            station.getPoint(404,404);
            fail("The point doesn't not exist but we can get it");
        } catch (NoPointException e){}
        try {
            station.getPoint(42,42);
        } catch (NoPointException e){
            fail("The point exist but we cannot get it");
        }
    }

    @Test
    void getPointWithId() {
        try {
            station.getPoint(404);
            fail("The point doesn't not exist but we can get it");
        } catch (NoPointException e) {}
        Point c;
        try {
            c = station.getPoint(1);
            assert (c.equals(a));
        } catch (NoPointException e) {
            fail("The point exist but we cannot get it");
        }
    }

    @Test
    void addSamePoint() {
        Point b = new Point(1,"patate chaude",403,42,42);
        try {
            station.addPoint(b);
            fail("The point already exists but we can add it");
        } catch (PointAlreadyExistException e){}
    }

    @Test
    void addTransitionOK() {
        try {
            station.addTransition(new Transition(1, "m_transition", a, b) {
                @Override
                public double temps() {
                    return 0;
                }
            });
        } catch (NoPointException e) {
            fail("We can add the transition but it failed");
        }
    }
    @Test
    void addTransitionFailedArrive() {
        try {
            station.addTransition(new Transition(2, "m_failing_transition", a, null) {
                @Override
                public double temps() {
                    return 0;
                }
            });
            fail("The arrive point of the transition is null but we can add the transition");
        } catch (NoPointException e) {
        }
    }

    @Test
    void addTransitionFailedStart() {
        try {
            station.addTransition(new Transition(2, "m_failing_transition", null, a) {
                @Override
                public double temps() {
                    return 0;
                }
            });
            fail("The start point of the transition is null but we can add the transition");
        } catch (NoPointException e) {
        }
    }

    @Test
    void addTransitionFaildedBothPointNull(){
        try {
            station.addTransition(new Transition(2,"m_failing_transition",null,null) {
                @Override
                public double temps() {
                    return 0;
                }
            });
            fail("Both points of the transition is null but we can add the transition");
        } catch (NoPointException e){}
    }

}