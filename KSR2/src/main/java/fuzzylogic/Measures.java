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

    public double degreeOfImprecision(LinguisticVariable<T> summarizer, List<T> objects) {
        double product = 1.0;
        List<LinguisticVariable<T>> all = summarizer.getAll();

        for (LinguisticVariable<T> linguisticVariable : all) {
            product *= linguisticVariable.getSet().degreeOfFuzziness(objects);
        }

        return 1 - Math.pow(product, 1.0 / all.size());
    }

    public double degreeOfCovering(LinguisticVariable<T> qualifier, LinguisticVariable<T> summarizer, List<T> objects) {
        int t = 0;
        int h = 0;

        for (T obj : objects) {
            if (qualifier.getMembership(obj) > 0) {
                h++;
                if (summarizer.getMembership(obj) > 0) {
                    t++;
                }
            }
        }

        return (double) t / h;
    }

    public double degreeOfAppropriateness(LinguisticVariable<T> qualifier, LinguisticVariable<T> summarizer, List<T> objects) {
        double product = 1.0;
        List<LinguisticVariable<T>> all = summarizer.getAll();
        double T3 = degreeOfCovering(qualifier, summarizer, objects);

        for (LinguisticVariable<T> linguisticVariable : all) {
            int r = 0;
            for (T obj : objects) {
                if (linguisticVariable.getMembership(obj) > 0) {
                    r++;
                }
            }
            product *= (double) r / objects.size();
        }

        return Math.abs(product - T3);
    }

    public double lengthOfSummary(LinguisticVariable<T> summarizer) {
        return 2 * Math.pow(0.5, summarizer.getAll().size());
    }

    public double degreeOfQuantifierImprecision(Quantifier<T> quantifier, List<T> objects) {
        double supp = quantifier.set.getMembershipFunction().support();

        if (quantifier.isAbsolute()) {
            supp /= objects.size();
        }

        return supp;
    }

    public double degreeOfQuantifierCardinality(Quantifier<T> quantifier, List<T> objects) {
        double card = quantifier.set.getMembershipFunction().cardinality();

        if (quantifier.isAbsolute()) {
            card /= objects.size();
        }

        return 1.0 - card;
    }

    public double degreeOfSummarizerCardinality(LinguisticVariable<T> summarizer, List<T> objects) {
        double product = 1.0;
        List<LinguisticVariable<T>> all = summarizer.getAll();

        for (LinguisticVariable<T> linguisticVariable : all) {
            product *= linguisticVariable.set.getMembershipFunction().cardinality() / objects.size();
        }

        return 1 - Math.pow(product, 1 - all.size());
    }

    public double degreeOfQualifierImprecision(LinguisticVariable<T> qualifier, List<T> objects) {
        return 1 - qualifier.set.degreeOfFuzziness(objects);
    }

    public double degreeOfQualifierCardinality(LinguisticVariable<T> qualifier, List<T> objects) {
        double product = 1.0;
        List<LinguisticVariable<T>> all = qualifier.getAll();

        for (LinguisticVariable<T> linguisticVariable : all) {
            product *= linguisticVariable.set.getMembershipFunction().cardinality() / objects.size();
        }
        return 1 - Math.pow(product, (double) 1 / all.size());
    }

    public double lengthOfQualifier(LinguisticVariable<T> qualifier) {
        return 2 * Math.pow(0.5, qualifier.getAll().size());
    }


}
