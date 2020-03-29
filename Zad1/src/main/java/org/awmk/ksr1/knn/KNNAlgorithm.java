package org.awmk.ksr1.knn;

import org.awmk.ksr1.extracting.CustomFeatures;
import org.awmk.ksr1.metrics.Metric;

import java.util.*;
import java.util.stream.Collectors;

public class KNNAlgorithm {
    private int k;

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
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

    public Map<Float, String> extractNeighbours(Map<Float, String> distances) {
        Map<Float, String> kNeighbours = distances
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .limit(this.k)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        return kNeighbours;
    }

    public String assignCountry(Map<Float, String> kNeighbours) {
        List<String> countries = new ArrayList<>(kNeighbours.values());
        System.out.println(countries);
        String assignedCountry = countries
                .stream()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new))
                .entrySet()
                .iterator()
                .next()
                .getKey();
        return assignedCountry;
    }
}
