package org.awmk.ksr1.knn;

import org.awmk.ksr1.extracting.CustomFeatures;
import org.awmk.ksr1.metrics.Metric;

import java.util.*;
import java.util.stream.Collectors;

public class KNNAlgorithm {
    private int k;
    private int testingSetPercent;

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public int getTestingSetPercent() {
        return testingSetPercent;
    }

    public void setTestingSetPercent(int testingSetPercent) {
        this.testingSetPercent = testingSetPercent;
    }

    public KNNAlgorithm(int k) {
        this.k = k;
    }

    public List<Float> calculateDistances (List<Float> featuresVector, List<List<Float>> vectors, Metric metric) {
        List<Float> distances = new ArrayList<>();
        for (List<Float> v : vectors) {
            distances.add(metric.calculateDistance(featuresVector, v));
        }
        return distances;
    }

    public Map<Float, String> calcDsitancesWithLabels (List<Float> featuresVector, List<CustomFeatures> vectors, Metric metric) {
        Map<Float, String> distancesMap = new HashMap<>();
        for (CustomFeatures cf : vectors) {
            distancesMap.put(metric.calculateDistance(featuresVector, cf.getFeatures()), cf.getCountry());
        }
        return distancesMap;
    }

    public void extractNeighbours(Map<Float, String> distances) {
        Map<Float, String> kNeighbours = distances
                .entrySet()
                .stream()
                .sorted(Map.Entry.<Float, String>comparingByKey().reversed())
                .limit(this.k)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        System.out.println(kNeighbours);
    }
}
