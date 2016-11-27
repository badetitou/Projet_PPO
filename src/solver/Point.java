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

    private int x;
    private int y;

    /**
     *

     * @param numero numero identifie un point de maniere unique
     * @param lieu lieu identifie le lieu du point
     * @param altitude altitude correspond a la altitude du point
     */
    public Point(int numero, String lieu, double altitude, int x, int y) {
        this.numero = numero;
        this.lieu = lieu;
        this.altitude = altitude;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
