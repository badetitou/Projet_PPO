package solver;

/**
 * Created by badetitou on 16/11/16.
 */
public class Remontee extends Transition {
    private TypePiste type;
    private double duree;
    private double vitesse;

    public Remontee(int numero, String nom, Point depart, Point arrivee, TypePiste type, double duree, double vitesse) {
        super(numero, nom, depart, arrivee);
        this.type = type;
        this.duree = duree;
        this.vitesse = vitesse;
    }

    @Override
    public double temps() {
        double tempsAttente = 0;
        if (Station.TEMPS_REEL){
            tempsAttente = Math.random()*Station.MAX_WAITING;
        }
        return tempsAttente + duree + (vitesse/100)*(denivele());
    }
}
