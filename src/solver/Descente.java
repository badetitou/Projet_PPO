package solver;

/**
 * Created by badetitou on 16/11/16.
 */
public class Descente extends Transition {
    private TypeDescente type;
    private double vitesse;

    @Override
    public double temps() {
        return (vitesse/100)*(denivele());
    }
}
