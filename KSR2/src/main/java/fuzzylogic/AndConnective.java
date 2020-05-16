package fuzzylogic;

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
}
