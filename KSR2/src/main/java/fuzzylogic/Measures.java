package fuzzylogic;

import java.util.List;

public class Measures<T> {
    public double degreeOfTruth(Quantifier<T> quantifier, LinguisticVariable<T> qualifier, LinguisticVariable<T> summarizer, List<T> objects) {
        double r = 0;
        double m = 0;

        if (qualifier == null) { // summary without qualifier
            for (T obj : objects) {
                r += summarizer.getMembership(obj);
            }
            m = objects.size();
        } else {
            for (T obj : objects) {
                r += Math.min(summarizer.getMembership(obj), qualifier.getMembership(obj));
                m += qualifier.getMembership(obj);
            }
        }

        if (quantifier.isAbsolute() && qualifier == null) {
            return quantifier.getSet().getMembershipFunction().getMembership(r);
        } else if (!quantifier.isAbsolute()) {
            return quantifier.getSet().getMembershipFunction().getMembership(r / m);
        } else return 0.0;
    }

}
