package org.awmk.ksr1;

import org.awmk.ksr1.extracting.CustomFeatures;
import org.awmk.ksr1.extracting.KeyWords;
import org.awmk.ksr1.knn.DataSplitter;
import org.awmk.ksr1.knn.KNNAlgorithm;
import org.awmk.ksr1.loading.Article;
import org.awmk.ksr1.loading.ArticleParser;
import org.awmk.ksr1.metrics.*;
import org.awmk.ksr1.processing.Stemming;
import org.awmk.ksr1.processing.StopWords;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static int k = 7;
    private static double trainingSet = 0.9;
    private static boolean[] filter = new boolean[] {
            false,
            false,
            false,
            true,
            true,
            true,
            true,
            true,
            true
    };
    private static Metric metric = new EuclideanMetric();
    private static Measure measure = new GeneralizedNGramWithRestraints();

    public static void main(String[] args) throws IOException {
        ArticleParser parser = new ArticleParser("XD");

        //KeyWords kw = new KeyWords(parser.processArticles());
        //KeyWords kw = new KeyWords("src/main/resources/keywords/kw.txt");
        KeyWords kw = new KeyWords(0);

        KNNAlgorithm knn = new KNNAlgorithm(k);
        DataSplitter ds = new DataSplitter();

        List<List<Article>> splittedDataset = ds.splitData(parser.getArticles(), trainingSet); // index 0 - training, 1 - testing

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

        List<List<String>> actualWhenTopics = new ArrayList<>();

        for(CustomFeatures cf : featuresTesting) {
            String predictedLabel = knn.assignCountry(knn.extractNeighbours(knn.calcDsitancesWithLabelsWhenTopics(cf.getFeatures(), featuresTraining, metric)));
            //System.out.println(cf.getCountry() + " guessed: " + predictedCountry);
            predicted.add(predictedLabel);
            actualWhenTopics.add(cf.getTopics());
            System.out.println("......");
            System.out.println("predicted: " + predictedLabel);
            System.out.println("actual: " + cf.getTopics());
        }

        //System.out.println(predicted);
        //System.out.println(actual);
        Estimation estimation = new Estimation(predicted, actualWhenTopics);
        System.out.println(estimation.getAccuracy());
        //System.out.println(estimation.getPrecision());
        //System.out.println(estimation.getRecall());

    }
}
