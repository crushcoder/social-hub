package de.develcab.socialhub.youtube.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by jb on 22.01.17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourceId {
    private String kind;
    private String videoId;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
