package de.develcab.socialhub.model;

import java.util.List;

/**
 * Created by jb on 27.01.17.
 */
public class NewsStream {

    private String headline;
    private List<News> news;
    private int order;

    public NewsStream() {
    }

    public NewsStream(String headline, List<News> news, int order) {
        this.headline = headline;
        this.news = news;
        this.order = order;
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

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
