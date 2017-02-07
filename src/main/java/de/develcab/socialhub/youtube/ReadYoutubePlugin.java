package de.develcab.socialhub.youtube;

import de.develcab.socialhub.ReadPlugin;
import de.develcab.socialhub.model.News;
import de.develcab.socialhub.youtube.dto.PlaylistItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

/**
 * Created by jbruester on 13.10.16.
 */
@Component
public class ReadYoutubePlugin implements ReadPlugin {
    private static final String PlAYLIST_ITEM_URL_TEMPLATE = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId=%s&key=%s&maxResults=%s";

    @Value("${youtube.headline}")
    private String headline;

    @Value("${youtube.count:3}")
    private int count = 3;

    @Value("${youtube.playlistid:}")
    private String playlistId;

    @Value("${youtube.apikey:}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NewsMapper mapper;

    @Override
    public List<News> read() {
        if(apiKey == null || playlistId == null || apiKey.isEmpty() || playlistId.isEmpty()) {
            return Collections.emptyList();
        }

        String apiUrl = String.format(PlAYLIST_ITEM_URL_TEMPLATE, playlistId, apiKey, count);
        ResponseEntity<PlaylistItemResponse> response = restTemplate.getForEntity(apiUrl, PlaylistItemResponse.class);

        if(response.getStatusCode().is2xxSuccessful()) {
            return mapper.mapToNews(response.getBody().getItems());
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public String getHeadline() {
        return headline != null ? headline : "";
    }
}
