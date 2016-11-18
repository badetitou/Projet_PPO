package solver;

/**
 * Created by badetitou on 16/11/16.
 */
public abstract class Transition {
    private int numero;
    private String nom;
    private Point depart;
    private Point arrivee;

    public Transition(int numero, String nom, Point depart, Point arrivee) {
        this.numero = numero;
        this.nom = nom;
        this.depart = depart;
        this.arrivee = arrivee;
    }

    public Point getArrivee() {
        return arrivee;
    }

    protected double denivele() {
        return Math.abs(depart.getAltitude() - arrivee.getAltitude());
    }

    /**
     * @return le temps entre depart et arrivee
     */
    public abstract double temps();

}
