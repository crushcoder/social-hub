package de.develcab.socialhub.wordpress.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by jb on 27.01.17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Blog {
    private int found;
    private List<Post> posts;

    public int getFound() {
        return found;
    }

    public void setFound(int found) {
        this.found = found;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
