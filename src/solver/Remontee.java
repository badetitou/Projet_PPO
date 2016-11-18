package solver;

/**
 * Created by badetitou on 16/11/16.
 */
public class Remontee extends Transition {
    private TypePiste type;
    private double duree;
    private double vitesse;

    @Override
    public double temps() {
        double tempsAttente = 0;
        if (Station.TEMPS_REEL){
            tempsAttente = Math.random()*Station.MAX_WAITING;
        }
        return tempsAttente + duree + (vitesse/100)*(denivele());
    }
}
