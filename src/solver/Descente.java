package solver;

/**
 * Created by badetitou on 16/11/16.
 */
public class Descente extends Transition {
    private TypeDescente type;
    private double vitesse;

    public Descente(int numero, String nom, Point depart, Point arrivee, TypeDescente type, double vitesse) {
        super(numero, nom, depart, arrivee);
        this.type = type;
        this.vitesse = vitesse;
    }

    @Override
    public double temps() {
        return (vitesse/100)*(denivele());
    }
}
