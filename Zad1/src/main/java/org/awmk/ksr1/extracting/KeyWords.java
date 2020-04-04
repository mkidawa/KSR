package org.awmk.ksr1.extracting;

import org.awmk.ksr1.loading.Article;
import org.awmk.ksr1.loading.ArticleParser;
import org.awmk.ksr1.processing.Stemming;
import org.awmk.ksr1.processing.StopWords;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class KeyWords {
    // lista słów kluczowych dla każdego kraju
    private List<List<String>> keywords;

    public List<List<String>> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<List<String>> keywords) {
        this.keywords = keywords;
    }

    public KeyWords(File file) {

    }

    public KeyWords(List<Article> articles) throws IOException {
        List<List<String>> keywordsFromArticles = new ArrayList<List<String>>(); // rozmiar taki ile krajów

        Map<String, Float> usaMap = new HashMap();
        Map<String, Float> germanyMap = new HashMap();
        Map<String, Float> franceMap = new HashMap();
        Map<String, Float> ukMap = new HashMap();
        Map<String, Float> canadaMap = new HashMap();
        Map<String, Float> japanMap = new HashMap();

        for (Article a : articles) {
            for (String word : a.getBody()) {
                if(word.equals("Reuter") || word.equals("REUTER") || word.equals("mln")) {
                    continue;
                }
                float termFrequency = 0;
                for (String comparedWord : a.getBody()) {
                    if(comparedWord.equals(word)) {
                        termFrequency++;
                    }
                }
                termFrequency /= a.getBody().size();
                float inverseDocumentFrequency = 0;
                for (Article ar : articles) {
                    if(!ar.getCountry().equals(a.getCountry())) {
                        continue;
                    }
                    if(ar.getBody().contains(word)) {
                        inverseDocumentFrequency++;
                    }
                }
                inverseDocumentFrequency = (float) Math.log(articles.size() / inverseDocumentFrequency);
                float tfidf = termFrequency * inverseDocumentFrequency;
                //System.out.println(tfidf);
                switch (a.getCountry()) {
                    case "usa":
                        usaMap.put(word, tfidf);
                        break;
                    case "west-germany":
                        germanyMap.put(word, tfidf);
                        break;
                    case "france":
                        franceMap.put(word, tfidf);
                        break;
                    case "uk":
                        ukMap.put(word, tfidf);
                        break;
                    case "canada":
                        canadaMap.put(word, tfidf);
                        break;
                    case "japan":
                        japanMap.put(word, tfidf);
                        break;
                }
                //System.out.println(a.getCountry() + " " + word + " " + tfidf);
            }
        }

        Map<String, Float> usaWords = usaMap
                .entrySet()
                .stream()
                .sorted((Map.Entry.<String, Float>comparingByValue().reversed()))
                .limit(20)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        Map<String, Float> germanyWords = germanyMap
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Float>comparingByValue().reversed())
                .limit(20)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        Map<String, Float> franceWords = franceMap
                .entrySet()
                .stream()
                .sorted((Map.Entry.<String, Float>comparingByValue().reversed()))
                .limit(20)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        Map<String, Float> ukWords = ukMap
                .entrySet()
                .stream()
                .sorted((Map.Entry.<String, Float>comparingByValue().reversed()))
                .limit(20)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        Map<String, Float> canadaWords = canadaMap
                .entrySet()
                .stream()
                .sorted((Map.Entry.<String, Float>comparingByValue().reversed()))
                .limit(20)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        Map<String, Float> japanWords = japanMap
                .entrySet()
                .stream()
                .sorted((Map.Entry.<String, Float>comparingByValue().reversed()))
                .limit(20)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
//        System.out.println(usaWords);
//        System.out.println(germanyWords);
//        System.out.println(ukWords);
//        System.out.println(franceWords);
//        System.out.println(canadaWords);
//        System.out.println(japanWords);

        // dodać je do keywords zależnie od kraju
        keywordsFromArticles.add(new ArrayList<>(usaWords.keySet()));
        keywordsFromArticles.add(new ArrayList<>(germanyWords.keySet()));
        keywordsFromArticles.add(new ArrayList<>(franceWords.keySet()));
        keywordsFromArticles.add(new ArrayList<>(ukWords.keySet()));
        keywordsFromArticles.add(new ArrayList<>(canadaWords.keySet()));
        keywordsFromArticles.add(new ArrayList<>(japanWords.keySet()));

        this.keywords = keywordsFromArticles;
        writeToFile("src/main/resources/keywords/kw.txt", this.keywords);
    }

    public void writeToFile(String path, List<List<String>> data) {
        File file = new File(path);
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            for(List<String> l : data) {
                for(String w : l) {
                    fw.write(w + " ");
                }
                fw.write("|");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "KeyWords{" +
                "keywords=" + keywords +
                '}';
    }
}
