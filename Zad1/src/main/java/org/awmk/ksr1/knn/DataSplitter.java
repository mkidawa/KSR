package org.awmk.ksr1.knn;

import org.awmk.ksr1.loading.Article;
import org.awmk.ksr1.loading.ArticleParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataSplitter {
    public List<List<Article>> splitData(List<Article> toSplit, double trainingSetPercentage) {
        int pointOfSplitting = (int)(trainingSetPercentage * toSplit.size());
        Collections.shuffle(toSplit);
        List<List<Article>> splitted = new ArrayList<>();
        splitted.add(toSplit.subList(0, pointOfSplitting));
        splitted.add(toSplit.subList(pointOfSplitting, toSplit.size()));
        return splitted;
    }
}
