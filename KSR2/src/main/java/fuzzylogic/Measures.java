package fuzzylogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Measures<T> {
    public double degreeOfTruth(Summary<T> summary) {
        double r = 0;
        double m = 0;

        if (summary.getQualifier() == null) { // summary without qualifier
            for (T obj : summary.getObjects()) {
                if (summary.getSummarizers().size() == 1) {
                    r += summary.getSummarizers().get(0).getMembership(obj);
                } else if (summary.getSummarizers().size() > 1) {
                    r += Label.<T>andConnective(summary.getSummarizers(), obj);
                }
            }
            m = summary.getObjects().size();
        } else {
            for (T obj : summary.getObjects()) {
                if (summary.getSummarizers().size() == 1) {
                    r += Math.min(summary.getSummarizers().get(0).getMembership(obj), summary.getQualifier().getMembership(obj));
                } else if (summary.getSummarizers().size() > 1) {
                    r += Math.min(Label.<T>andConnective(summary.getSummarizers(), obj), summary.getQualifier().getMembership(obj));
                }
                m += summary.getQualifier().getMembership(obj);
            }
        }

        if (summary.getQuantifier().isAbsolute() && summary.getQualifier() == null) {
            return summary.getQuantifier().getFuzzySet().getMembershipFunction().getMembership(r);
        } else if (!summary.getQuantifier().isAbsolute()) {
            return summary.getQuantifier().getFuzzySet().getMembershipFunction().getMembership(r / m);
        } else return 0.0;
    }

    public double degreeOfImprecision(Summary<T> summary) {
        double product = 1.0;

        for (Label<T> label : summary.getSummarizers()) {
            product *= label.getFuzzySet().degreeOfFuzziness(summary.getObjects());
        }

        return 1 - Math.pow(product, 1.0 / summary.getSummarizers().size());
    }

    public double degreeOfCovering(Summary<T> summary) {
        double qualifierMembership = 1.0;
        int t = 0;
        int h = 0;

        for (T obj : summary.getObjects()) {
            if (summary.getQualifier() != null) {
                qualifierMembership = summary.getQualifier().getMembership(obj);
            }
            if (qualifierMembership > 0) {
                h++;
                if (summary.getSummarizers().size() == 1) {
                    if (summary.getSummarizers().get(0).getMembership(obj) > 0) {
                        t++;
                    }
                } else if (summary.getSummarizers().size() > 1) {
                    if (Label.<T>andConnective(summary.getSummarizers(), obj) > 0) {
                        t++;
                    }
                }

            }
        }

        return (double) t / h;
    }

    public double degreeOfAppropriateness(Summary<T> summary) {
        double product = 1.0;
        double T3 = degreeOfCovering(summary);

        for (Label<T> summarizer : summary.getSummarizers()) {
            int r = 0;
            for (T obj : summary.getObjects()) {
                if (summarizer.getMembership(obj) > 0) {
                    r++;
                }
            }
            product *= (double) r / summary.getObjects().size();
        }

        return Math.abs(product - T3);
    }

    public double lengthOfSummary(Summary<T> summary) {
        return 2 * Math.pow(0.5, summary.getSummarizers().size());
    }

    public double degreeOfQuantifierImprecision(Summary<T> summary) {
        double supp = summary.getQuantifier().fuzzySet.getMembershipFunction().support();

        if (summary.getQuantifier().isAbsolute()) {
            supp /= summary.getObjects().size();
        }

        return supp;
    }

    public double degreeOfQuantifierCardinality(Summary<T> summary) {
        double card = summary.getQuantifier().fuzzySet.getMembershipFunction().cardinality();

        if (summary.getQuantifier().isAbsolute()) {
            card /= summary.getObjects().size();
        }

        return 1.0 - card;
    }

    public double degreeOfSummarizerCardinality(Summary<T> summary) {
        double product = 1.0;

        for (Label<T> summarizer : summary.getSummarizers()) {
            product *= summarizer.fuzzySet.getMembershipFunction().cardinality() / summary.getObjects().size();
        }

        return 1 - Math.pow(product, (double) 1 / summary.getSummarizers().size());
    }

    public double degreeOfQualifierImprecision(Summary<T> summary) {
        if (summary.getQualifier() == null) return 0.0;
        return 1 - summary.getQualifier().fuzzySet.degreeOfFuzziness(summary.getObjects());
    }

    public double degreeOfQualifierCardinality(Summary<T> summary) {
        if (summary.getQualifier() == null) return 0.0;

        double product = 1.0;
        List<Label<T>> all = summary.getQualifier().getAll();

        for (Label<T> linguisticVariable : all) {
            product *= linguisticVariable.fuzzySet.getMembershipFunction().cardinality() / summary.getObjects().size();
        }
        return 1 - Math.pow(product, (double) 1 / all.size());
    }

    public double lengthOfQualifier(Summary<T> summary) {
        if (summary.getQualifier() == null) return 0.0;
        return 2 * Math.pow(0.5, summary.getQualifier().getAll().size());
    }

    public double goodnessOfTheSummary(Summary<T> summary, List<Double> weights) {
        if (weights.size() != 11) return 0.0;
        List<Double> measures = new ArrayList<>(Arrays.asList(
                degreeOfTruth(summary),
                degreeOfImprecision(summary),
                degreeOfCovering(summary),
                degreeOfAppropriateness(summary),
                lengthOfSummary(summary),
                degreeOfQuantifierImprecision(summary),
                degreeOfQuantifierCardinality(summary),
                degreeOfSummarizerCardinality(summary),
                degreeOfQualifierImprecision(summary),
                degreeOfQualifierCardinality(summary),
                lengthOfQualifier(summary)
        ));
        double T = 0.0;
        for (int i = 0; i < measures.size(); i++) {
            T += weights.get(i) * measures.get(i);
        }
        return T;
    }

}
