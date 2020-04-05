package org.awmk.ksr1.loading;

import java.util.*;

public class Article {
     private String title;
     private List<String> body;
     private String country;
     private List<String> topic;

    public Article(String title, String body, String country, List<String> topic) {
        this.title = title;
        this.body = splitBody(body);
        this.country = country;
        this.topic = topic;
    }

    public Article(Article article, List<String> body) {
        this.title = article.getTitle();
        this.body = body;
        this.country = article.getCountry();
        this.topic = article.getTopic();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getBody() {
        return body;
    }

    public void setBody(List<String> body) {
        this.body = body;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<String> getTopic() {
        return topic;
    }

    public void setTopic(List<String> topic) {
        this.topic = topic;
    }

    public List<String> splitBody(String body) {
        List<String> bodyList = new ArrayList<String>(Arrays.asList(body.replaceAll("[^a-zA-Z ]", "").split(" ")));
        bodyList.removeAll(Arrays.asList("", null));
        return bodyList;
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", country='" + country + '\'' +
                ", topic='" + topic + '\'' +
                '}';
    }
}
