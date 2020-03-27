package org.awmk.ksr1.extracting;

import org.awmk.ksr1.loading.Article;
import org.awmk.ksr1.loading.ArticleParser;
import org.awmk.ksr1.processing.Stemming;
import org.awmk.ksr1.processing.StopWords;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class KeyWords {
    // lista słów kluczowych dla każdego kraju
    private List<List<String>> keywords;

    public KeyWords(List<Article> articles) throws IOException {
        List<List<String>> keywordsFromArticles = new ArrayList<List<String>>(); // rozmiar taki ile krajów
        //List<List<String>> allWords = prepareWords(); // rozmiar taki ile artykułów
        //System.out.println(allWords.size());
//        for (List<String> articleBody : allWords) {
//            for (String word : articleBody) {
//                // term frequency
//                float termFrequency = 0;
//                for (String comparedWord : articleBody) {
//                    if(comparedWord.equals(word)) {
//                        termFrequency++;
//                    }
//                }
//                termFrequency /= articleBody.size();
//                System.out.println(allWords.indexOf(articleBody) + " " + word + " " + termFrequency);
//            }
//        }
        for (Article a : articles) {
            for (String word : a.getBody()) {
                float termFrequency = 0;
                for (String comparedWord : a.getBody()) {
                    if(comparedWord.equals(word)) {
                        termFrequency++;
                    }
                }
                termFrequency /= a.getBody().size();
                System.out.println(a.getCountry() + " " + word + " " + termFrequency);
            }
        }
        // usunąć duplikaty
        // przypisać do etykiety, albo i nie?

        // inverse document frequency
//        for (List<String> articleBody : allWords) {
//            for (String word : articleBody) {
//                float inverseDocumentFrequency = 0;
//                for (List<String> article : allWords) {
//                    if(article.contains(word)) {
//                        inverseDocumentFrequency++;
//                    }
//                }
//                inverseDocumentFrequency = allWords.size() / inverseDocumentFrequency;
//                System.out.println(allWords.indexOf(articleBody) + " " + word + " " + inverseDocumentFrequency);
//            }
//        }

        // sortowanie malejąco
        // wybranie pierwszych n np. 20 i to są słowa kluczowe
        // dodać je do keywords zależnie od kraju

        //Dictionary tfDict = new Hashtable();


        this.keywords = keywordsFromArticles;
    }

    public List<List<String>> prepareWords () throws IOException {
        List<List<String>> words = new ArrayList<List<String>>();
        ArticleParser parser = new ArticleParser();
        StopWords sw = new StopWords();
        Stemming s = new Stemming();
        for (Article a : parser.getArticles()) {
            words.add(s.stemWords(sw.removeStopWordsFromArticle(a)));
        }
        return words;
    }
}
