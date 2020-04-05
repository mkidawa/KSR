package org.awmk.ksr1.extracting;

import org.awmk.ksr1.loading.Article;
import org.awmk.ksr1.metrics.Measure;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomFeatures {
    private List<Float> features;
    private String country;
    private List<String> topics;

    public List<Float> getFeatures() {
        return features;
    }

    public void setFeatures(List<Float> features) {
        this.features = features;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public CustomFeatures(Measure m, KeyWords kw, Article a, boolean[] filter) throws IOException {
        this.features = new ArrayList<>();

        if (filter[0]) this.features.addAll(numberOfKeywords(m, kw, a));
        if (filter[1]) this.features.addAll(numberOfKeywordsFirst10Percent(m, kw, a));
        if (filter[2]) this.features.addAll(frequencyOfKeywords(m, kw, a));
        if (filter[3]) this.features.add(lengthOfWord(a));
        if (filter[4]) this.features.add((float) lengthOfArticle(a));
        if (filter[5]) this.features.add(frequencyOfShortWords(a));
        if (filter[6]) this.features.add(frequencyOfLongWords(a));
        if (filter[7]) this.features.add(frequencyOfUniqueWords(a));
        if (filter[8]) this.features.add(frequencyOfWordsStartingWithLower(a));

        this.features = normalizeVector(this.features);
        this.country = a.getCountry();
        this.topics = a.getTopic();
    }

    public List<Float> numberOfKeywords (Measure m, KeyWords kw, Article a) {
        List<Float> numsOfKeywords = new ArrayList<>();
        for (List<String> countryKeywords : kw.getKeywords()) {
            float numOfKeywords = 0;
            for (String keyword : countryKeywords) {
//                if(a.getBody().contains(keyword)) {
//                    numOfKeywords++;
//                }
                for (String word : a.getBody()) {
                    numOfKeywords += m.compareWords(word.toLowerCase(), keyword.toLowerCase());
                }
            }
            numsOfKeywords.add(numOfKeywords);
        }
        return numsOfKeywords;
    }

    public List<Float> numberOfKeywordsFirst10Percent (Measure m, KeyWords kw, Article a) {
        List<Float> numsOfKeywords = new ArrayList<>();
        for (List<String> countryKeywords : kw.getKeywords()) {
            float numOfKeywords = 0;
            for (String keyword : countryKeywords) {
                for (int i = 0; i < a.getBody().size() * 0.1; i++) {
//                    if(a.getBody().get(i).equals(keyword)) {
//                        numOfKeywords++;
//                    }
                    numOfKeywords += m.compareWords(a.getBody().get(i).toLowerCase(), keyword.toLowerCase());
                }
            }
            numsOfKeywords.add(numOfKeywords);
        }
        return numsOfKeywords;
    }

    public List<Float> frequencyOfKeywords (Measure m, KeyWords kw, Article a) {
        List<Float> freqOfKeywords = new ArrayList<>();
        for (List<String> countryKeywords : kw.getKeywords()) {
            float numOfKeywords = 0;
            for (String keyword : countryKeywords) {
//                if(a.getBody().contains(keyword)) {
//                    numOfKeywords++;
//                }
                for (String word : a.getBody()) {
                    numOfKeywords += m.compareWords(word.toLowerCase(), keyword.toLowerCase());
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

    public List<Float> normalizeVector (List<Float> features) {
        float vectorLength = 0;
        for (Float f : features) {
            vectorLength += f * f;
        }
        vectorLength = (float) Math.sqrt(vectorLength);
        List<Float> normalizedFeatures = new ArrayList<>();
        for (Float f : features) {
            normalizedFeatures.add(f / vectorLength);
        }
        return normalizedFeatures;
    }
}
