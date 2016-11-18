package solver;

/**
 * Created by badetitou on 16/11/16.
 */
public class Navette extends Transition {
    private TypeNavette type;
    private double duree;


    @Override
    public double temps() {
        double tempsAttente = 0;
        if (Station.TEMPS_REEL){
            tempsAttente = Math.random()*Station.MAX_WAITING;
        }
        return tempsAttente + duree;
    }
}
