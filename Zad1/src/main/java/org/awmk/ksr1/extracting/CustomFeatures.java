package org.awmk.ksr1.extracting;

import org.awmk.ksr1.loading.Article;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomFeatures {
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
        this.features.add(lengthOfWord(a));
        this.features.add((float) lengthOfArticle(a));
        this.features.add(frequencyOfShortWords(a));
        this.features.add(frequencyOfLongWords(a));
        this.features.add(frequencyOfUniqueWords(a));
        this.features.add(frequencyOfWordsStartingWithLower(a));
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

    public float lengthOfWord (Article a) {
        float length = 0;
        for(String w : a.getBody()) {
            length += w.length();
        }
        length /= a.getBody().size();
        return length;
    }

    public int lengthOfArticle (Article a) {
        return a.getBody().size();
    }

    public float frequencyOfShortWords (Article a) {
        float freqOfShortWords = 0;
        for(String w : a.getBody()) {
            if (w.length() < 4) {
                freqOfShortWords++;
            }
        }
        freqOfShortWords /= a.getBody().size();
        return freqOfShortWords;
    }

    public float frequencyOfLongWords (Article a) {
        float freqOfLongWords = 0;
        for (String w : a.getBody()) {
            if (w.length() > 7) {
                freqOfLongWords++;
            }
        }
        freqOfLongWords /= a.getBody().size();
        return freqOfLongWords;
    }

    public float numberOfUniqueWords (Article a) {
        int numOfUniqueWords = 0;
        for (String w : a.getBody()) {
            if (Collections.frequency(a.getBody(), w) == 1) {
                numOfUniqueWords++;
            }
        }
        return numOfUniqueWords;
    }

    public float frequencyOfUniqueWords (Article a) {
        return numberOfUniqueWords(a) / a.getBody().size();
    }

    public float numberOfWordsStartingWithLower (Article a) {
        int numOfWords = 0;
        for (String w : a.getBody()) {
            if (w.matches("\\b[a-z][a-zA-Z0-9]*")) {
                numOfWords++;
            }
        }
        return numOfWords;
    }

    public float frequencyOfWordsStartingWithLower (Article a) {
        return numberOfWordsStartingWithLower(a) / a.getBody().size();
    }
}
