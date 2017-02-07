package de.develcab.socialhub.model;

import java.time.LocalDateTime;

/**
 * Created by jb on 20.11.16.
 */
public class News {

    private String headline;
    private String teaser;
    private String url;
    private String rawHtml;
    private LocalDateTime createdAt;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getTeaser() {
        return teaser;
    }

    public void setTeaser(String teaser) {
        this.teaser = teaser;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRawHtml() {
        return rawHtml;
    }

    public void setRawHtml(String rawHtml) {
        this.rawHtml = rawHtml;
    }

    @Override
    public String toString() {
        return "News{" +
                "headline='" + headline + '\'' +
                ", teaser='" + teaser + '\'' +
                ", url='" + url + '\'' +
                ", rawHtml='" + rawHtml + '\'' +
                ", createdAt=" + createdAt +
                "}\n";
    }
}
