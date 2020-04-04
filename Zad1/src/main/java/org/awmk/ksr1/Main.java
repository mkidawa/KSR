package org.awmk.ksr1;

import org.awmk.ksr1.extracting.CustomFeatures;
import org.awmk.ksr1.extracting.KeyWords;
import org.awmk.ksr1.knn.DataSplitter;
import org.awmk.ksr1.knn.KNNAlgorithm;
import org.awmk.ksr1.loading.Article;
import org.awmk.ksr1.loading.ArticleParser;
import org.awmk.ksr1.metrics.EuclideanMetric;
import org.awmk.ksr1.metrics.GeneralizedNGram;
import org.awmk.ksr1.metrics.GeneralizedNGramWithRestraints;
import org.awmk.ksr1.metrics.Measure;
import org.awmk.ksr1.processing.Stemming;
import org.awmk.ksr1.processing.StopWords;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        ArticleParser parser = new ArticleParser();

        //KeyWords kw = new KeyWords(parser.processArticles());
        KeyWords kw = new KeyWords("src/main/resources/keywords/kw.txt");

        KNNAlgorithm knn = new KNNAlgorithm(10);
        DataSplitter ds = new DataSplitter();

        List<List<Article>> splittedDataset = ds.splitData(parser.getArticles(), 0.6); // index 0 - training, 1 - testing

        Measure measure = new GeneralizedNGram();

        boolean[] filter = new boolean[] {
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
        };

        List<CustomFeatures> featuresTraining = new ArrayList<>();
        for (Article a : splittedDataset.get(0)) {
            CustomFeatures cf = new CustomFeatures(measure, kw, a, filter);
            //System.out.println(cf.getFeatures());
            featuresTraining.add(cf);
            //System.out.println(knn.calculateDistances(cf));
        }
        List<CustomFeatures> featuresTesting = new ArrayList<>();
        for (Article a : splittedDataset.get(1)) {
            CustomFeatures cf = new CustomFeatures(measure, kw, a, filter);
            featuresTesting.add(cf);
        }

        List<String> predicted = new ArrayList<>();
        List<String> actual = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        labels.add("usa");
        labels.add("west-germany");
        labels.add("france");
        labels.add("uk");
        labels.add("canada");
        labels.add("japan");

        for(CustomFeatures cf : featuresTesting) {
            String predictedCountry = knn.assignCountry(knn.extractNeighbours(knn.calcDsitancesWithLabels(cf.getFeatures(), featuresTraining, new EuclideanMetric())));
            //System.out.println(cf.getCountry() + " guessed: " + predictedCountry);
            predicted.add(predictedCountry);
            actual.add(cf.getCountry());
        }

        Estimation estimation = new Estimation(predicted, actual, labels);
        System.out.println(estimation.getAccuracy());
        System.out.println(estimation.getPrecision());
        System.out.println(estimation.getRecall());

    }
}
