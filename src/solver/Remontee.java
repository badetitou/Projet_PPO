package solver;

/**
 * Classe Remontee represente une transition de type Remontee
 */
public class Remontee extends Transition {
    /**
     * type correspond au type de remontee, teleski etc...
     */
    private TypeRemontee type;
    /**
     * duree fixe en seconde correspondant a la phase de controle et d'installation de l'utilisateur
     */
    private double duree;
    /**
     * vitesse est le temps moyen en secondes pour monter 100m de denivele
     */
    private double vitesse;

    /**
     *
     * @param numero numero de la descente
     * @param nom eventuel de la transition
     * @param depart point de depart de la transition
     * @param arrivee point d'arriver de la transition
     * @param type type correspond au type de remontee, teleski etc...
     * @param duree duree fixe en seconde correspondant a la phase de controle et d'installation de l'utilisateur
     * @param vitesse vitesse est le temps moyen en secondes pour monter 100m de denivele
     */
    public Remontee(int numero, String nom, Point depart, Point arrivee, TypeRemontee type, double duree, double vitesse) {
        super(numero, nom, depart, arrivee);
        this.type = type;
        this.duree = duree;
        this.vitesse = vitesse;
    }

    /**
     *
     * @return temps de la remontee, en temps reel ou fixe selon le choix de l'utilisateur
     */
    @Override
    public double temps() {
        double tempsAttente = 0;
        if (Station.TEMPS_REEL){
            tempsAttente = Math.random()*Station.MAX_WAITING;
        }
        return tempsAttente + duree + (vitesse/100)*(denivele());
    }
}
