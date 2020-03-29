package org.awmk.ksr1.metrics;

import java.util.List;

public class EuclideanMetric implements Metric {
    @Override
    public float calculateDistance (List<Float> first, List<Float> second) {
        float distance = 0;
        for (int i = 0; i < first.size(); i++) {
            distance += (first.get(i) - second.get(i)) * (first.get(i) - second.get(i));
        }
        return (float) Math.sqrt(distance);
    }

}
