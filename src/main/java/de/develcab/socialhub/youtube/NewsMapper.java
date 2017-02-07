package de.develcab.socialhub.youtube;

import de.develcab.socialhub.model.News;
import de.develcab.socialhub.youtube.dto.PlaylistItem;
import de.develcab.socialhub.youtube.dto.Snippet;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jb on 23.01.17.
 */
@Service
public class NewsMapper {
    private static final String VIDEO_URL_TEMPLATE = "https://www.youtube.com/watch?v=%s";
    private static final String RAW_HTML_TEMPLATE = "<object class=\"youtubePlayer\" data=\"http://www.youtube-nocookie.com/embed/%s?rel=0\"></object>";

    public List<News> mapToNews(List<PlaylistItem> items) {
        List<News> news = new ArrayList<>();
        items.forEach(item -> news.add(mapToNews(item)));
        return news;
    }

    private News mapToNews(PlaylistItem item) {
        News newNews = new News();
        Snippet snippet = item.getSnippet();
        newNews.setHeadline(snippet.getTitle());
        newNews.setTeaser(snippet.getDescription());
        newNews.setUrl(String.format(VIDEO_URL_TEMPLATE, snippet.getResourceId().getVideoId()));
        newNews.setCreatedAt(LocalDateTime.ofInstant(snippet.getPublishedAt().toInstant(), ZoneOffset.UTC));
        newNews.setRawHtml(String.format(RAW_HTML_TEMPLATE, snippet.getResourceId().getVideoId()));

        return newNews;
    }
}
