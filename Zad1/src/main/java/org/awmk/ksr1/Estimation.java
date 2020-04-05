package org.awmk.ksr1;

import java.util.List;

public class Estimation {
    private float accuracy;
    private float precision;
    private float recall;

    public float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }

    public float getPrecision() {
        return precision;
    }

    public void setPrecision(float precision) {
        this.precision = precision;
    }

    public float getRecall() {
        return recall;
    }

    public void setRecall(float recall) {
        this.recall = recall;
    }

    public Estimation (List<String> predicted, List<List<String>> actual) {
        this.accuracy = calculateAccuracyWhenTopics(predicted, actual);
    }

    public Estimation (List<String> predicted, List<String> actual, List<String> labels) {
        this.accuracy = calculateAccuracy(predicted, actual);
        this.precision = calculatePrecision(predicted, actual, labels);
        this.recall = calculateRecall(predicted, actual, labels);
    }

    public float calculateAccuracy (List<String> predicted, List<String> actual) {
        int trues = 0;
        for(int i = 0; i < predicted.size(); i++) {
            if (predicted.get(i).equals(actual.get(i))) {
                trues++;
            }
        }
        return (float) trues / predicted.size();
    }

    public float calculateAccuracyWhenTopics (List<String> predicted, List<List<String>> actual) {
        int trues = 0;
        for(int i = 0; i < predicted.size(); i++) {
            if (actual.get(i).contains(predicted.get(i))) {
                trues++;
            }
        }
        return (float) trues / predicted.size();
    }

    public float calculatePrecision (List<String> predicted, List<String> actual, List<String> labels) {
        int truePositives = 0;
        int falsePositives = 0;
        for (String label : labels) {
            int truePositive = 0;
            int falsePositive = 0;
            for (int i = 0; i < predicted.size(); i++) {
                if (predicted.get(i).equals(label)) {
                    if (predicted.get(i).equals(actual.get(i))) {
                        truePositive++;
                    } else {
                        falsePositive++;
                    }
                }
            }
            truePositives += truePositive;
            falsePositives += falsePositive;
        }
        return (float) truePositives / (truePositives + falsePositives);
    }

    public float calculateRecall (List<String> predicted, List<String> actual, List<String> labels) {
        int truePositives = 0;
        int falseNegatives = 0;
        for (String label : labels) {
            int truePositive = 0;
            int falseNegative = 0;
            for (int i = 0; i < predicted.size(); i++) {
                if (actual.get(i).equals(label)) {
                    if (predicted.get(i).equals(actual.get(i))) {
                        truePositive++;
                    } else {
                        falseNegative++;
                    }
                }
            }
            truePositives += truePositive;
            falseNegatives += falseNegative;
        }
        return (float) truePositives / (truePositives + falseNegatives);
    }
}
