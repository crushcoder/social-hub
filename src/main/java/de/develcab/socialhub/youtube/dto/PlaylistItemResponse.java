package de.develcab.socialhub.youtube.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by jb on 21.01.17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaylistItemResponse {
    private String nextPageToken;
    private List<PlaylistItem> items;

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public List<PlaylistItem> getItems() {
        return items;
    }

    public void setItems(List<PlaylistItem> items) {
        this.items = items;
    }
}
