package org.awmk.ksr1.extracting;

import org.awmk.ksr1.loading.Article;
import org.awmk.ksr1.loading.ArticleParser;
import org.awmk.ksr1.processing.StopWords;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomFeatures {
    //private KeyWords kw = new KeyWords(new ArticleParser().processArticles());

    private List<Float> features;

    public List<Float> getFeatures() {
        return features;
    }

    public void setFeatures(List<Float> features) {
        this.features = features;
    }


    public CustomFeatures(KeyWords kw, Article a) throws IOException {
        this.features = new ArrayList<>();
        this.features.addAll(numberOfKeywords(kw, a));
        this.features.addAll(numberOfKeywordsFirst10Percent(kw, a));
        this.features.addAll(frequencyOfKeywords(kw, a));
    }

    public List<Float> numberOfKeywords (KeyWords kw, Article a) {
        List<Float> numsOfKeywords = new ArrayList<>();
        for (List<String> countryKeywords : kw.getKeywords()) {
            float numOfKeywords = 0;
            for (String keyword : countryKeywords) {
                if(a.getBody().contains(keyword)) {
                    numOfKeywords++;
                }
            }
            numsOfKeywords.add(numOfKeywords);
        }
        return numsOfKeywords;
    }

    public List<Float> numberOfKeywordsFirst10Percent (KeyWords kw, Article a) {
        List<Float> numsOfKeywords = new ArrayList<>();
        for (List<String> countryKeywords : kw.getKeywords()) {
            float numOfKeywords = 0;
            for (String keyword : countryKeywords) {
                for (int i = 0; i < a.getBody().size() * 0.1; i++) {
                    if(a.getBody().get(i).equals(keyword)) {
                        numOfKeywords++;
                    }
                }
            }
            numsOfKeywords.add(numOfKeywords);
        }
        return numsOfKeywords;
    }

    public List<Float> frequencyOfKeywords (KeyWords kw, Article a) {
        List<Float> freqOfKeywords = new ArrayList<>();
        for (List<String> countryKeywords : kw.getKeywords()) {
            float numOfKeywords = 0;
            for (String keyword : countryKeywords) {
                if(a.getBody().contains(keyword)) {
                    numOfKeywords++;
                }
            }
            freqOfKeywords.add(numOfKeywords / a.getBody().size());
        }
        return freqOfKeywords;
    }
}
