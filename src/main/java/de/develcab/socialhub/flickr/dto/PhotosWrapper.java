package de.develcab.socialhub.flickr.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by jb on 25.01.17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhotosWrapper {
    private String stat;
    private Photos photos;

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }
}
