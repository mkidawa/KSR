package org.awmk.ksr1.processing;

import org.awmk.ksr1.loading.Article;

import java.util.ArrayList;
import java.util.List;

import opennlp.tools.stemmer.PorterStemmer;

public class Stemming {
    public List<String> stemWords (List<String> toStem) {
        List<String> stemmed = new ArrayList<String>();
        PorterStemmer ps = new PorterStemmer();
        for (String b : toStem) {
            stemmed.add(ps.stem(b));
        }
        return stemmed;
    }
}
