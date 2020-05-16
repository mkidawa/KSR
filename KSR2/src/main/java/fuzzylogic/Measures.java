package fuzzylogic;

import java.util.List;

public class Measures<T> {
    public double degreeOfTruth(Quantifier<T> quantifier, LinguisticVariable<T> summarizer, List<T> objects) {
        double r = 0;

        for(T obj : objects) {
            r += summarizer.getMembership(obj);
        }

        if(quantifier.isAbsolute()) {
            return quantifier.getSet().getMembershipFunction().getMembership(r);
        } else {
            return quantifier.getSet().getMembershipFunction().getMembership(r / objects.size());
        }
    }

}
