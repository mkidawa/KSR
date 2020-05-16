package fuzzylogic;

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
}
