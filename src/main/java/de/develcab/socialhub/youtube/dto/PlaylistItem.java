package de.develcab.socialhub.youtube.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by jb on 21.01.17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaylistItem {
    private String id;
    private Snippet snippet;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Snippet getSnippet() {
        return snippet;
    }

    public void setSnippet(Snippet snippet) {
        this.snippet = snippet;
    }
}
