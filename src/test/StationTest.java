package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import solver.Point;
import solver.Station;
import solver.Station.PointAlreadyExistException;
import solver.Station.NoPointException;

import static org.junit.jupiter.api.Assertions.fail;

class StationTest {
    Station station;
    Point a = new Point(1, "patate", 200, 42,42);

    @BeforeEach
    void setUp() {
         station = new Station();
        try {
            station.addPoint(a);
        } catch (PointAlreadyExistException e) {}
    }

    @Test
    void getPointXY() {

    }

    @Test
    void getPointWithId() {
        try {
            station.getPoint(404);
            fail("The point doesn't not exist but we can get it");
        } catch (NoPointException e) {}
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
    void addTransition() {

    }

}