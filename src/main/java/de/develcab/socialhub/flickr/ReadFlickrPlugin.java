package de.develcab.socialhub.flickr;

import de.develcab.socialhub.ReadPlugin;
import de.develcab.socialhub.flickr.dto.PhotosWrapper;
import de.develcab.socialhub.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by jb on 23.01.17.
 */
@Component
public class ReadFlickrPlugin implements ReadPlugin {
    private static final Logger LOGGER = Logger.getLogger(ReadFlickrPlugin.class.getName());
    private static final String PHOTOSTREAM_URL_TEMPLATE = "https://api.flickr.com/services/rest/?method=flickr.people.getPublicPhotos&api_key=%s&user_id=%s&per_page=%s&format=json&nojsoncallback=1";

    @Value("${flickr.userid:}")
    private String userId;

    @Value("${flickr.apikey:}")
    private String apiKey;

    @Value("${flickr.count}")
    private int count = 3;

    @Value("${flickr.headline}")
    private String headline;

    @Value("${flickr.order:1}")
    private int order;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PhotoMapper mapper;

    @Override
    public List<News> read() {
        if(apiKey == null || apiKey.isEmpty() || userId == null || userId.isEmpty()) {
            return Collections.emptyList();
        }

        LOGGER.info("Reading Flickr Stream");

        String url = String.format(PHOTOSTREAM_URL_TEMPLATE, apiKey, userId, count);
        ResponseEntity<PhotosWrapper> photoResponse = restTemplate.getForEntity(url, PhotosWrapper.class);

        if(photoResponse.getStatusCode().is2xxSuccessful()) {
            return mapper.mapPhotos(photoResponse.getBody().getPhotos().getPhotoList());
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public String getHeadline() {
        return headline != null ? headline : "";
    }

    @Override
    public int gerOrder() {
        return order;
    }
}
