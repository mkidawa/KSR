package org.awmk.ksr1.processing;

import org.awmk.ksr1.loading.Article;

import java.util.ArrayList;
import java.util.HashSet;

import opennlp.tools.stemmer.PorterStemmer;

public class Stemming {
    public HashSet<String> stemWords (HashSet<String> toStem) {
        HashSet<String> stemmed = new HashSet<String>();
        PorterStemmer ps = new PorterStemmer();
        for (String b : toStem) {
            stemmed.add(ps.stem(b));
        }
        return stemmed;
    }
}
