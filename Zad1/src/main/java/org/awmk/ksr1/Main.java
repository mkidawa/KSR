package org.awmk.ksr1;

import org.awmk.ksr1.loading.ArticleParser;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ArticleParser parser = new ArticleParser();
        parser.importFromFile();
        parser.fillArticle();
    }
}
