package fuzzylogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;


public class FuzzySet<T> {
    //private HashMap<T, Double> map;
    private MembershipFunction membershipFunction;
    private Function<T, Double> function;

    public MembershipFunction getMembershipFunction() {
        return membershipFunction;
    }

//    public FuzzySet(HashMap<T, Double> map) {
//        this.map = map;
//    }

    public FuzzySet(MembershipFunction membershipFunction) {
        this.membershipFunction = membershipFunction;
    }

    public FuzzySet(MembershipFunction membershipFunction, Function<T, Double> function) {
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

    public double degreeOfFuzziness (List<T> objects) {
        return (double) support(objects).size() / objects.size();
    }

}
