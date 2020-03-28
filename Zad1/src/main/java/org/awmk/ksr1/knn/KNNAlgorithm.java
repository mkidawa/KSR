package org.awmk.ksr1.knn;

import org.awmk.ksr1.metrics.Metric;

import java.util.ArrayList;
import java.util.List;

public class KNNAlgorithm {
    public List<Float> calculateDistances (List<Float> featuresVector, List<List<Float>> vectors, Metric metric) {
        List<Float> distances = new ArrayList<>();
        for (List<Float> v : vectors) {
            distances.add(metric.calculateDistance(featuresVector, v));
        }
        return distances;
    }
}
