package solver;

/**
 * classe Navette represente une transition de type descente
 */
public class Navette extends Transport {
    /**
     * type de Navette : bus, metro etc...
     */
    private TypeNavette type;

    /**
     *
     * @param numero numero de la descente
     * @param nom nom eventuel de la transition
     * @param depart point de depart de la transition
     * @param arrivee point d'arriver de la transition
     * @param type type de Navette : bus, metro etc...
     * @param duree duree du trajet en seconde
     */
    public Navette(int numero, String nom, Point depart, Point arrivee, TypeNavette type, double duree) {
        super(numero, nom, depart, arrivee, duree);
        this.type = type;
    }

    /**
     *
     * @return le temps d'attente
     */
    @Override
    public double temps() {
        double tempsAttente = 0;
        if (Station.TEMPS_REEL){
            tempsAttente = Math.random()*Station.MAX_WAITING;
        }
        return tempsAttente + duree;
    }
}
