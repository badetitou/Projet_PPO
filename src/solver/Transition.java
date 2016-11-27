package solver;

/**
 * La classe abstract Transition represente une transition generique entre deux points
 */
public abstract class Transition {
    /**
     * numero de la transition
     */
    private int numero;
    /**
     * nom eventuel de la descente
     */
    private String nom;
    /**
     * point de depart de la transition
     */
    private Point depart;
    /**
     * point d'arriver de la transition
     */
    private Point arrivee;

    /**
     *
     * @param numero numero de la descente
     * @param nom nom eventuel de la descente
     * @param depart point de depart de la transition
     * @param arrivee point d'arriver de la transition
     */
    public Transition(int numero, String nom, Point depart, Point arrivee) {
        this.numero = numero;
        this.nom = nom;
        this.depart = depart;
        this.arrivee = arrivee;
    }

    public int getNumero() {
        return numero;
    }

    public String getNom() {
        return nom;
    }

    /**
     *
     * @return le point d'arrivee de la transition
     */
    public Point getArrivee() {
        return arrivee;
    }

    /**
     *
     * @return le le point de depart de la transition
     */
    public Point getDepart() {
        return depart;
    }

    /**

     *
     * @return retourne le denivele entre le point de depart et d'arrivee (valeur absolu)
     */
    public double denivele() {
        return Math.abs(depart.getAltitude() - arrivee.getAltitude());
    }

    /**
     * @return le temps entre depart et arrivee
     */
    public abstract double temps();

}
