package solver;

/**
 * La classe Descente represente une transition de type Descente
 */
public class Descente extends Transition {
    /**
     * type correspond a la difficulte des descente
     */
    private TypeDescente type;
    /**
     * vitesse represente le temps en moyen pour descendre 100m
     */
    private double vitesse;

    /**
     *
     * @param numero numero de la descente
     * @param nom nom eventuel de la descente
     * @param depart point de depart de la transition
     * @param arrivee point d'arriver de la transition
     * @param type type correspond a la difficulte de la descente
     * @param vitesse vitesse represente le temps moyen pour descendre 100m
     */
    public Descente(int numero, String nom, Point depart, Point arrivee, TypeDescente type, double vitesse) {
        super(numero, nom, depart, arrivee);
        this.type = type;
        this.vitesse = vitesse;
    }

    /**
     *
     * @return le temps de la transition
     */
    @Override
    public double temps() {
        return (vitesse/100)*(denivele());
    }
}
