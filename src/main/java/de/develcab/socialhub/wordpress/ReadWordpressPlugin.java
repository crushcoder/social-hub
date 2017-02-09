package de.develcab.socialhub.wordpress;

import de.develcab.socialhub.ReadPlugin;
import de.develcab.socialhub.model.News;
import de.develcab.socialhub.wordpress.dto.Blog;
import de.develcab.socialhub.wordpress.dto.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by jb on 27.01.17.
 */
@Service
public class ReadWordpressPlugin implements ReadPlugin {
    private static final Logger LOGGER = Logger.getLogger(ReadWordpressPlugin.class.getName());
    private static final String URL_TEMPLATE = "https://public-api.wordpress.com/rest/v1.1/sites/%s/posts/?number=%s&fields=date,title,URL,excerpt";
    private static final String HTML_TEMPLATE = "<h4 class=\"wordpressNews\">%s</h4><a class=\"wordpressNews\" href=\"%s\">%s</a>";


    @Value("${wordpress.headline}")
    private String headline;

    @Value("${wordpress.sites:}")
    private List<String> siteNames;

    @Value("${wordpress.count}")
    private int count = 3;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<News> read() {
        if(siteNames == null || siteNames.isEmpty()) {
            return Collections.emptyList();
        }

        List<News> news = new ArrayList<>();

        siteNames.forEach(siteName -> {
            LOGGER.info("Reading wordpress blog " + siteName);
            String url = String.format(URL_TEMPLATE, siteName, count);
            ResponseEntity<Blog> blogResponse = restTemplate.getForEntity(url, Blog.class);

            if(blogResponse.getStatusCode().is2xxSuccessful()) {
                news.addAll(mapNews(blogResponse.getBody().getPosts()));
            }
        });

        return news;
    }

    public List<News> mapNews(List<Post> posts) {
        List<News> news = new ArrayList<>();
        posts.forEach(post -> {
            News newNews = new News();
            newNews.setHeadline(post.getTitle());
            newNews.setUrl(post.getUrl());
            newNews.setCreatedAt(LocalDateTime.ofInstant(post.getDate().toInstant(), ZoneOffset.UTC));
            newNews.setTeaser(post.getExcerpt());
            String html = String.format(HTML_TEMPLATE, post.getTitle(), post.getUrl(), post.getExcerpt());
            newNews.setRawHtml(html);
            news.add(newNews);
        });

        return news;
    }

    @Override
    public String getHeadline() {
        return headline;
    }
}
