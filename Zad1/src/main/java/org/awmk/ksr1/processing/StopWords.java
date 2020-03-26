package org.awmk.ksr1.processing;

import org.awmk.ksr1.loading.Article;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class StopWords {
    private List<String> words;

    public StopWords() throws FileNotFoundException {
        this.words = new LinkedList<String>();
        File file = new File("D:\\studia\\ksr\\KSR\\Zad1\\src\\main\\resources\\stopwords\\stopwords.txt");
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            words.add(s.next());
        }
        s.close();
    }

    public List<String> removeStopWordsFromArticle (Article a) {
        List<String> bodyWithoutStopWords = new ArrayList<String>();
        for (String b : a.getBody()) {
            if (!words.contains(b.toLowerCase())) {
                bodyWithoutStopWords.add(b);
            }
        }
        return bodyWithoutStopWords;
    }
}
