package org.awmk.ksr1.loading;

public class Article {
     private String title;
     private String body;
     private String country;
     private String topic;

    public Article(String title, String body, String country, String topic) {
        this.title = title;
        this.body = body;
        this.country = country;
        this.topic = topic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
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
