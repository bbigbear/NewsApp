package com.example.news.Bean;

/**
 * Created by bear on 2016/5/18.
 */
public class NewsInfo {
    private String news_content;
    private String news_img;

    public NewsInfo(String news_content, String news_img) {
        this.news_content = news_content;
        this.news_img = news_img;
    }

    public String getNews_content() {
        return news_content;
    }

    public void setNews_content(String news_content) {
        this.news_content = news_content;
    }

    public String getNews_img() {
        return news_img;
    }

    public void setNews_img(String news_img) {
        this.news_img = news_img;
    }
}
