package org.awmk.ksr1;

import org.awmk.ksr1.extracting.CustomFeatures;
import org.awmk.ksr1.extracting.KeyWords;
import org.awmk.ksr1.loading.Article;
import org.awmk.ksr1.loading.ArticleParser;
import org.awmk.ksr1.processing.Stemming;
import org.awmk.ksr1.processing.StopWords;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ArticleParser parser = new ArticleParser();

        KeyWords kw = new KeyWords(parser.processArticles());
        System.out.println(kw.toString());
//        for (Article a : parser.getArticles()) {
//            CustomFeatures cf = new CustomFeatures(kw, a);
//            System.out.println(cf.getFeatures());
//        }


    }
}
