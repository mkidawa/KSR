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


    public CustomFeatures(Measure m, KeyWords kw, Article a) throws IOException {
        this.features = new ArrayList<>();
        this.features.addAll(numberOfKeywords(m, kw, a));
        this.features.addAll(numberOfKeywordsFirst10Percent(m, kw, a));
        this.features.addAll(frequencyOfKeywords(m, kw, a));
        this.features.add(lengthOfWord(a));
        this.features.add((float) lengthOfArticle(a));
        this.features.add(frequencyOfShortWords(a));
        this.features.add(frequencyOfLongWords(a));
        this.features.add(frequencyOfUniqueWords(a));
        this.features.add(frequencyOfWordsStartingWithLower(a));
        this.features = normalizeVector(this.features);
        this.country = a.getCountry();
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
                    numOfKeywords += m.compareWords(word, keyword);
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
                    numOfKeywords += m.compareWords(a.getBody().get(i), keyword);
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
                    numOfKeywords += m.compareWords(word, keyword);
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
