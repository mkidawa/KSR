package fuzzylogic;

import java.util.List;
import java.util.function.Function;

public class LinguisticVariable<T> {
    private String name;
    private CrispSet<T> universe;
    private Function<T, Double> function;

    public LinguisticVariable(String name, CrispSet<T> universe, Function<T, Double> function) {
        this.name = name;
        this.universe = universe;
        this.function = function;
    }

    public LinguisticVariable(String name, List<T> universe, Function<T, Double> function) {
        this.name = name;
        this.universe = new CrispSet<>(universe);
        this.function = function;
    }

    public LinguisticVariable(String name, FuzzySet<T> fuzzySet) {
        this.name = name;
        this.universe = fuzzySet.getUniverse();
        this.function = fuzzySet.getFunction();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CrispSet<T> getUniverse() {
        return universe;
    }

    public void setUniverse(CrispSet<T> universe) {
        this.universe = universe;
    }

    public Function<T, Double> getFunction() {
        return function;
    }

    public void setFunction(Function<T, Double> function) {
        this.function = function;
    }
}
