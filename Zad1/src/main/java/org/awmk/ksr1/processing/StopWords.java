package org.awmk.ksr1.processing;

import org.awmk.ksr1.loading.Article;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class StopWords {
    private HashSet<String> words;

    public StopWords() throws FileNotFoundException {
        this.words = new HashSet<String>();
        File file = new File("D:\\Programowanie\\KSR\\Zad1\\src\\main\\resources\\stopwords\\stopwords.txt");
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            words.add(s.next());
        }
        s.close();
    }

    public HashSet<String> removeStopWordsFromArticle (Article a) {
        HashSet<String> bodyWithoutStopWords = new HashSet<String>();
        for (String b : a.getBody()) {
            if (!words.contains(b.toLowerCase())) {
                bodyWithoutStopWords.add(b);
            }
        }
        return bodyWithoutStopWords;
    }
}
