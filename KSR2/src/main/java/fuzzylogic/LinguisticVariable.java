package fuzzylogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class LinguisticVariable<T> {
    protected String name; // horse age
    protected String label; // young
    protected FuzzySet<T> set;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public FuzzySet<T> getSet() {
        return set;
    }

    public LinguisticVariable() {}

    public LinguisticVariable(String name, String label, FuzzySet<T> set) {
        this.name = name;
        this.label = label;
        this.set = set;
    }

    public LinguisticVariable(String name, String label, MembershipFunction membershipFunction, Function<T, Double> function) {
        this.name = name;
        this.label = label;
        this.set = new FuzzySet<T>(membershipFunction, function);
    }

    public double getMembership(T obj) {
        return set.getMembership(obj);
    }

    public List<LinguisticVariable<T>> getAll() {
        return new ArrayList<>(Collections.singletonList(this));
    }
}
