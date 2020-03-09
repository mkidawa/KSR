package org.awmk.ksr1.loading;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ArticleParser {
    private String path;
    private Document doc;
    private List<Article> articles;

    public ArticleParser(String path) {
        this.path = path;
    }

    public void setDoc(Document doc) {
        this.doc = doc;
    }

    public Document getDoc() {
        return doc;
    }

    public String getPath() {
        return path;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public void importFromFile() throws IOException {
        File input = new File(this.path);
        setDoc(Jsoup.parse(input, "UTF-8", ""));
    }

    public void fillArticle() {
        for (Element el : doc.select("reuters")) {
            String title = el.select("title").text();
            String body = el.select("body").text();
            String places = el.select("places").select("d").text();
            String topics = el.select("topics").select("d").text();
            Article a = new Article(title, body, places, topics);
            System.out.println(a.toString());
            //articles.add(a);
        }
        //System.out.println(articles.toString());
    }


}
