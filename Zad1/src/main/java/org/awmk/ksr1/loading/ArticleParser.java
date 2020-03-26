package org.awmk.ksr1.loading;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ArticleParser {
    private File[] files;
    private String path;
    private List<Document> docs;
    private List<Article> articles;

    public ArticleParser() {
        File f = new File("D:\\studia\\ksr\\KSR\\Zad1\\src\\main\\resources\\articles");
        this.files = f.listFiles();
    }

    public ArticleParser(String path) {
        this.path = path;
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
        docs = new LinkedList<Document>();
        for (File f : files) {
            docs.add(Jsoup.parse(f, "UTF-8", ""));
        }
    }

    public void fillArticle() {
        articles = new LinkedList<Article>();
        for (Document doc : docs) {
            for (Element el : doc.select("reuters")) {
                String title = el.select("title").text();
                String dateline = el.select("dateline").text();
                String body = el.select("text").text();
                String places = el.select("places").select("d").text();
                String topics = el.select("topics").select("d").text();
                Article a = new Article(title, body, places, topics);
                //System.out.println(a.toString());
                if (!a.getBody().contains("blah") &&
                        !a.getCountry().contains(" ") &&
                        a.getCountry().matches("usa|west-germany|france|uk|canada|japan")) {
                    articles.add(a);
                }
            }
        }
        //System.out.println(doc.body());
        for (Article ar : articles) {
            System.out.println(ar.toString());
        }
        System.out.println(articles.size());
    }


}
