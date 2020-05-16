package fuzzylogic;

import java.util.ArrayList;
import java.util.List;

public class OrConnective<T> extends LinguisticVariable<T> {
    private LinguisticVariable<T> firstSummarizer;
    private LinguisticVariable<T> secondSummarizer;

    public OrConnective(LinguisticVariable<T> firstSummarizer, LinguisticVariable<T> secondSummarizer) {
        this.firstSummarizer = firstSummarizer;
        this.secondSummarizer = secondSummarizer;
    }

    @Override
    public double getMembership(T obj) {
        return Math.max(firstSummarizer.getMembership(obj), secondSummarizer.getMembership(obj));
    }

    @Override
    public List<LinguisticVariable<T>> getAll() {
        List<LinguisticVariable<T>> allVariables = new ArrayList<>();
        allVariables.add(firstSummarizer);
        allVariables.add(secondSummarizer);
        return allVariables;
    }
}
