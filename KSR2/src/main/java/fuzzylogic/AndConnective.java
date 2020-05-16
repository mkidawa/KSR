package fuzzylogic;

import java.util.ArrayList;
import java.util.List;

public class AndConnective<T> extends LinguisticVariable<T> {
    private LinguisticVariable<T> firstSummarizer;
    private LinguisticVariable<T> secondSummarizer;

    public AndConnective(LinguisticVariable<T> firstSummarizer, LinguisticVariable<T> secondSummarizer) {
        this.firstSummarizer = firstSummarizer;
        this.secondSummarizer = secondSummarizer;
    }

    @Override
    public double getMembership(T obj) {
        return Math.min(firstSummarizer.getMembership(obj), secondSummarizer.getMembership(obj));
    }

    @Override
    public List<LinguisticVariable<T>> getAll() {
        List<LinguisticVariable<T>> allVariables = new ArrayList<>();
        allVariables.add(firstSummarizer);
        allVariables.add(secondSummarizer);
        return allVariables;
    }
}
