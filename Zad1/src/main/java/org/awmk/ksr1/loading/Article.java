package org.awmk.ksr1.loading;

import java.util.*;

public class Article {
     private String title;
     private HashSet<String> body;
     private String country;
     private String topic;

    public Article(String title, String body, String country, String topic) {
        this.title = title;
        this.body = splitBody(body);
        this.country = country;
        this.topic = topic;
    }

    public Article(Article article, HashSet<String> body) {
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

    public HashSet<String> getBody() {
        return body;
    }

    public void setBody(HashSet<String> body) {
        this.body = body;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public HashSet<String> splitBody(String body) {
        HashSet<String> bodyList = new HashSet<String>(Arrays.asList(body.replaceAll("[^a-zA-Z ]", "").split(" ")));
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
