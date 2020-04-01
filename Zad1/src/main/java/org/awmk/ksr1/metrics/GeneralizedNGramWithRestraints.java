package org.awmk.ksr1.metrics;

public class GeneralizedNGramWithRestraints implements Measure{
    @Override
    public float compareWords(String first, String second) {
        int N = Math.max(first.length(), second.length());
        int numberOfPossibleGrams = 2 / (N - first.length() + 1)*(N - first.length() + 2)
                - (N - second.length() + 1)*(N - second.length());
        int numberOfActualGrams = 0;
        for (int i = 0; i < first.length(); i++) {
            for (int j = 0; j < first.length() - i + 1; j++) {
                String gram = first.substring(j, j + i);
                if(second.contains(gram)) {
                    numberOfActualGrams++;
                }
            }
        }
        return (float) numberOfActualGrams * numberOfPossibleGrams;
    }
}
