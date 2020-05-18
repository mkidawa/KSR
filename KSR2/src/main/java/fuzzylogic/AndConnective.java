package fuzzylogic;

import java.util.ArrayList;
import java.util.List;

public class AndConnective<T> extends LinguisticVariable<T> {
    private LinguisticVariable<T> firstSummarizer;
    private LinguisticVariable<T> secondSummarizer;
    private List<LinguisticVariable<T>> summarizers;

    public List<LinguisticVariable<T>> getSummarizers() {
        return summarizers;
    }

    public AndConnective(LinguisticVariable<T> firstSummarizer, LinguisticVariable<T> secondSummarizer) {
        this.firstSummarizer = firstSummarizer;
        this.secondSummarizer = secondSummarizer;
    }

    public AndConnective(List<LinguisticVariable<T>> summarizers) {
        this.summarizers = summarizers;
    }

    @Override
    public double getMembership(T obj) {
        double min = 1.0;
        for (LinguisticVariable<T> summarizer : summarizers) {
            double membership = summarizer.getMembership(obj);
            if (membership < min) {
                min = membership;
            }
        }
        // return min;
        return Math.min(firstSummarizer.getMembership(obj), secondSummarizer.getMembership(obj));
    }

    @Override
    public List<LinguisticVariable<T>> getAll() {
        List<LinguisticVariable<T>> allVariables = new ArrayList<>();
        allVariables.add(firstSummarizer);
        allVariables.add(secondSummarizer);
        return allVariables;
        // return getSummarizers();
    }
}
