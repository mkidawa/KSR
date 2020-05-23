package fuzzylogic;

import java.util.ArrayList;
import java.util.List;

public class AndConnective<T> extends Label<T> {
    private Label<T> firstSummarizer;
    private Label<T> secondSummarizer;
    private List<Label<T>> summarizers;

    public List<Label<T>> getSummarizers() {
        return summarizers;
    }

    public AndConnective(Label<T> firstSummarizer, Label<T> secondSummarizer) {
        this.firstSummarizer = firstSummarizer;
        this.secondSummarizer = secondSummarizer;
    }

    public AndConnective(List<Label<T>> summarizers) {
        this.summarizers = summarizers;
    }

    @Override
    public double getMembership(T obj) {
        double min = 1.0;
        for (Label<T> summarizer : summarizers) {
            double membership = summarizer.getMembership(obj);
            if (membership < min) {
                min = membership;
            }
        }
        // return min;
        return Math.min(firstSummarizer.getMembership(obj), secondSummarizer.getMembership(obj));
    }

    @Override
    public List<Label<T>> getAll() {
        List<Label<T>> allVariables = new ArrayList<>();
        allVariables.add(firstSummarizer);
        allVariables.add(secondSummarizer);
        return allVariables;
        // return getSummarizers();
    }
}
