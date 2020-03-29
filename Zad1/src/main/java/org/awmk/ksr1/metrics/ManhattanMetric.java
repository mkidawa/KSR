package org.awmk.ksr1.metrics;

import java.util.List;

public class ManhattanMetric implements Metric {
    @Override
    public float calculateDistance(List<Float> first, List<Float> second) {
        float distance = 0;
        for (int i = 0; i < first.size(); i++) {
            distance += Math.abs(first.get(i) - second.get(i));
        }
        return distance;
    }
}
