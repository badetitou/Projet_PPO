package solver;

/**
 * Classe Poin definie un point
 */
public class Point {

    /**
     * numero identifie un point de maniere unique
     */
    private int numero;
    /**
     * lieu identifie le lieu du point
     */
    private String lieu;
    /**
     * altitude correspond a la altitude du point
     */
    private double altitude;

    /**
     *
     * @param numero numero identifie un point de maniere unique
     * @param lieu lieu identifie le lieu du point
     * @param altitude altitude correspond a la altitude du point
     */
    public Point(int numero, String lieu, double altitude) {
        this.numero = numero;
        this.lieu = lieu;
        this.altitude = altitude;
    }

    /**
     *
     * @return l'altitude du point
     */
    public double getAltitude(){
        return altitude;
    }

    @Override
    public String toString(){
        return lieu;
    }
}
