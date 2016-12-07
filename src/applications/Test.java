package applications;

import solver.*;

class Test {
    public static void main(String args[]){
        Station station = new Station();
        Point a = new Point(1,"a", 100,1,2);
        Point b = new Point(2,"b",200,1,3);
        Point c = new Point(3,"c",300,2,2);
        Point d = new Point(4,"d",200,2,3);

        try {
            station.addPoint(a);
            station.addPoint(b);
            station.addPoint(c);
            station.addPoint(d);
        } catch (Station.PointAlreadyExistException e) {
            e.printStackTrace();
        }

        try {
            station.addTransition(new Descente(1, "piste canard", a, b, TypeDescente.V, 100));
            station.addTransition(new Descente(2, "piste chevreuil", b, c, TypeDescente.B, 100));
            station.addTransition(new Descente(3, "piste kamasutra", a, d, TypeDescente.N, 1000));
            station.addTransition(new Descente(4, "piste redon", d, c, TypeDescente.N, 0));
        } catch (Station.NoPointException e){
            e.printStackTrace();
        }

    }
}
