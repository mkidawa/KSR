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

//        KeyWords kw = new KeyWords(parser.processArticles());
        KeyWords kw = new KeyWords("src/main/resources/keywords/kw.txt");
        //System.out.println(kw.toString());
        KNNAlgorithm knn = new KNNAlgorithm(10);
        DataSplitter ds = new DataSplitter();

        List<List<Article>> splittedDataset = ds.splitData(parser.getArticles(), 0.6); // index 0 - training, 1 - testing

        Measure measure = new GeneralizedNGramWithRestraints();

        List<CustomFeatures> featuresTraining = new ArrayList<>();
        for (Article a : splittedDataset.get(0)) {
            CustomFeatures cf = new CustomFeatures(measure, kw, a);
            //System.out.println(cf.getFeatures());
            featuresTraining.add(cf);
            //System.out.println(knn.calculateDistances(cf));
        }
        List<CustomFeatures> featuresTesting = new ArrayList<>();
        for (Article a : splittedDataset.get(1)) {
            CustomFeatures cf = new CustomFeatures(measure, kw, a);
            featuresTesting.add(cf);
        }

        for(CustomFeatures cf : featuresTesting) {
            String guessedCountry = knn.assignCountry(knn.extractNeighbours(knn.calcDsitancesWithLabels(cf.getFeatures(), featuresTraining, new EuclideanMetric())));
            System.out.println(cf.getCountry() + " guessed: " + guessedCountry);
            if (cf.getCountry().equals(guessedCountry)) {
                System.out.println("gituwa siema");
            }
            else System.out.println("no chujowo");
        }

    }
}
