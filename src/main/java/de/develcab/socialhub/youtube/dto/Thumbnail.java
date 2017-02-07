package de.develcab.socialhub.youtube.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by jb on 21.01.17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Thumbnail {
    private String url;
    private Integer width;
    private Integer height;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
}
