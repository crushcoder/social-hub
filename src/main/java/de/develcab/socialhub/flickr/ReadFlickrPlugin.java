package de.develcab.socialhub.flickr;

import de.develcab.socialhub.ReadPlugin;
import de.develcab.socialhub.flickr.dto.Photo;
import de.develcab.socialhub.flickr.dto.Photos;
import de.develcab.socialhub.flickr.dto.PhotosWrapper;
import de.develcab.socialhub.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

/**
 * Created by jb on 23.01.17.
 */
@Component
public class ReadFlickrPlugin implements ReadPlugin {
    private static final String PHOTOSTREAM_URL_TEMPLATE = "https://api.flickr.com/services/rest/?method=flickr.people.getPublicPhotos&api_key=%s&user_id=%s&per_page=%s&format=json&nojsoncallback=1";

    @Value("${flickr.userid:}")
    private String userId;

    @Value("${flickr.apikey:}")
    private String apiKey;

    @Value("${flickr.count}")
    private int count = 3;

    @Value("${flickr.headline}")
    private String headline;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PhotoMapper mapper;

    @Autowired
    private Environment environment;

    @Override
    public List<News> read() {
        if(apiKey == null || apiKey.isEmpty() || userId == null || userId.isEmpty()) {
            return Collections.emptyList();
        }

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
}
