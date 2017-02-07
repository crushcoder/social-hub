package de.develcab.socialhub.model;

import java.util.List;

/**
 * Created by jb on 27.01.17.
 */
public class NewsStream {

    private String headline;
    private List<News> news;

    public NewsStream() {
    }

    public NewsStream(String headline, List<News> news) {
        this.headline = headline;
        this.news = news;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }
}
