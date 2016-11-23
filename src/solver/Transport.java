package solver;

/**
 * Generalisation de Transition.
 */
public abstract class Transport extends Transition {

    /**
     * duree fixe en seconde correspondant a la phase de controle et d'installation de l'utilisateur
     */
    protected double duree;

    /**
     * @param numero  numero de la descente
     * @param nom     nom eventuel de la descente
     * @param depart  point de depart de la transition
     * @param arrivee point d'arriver de la transition
     */
    public Transport(int numero, String nom, Point depart, Point arrivee, double duree) {
        super(numero, nom, depart, arrivee);
        this.duree = duree;
    }
}
