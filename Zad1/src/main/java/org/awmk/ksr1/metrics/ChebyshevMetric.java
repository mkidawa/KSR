package org.awmk.ksr1.metrics;

import java.util.List;

public class ChebyshevMetric implements Metric {
    @Override
    public float calculateDistance(List<Float> first, List<Float> second) {
        float maxDistance = 0;
        for (int i = 0; i < first.size(); i++) {
            float distance = Math.abs(first.get(i) - second.get(i));
            if (distance > maxDistance) {
                maxDistance = distance;
            }
        }
        return maxDistance;
    }
}
