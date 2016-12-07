package test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parseur.CParser;
import solver.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by badetitou on 07/12/16.
 */
class DikjstraTest {

    Station station;
    Point a;
    Point b;
    Point c;
    Point d;


    @BeforeEach
    void setUp(){
        station = new Station();
        CParser cParser = new CParser(station);
        cParser.run("ressources/station.xml");
    }

    @Test
    void calculTempsExample1() {
        List<Transition> fastestWay;
        double denivele = 0;
        double time = 0;
        try {
            fastestWay = station.calculTemps(station.getPoint(36, 14), station.getPoint(21, 9));
            assertEquals(5, fastestWay.size());
            for (Transition t : fastestWay){
                denivele += t.denivele();
                time += t.temps();
            }
            assertEquals(1586, denivele);
            assertEquals(2986, (int)time);
        } catch (Station.NoPointException e){
            fail("The point exist");
        }
    }

    @Test
    void calculTempsExample2() {
        List<Transition> fastestWay;
        double denivele = 0;
        double time = 0;
        try {
            fastestWay = station.calculTemps(station.getPoint(42, 24), station.getPoint(5, 28));
            assertEquals(5, fastestWay.size());
            for (Transition t : fastestWay){
                denivele += t.denivele();
                time += t.temps();
            }
            assertEquals(1835, denivele);
            assertEquals(3590, (int)time);
        } catch (Station.NoPointException e){
            fail("The point exist");
        }
    }

    @Test
    void calculTempsExample3() {
        List<Transition> fastestWay;
        double denivele = 0;
        double time = 0;
        try {
            fastestWay = station.calculTemps(station.getPoint(36, 14), station.getPoint(5, 28));
            assertEquals(6, fastestWay.size());
            for (Transition t : fastestWay){
                denivele += t.denivele();
                time += t.temps();
            }
            assertEquals(1400, denivele);
            assertEquals(2799, (int)time);
        } catch (Station.NoPointException e){
            fail("The point exist");
        }
    }

    @Test
    void calculTempsSameStartAndArrive(){
        List<Transition> fastestWay;
        try {
            fastestWay = station.calculTemps(station.getPoint(36, 14), station.getPoint(36, 14));
            assertNull(fastestWay);
        } catch (Station.NoPointException e){
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
        } catch (Station.PointAlreadyExistException e) {
            fail("No problem here");
            e.printStackTrace();
        } catch (Station.NoPointException e){
            fail("No problem here");
            e.printStackTrace();
        }
        try {
            station.calculTemps(station.getPoint(1), g);
            fail("We have to get a Null Pointer Exception");
        } catch (Station.NoPointException e){
            fail("We have to get a Null Pointer Exception");
            e.printStackTrace();
        } catch (NullPointerException e){

        }
    }

}