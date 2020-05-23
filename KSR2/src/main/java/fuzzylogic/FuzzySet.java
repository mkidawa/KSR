package fuzzylogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;


public class FuzzySet<T> {
    private CrispSet<T> universe;
    private MembershipFunction membershipFunction;
    private Function<T, Double> function;

    public CrispSet<T> getUniverse() {
        return universe;
    }

    public Function<T, Double> getFunction() {
        return function;
    }

    public MembershipFunction getMembershipFunction() {
        return membershipFunction;
    }

    public FuzzySet(MembershipFunction membershipFunction) {
        this.membershipFunction = membershipFunction;
    }

    public FuzzySet(MembershipFunction membershipFunction, Function<T, Double> function) {
        this.membershipFunction = membershipFunction;
        this.function = function;
    }

    public FuzzySet(List<T> objects, MembershipFunction membershipFunction, Function<T, Double> function) {
        this.universe = new CrispSet<>(objects);
        this.membershipFunction = membershipFunction;
        this.function = function;
    }

    public FuzzySet(CrispSet<T> objects, MembershipFunction membershipFunction, Function<T, Double> function) {
        this.universe = objects;
        this.membershipFunction = membershipFunction;
        this.function = function;
    }

    public double getMembership(T obj) {
        return membershipFunction.getMembership(function.apply(obj));
    }

    public List<T> support (List<T> objects) {
        List<T> sup = new ArrayList<>();
        for (T obj : objects) {
            if (getMembership(obj) > 0) {
                sup.add(obj);
            }
        }
        return sup;
    }

    public List<T> support () {
        List<T> sup = new ArrayList<>();
        for (T obj : universe.getSet()) {
            if (getMembership(obj) > 0) {
                sup.add(obj);
            }
        }
        return sup;
    }

    public double degreeOfFuzziness (List<T> objects) {
        return (double) support(objects).size() / objects.size();
    }

}
