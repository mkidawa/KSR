package org.awmk.ksr1;

import org.awmk.ksr1.loading.Article;
import org.awmk.ksr1.loading.ArticleParser;
import org.awmk.ksr1.processing.Stemming;
import org.awmk.ksr1.processing.StopWords;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ArticleParser parser = new ArticleParser();
        parser.importFromFile();
        parser.fillArticle();

        StopWords sw = new StopWords();
        Stemming s = new Stemming();
        for (Article a : parser.getArticles()) {
            System.out.println(s.stemWords(sw.removeStopWordsFromArticle(a)));
        }

    }
}
