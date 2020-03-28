package org.awmk.ksr1.metrics;

import java.util.List;

public interface Metric {
    float calculateDistance(List<Float> first, List<Float> second);
}
