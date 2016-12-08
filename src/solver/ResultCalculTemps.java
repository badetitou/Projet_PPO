package solver;

import java.util.List;

public class ResultCalculTemps {

    private List<Transition> transitionList;
    private TypeResult typeResult;

    public ResultCalculTemps(List<Transition> transitionList, TypeResult typeResult) {
        this.transitionList = transitionList;
        this.typeResult = typeResult;
    }

    public List<Transition> getTransitionList() {
        return transitionList;
    }

    public TypeResult getTypeResult() {
        return typeResult;
    }

}
