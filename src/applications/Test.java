package applications;

import solver.Descente;
import solver.Point;
import solver.Station;
import solver.TypeDescente;

class Test {
    public static void main(String args[]){
        Station station = new Station();
        Point a = new Point(1,"a", 100);
        Point b = new Point(2,"b",200);
        Point c = new Point(3,"c",300);
        Point d = new Point(4,"d",200);

        station.addPoint(a);
        station.addPoint(b);
        station.addPoint(c);
        station.addPoint(d);

        station.addTransition(new Descente(1,"piste canard", a,b, TypeDescente.V, 100));
        station.addTransition(new Descente(2,"piste chevreuil", b,c, TypeDescente.B, 100));
        station.addTransition(new Descente(3,"piste kamasutra", a,d, TypeDescente.N, 200));
        station.addTransition(new Descente(4,"piste redon", d,c, TypeDescente.N, 0));

        System.out.println(station.calculTemps(a,c));
    }
}
