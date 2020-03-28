package org.awmk.ksr1;

import org.awmk.ksr1.extracting.CustomFeatures;
import org.awmk.ksr1.extracting.KeyWords;
import org.awmk.ksr1.knn.KNNAlgorithm;
import org.awmk.ksr1.loading.Article;
import org.awmk.ksr1.loading.ArticleParser;
import org.awmk.ksr1.metrics.EuclideanMetric;
import org.awmk.ksr1.processing.Stemming;
import org.awmk.ksr1.processing.StopWords;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        ArticleParser parser = new ArticleParser();

        KeyWords kw = new KeyWords(parser.processArticles());
        //System.out.println(kw.toString());
        KNNAlgorithm knn = new KNNAlgorithm();
        List<CustomFeatures> features = new ArrayList<>();
        for (Article a : parser.getArticles()) {
            CustomFeatures cf = new CustomFeatures(kw, a);
            //System.out.println(cf.getFeatures());
            features.add(cf);
            //System.out.println(knn.calculateDistances(cf));
        }
        List<List<Float>> vectors = new ArrayList<>();
        for (CustomFeatures cf : features) {
            vectors.add(cf.getFeatures());
        }
        for (CustomFeatures cf : features) {
            System.out.println(knn.calculateDistances(cf.getFeatures(), vectors, new EuclideanMetric()));
        }

    }
}
