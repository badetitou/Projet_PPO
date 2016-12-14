package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parseur.CParser;
import solver.*;

import static org.junit.jupiter.api.Assertions.*;

class DikjstraTest {

    Station station;
    Solver solver;
    Point a;
    Point b;
    Point c;
    Point d;


    @BeforeEach
    void setUp(){
        station = new Station();
        solver = new Solver();
        CParser cParser = new CParser(station);
        cParser.run("ressources/station.xml");
    }

    @Test
    void calculTempsExample1() {
        double denivele = 0;
        double time = 0;
        try {
            ResultCalculTemps resultCalculTemps = solver.calculTemps(station.getPoint(36, 14), station.getPoint(21, 9), station);
            assertEquals(5, resultCalculTemps.getTransitionList().size());
            for (Transition t : resultCalculTemps.getTransitionList()){
                denivele += t.denivele();
                time += t.temps();
            }
            assertEquals(1586, denivele);
            assertEquals(2986, (int)time);
        } catch (Solver.NoPointException e){
            fail("The point exist");
        }
    }

    @Test
    void calculTempsExample2() {
        double denivele = 0;
        double time = 0;
        try {
            ResultCalculTemps resultCalculTemps = solver.calculTemps(station.getPoint(42, 24), station.getPoint(5, 28), station);
            assertEquals(5, resultCalculTemps.getTransitionList().size());
            for (Transition t : resultCalculTemps.getTransitionList()){
                denivele += t.denivele();
                time += t.temps();
            }
            assertEquals(1835, denivele);
            assertEquals(3590, (int)time);
        } catch (Solver.NoPointException e){
            fail("The point exist");
        }
    }

    @Test
    void calculTempsExample3() {
        double denivele = 0;
        double time = 0;
        try {
            ResultCalculTemps resultCalculTemps = solver.calculTemps(station.getPoint(36, 14), station.getPoint(5, 28), station);
            assertEquals(6, resultCalculTemps.getTransitionList().size());
            for (Transition t : resultCalculTemps.getTransitionList()){
                denivele += t.denivele();
                time += t.temps();
            }
            assertEquals(1400, denivele);
            assertEquals(2799, (int)time);
        } catch (Solver.NoPointException e){
            fail("The point exist");
        }
    }

    @Test
    void calculTempsSameStartAndArrive(){
        try {
            ResultCalculTemps resultCalculTemps = solver.calculTemps(station.getPoint(36, 14), station.getPoint(36, 14), station);
            assertNull(resultCalculTemps.getTransitionList());
        } catch (Solver.NoPointException e){
            fail("The point exist");
        }
    }

    @Test
    void calculTempsImpossibleWay(){
        Point g = new Point(5000, "Nowhere",404,42,42);
        try {
            station.addPoint(g);
            station.addTransition(new Transition(10000,"Strange link", g, station.getPoint(1)) {
                @Override
                public double temps() {
                    return 0;
                }
            });
        } catch (Solver.PointAlreadyExistException e) {
            fail("No problem here");
            e.printStackTrace();
        } catch (Solver.NoPointException e){
            fail("No problem here");
            e.printStackTrace();
        }
        try {
            ResultCalculTemps resultCalculTemps = solver.calculTemps(station.getPoint(1), g, station);
            if (resultCalculTemps.getTypeResult() == TypeResult.NoWayFound){
                assert true;
            } else {
                fail("We have to get a Null Pointer Exception");
            }
        } catch (Solver.NoPointException e){
            fail("We have to get a Null Pointer Exception");
            e.printStackTrace();
        } catch (NullPointerException e){

        }
    }

}