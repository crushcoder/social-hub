package de.develcab.socialhub.flickr;

import de.develcab.socialhub.flickr.dto.Photo;
import de.develcab.socialhub.model.News;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jb on 23.01.17.
 */
@Service
public class PhotoMapper {
    private static final String URL_TEMPLATE = "https://www.flickr.com/photos/%s/%s/in/dateposted-public/lightbox/";
    private static final String HTML_TEMPLATE = "<a class=\"%s\" href=\"%s\"><img class=\"flickrPhoto\" src=\"%s\" alt=\"%s\"/></a>";
    private static final String THUMB_TEMPLATE_BIG = "https://c2.staticflickr.com/%s/%s/%s_%s_z.jpg";
    private static final String THUMB_TEMPLATE_SMALL = "https://c2.staticflickr.com/%s/%s/%s_%s_n.jpg";
    private static final String BIG_CSS_CLASS = "flickrThumbBig";
    private static final String SMALL_CSS_CLASS = "flickrThumbSmall";

    @Value("${flickr.userid:}")
    private String userId;


    public List<News> mapPhotos(List<Photo> photos) {
        List<News> news = new ArrayList<>();
        for (int i = 0; i < photos.size(); i++) {
            news.add(mapPhoto(photos.get(i), i));
        }
        return news;
    }

    private News mapPhoto(Photo photo, int index) {
        News newNews = new News();
        newNews.setHeadline(photo.getTitle());
        String url = String.format(URL_TEMPLATE, userId, photo.getId());
        newNews.setUrl(url);

        // Big thumb or small thumb?
        String classCss;
        String template;
        if(index == 0) {
             classCss = BIG_CSS_CLASS;
             template = THUMB_TEMPLATE_BIG;
        } else {
            classCss = SMALL_CSS_CLASS;
            template = THUMB_TEMPLATE_SMALL;
        }

        String thumbNailUrl = String.format(template, photo.getFarm(), photo.getServer(), photo.getId(), photo.getSecret());
        newNews.setRawHtml(String.format(HTML_TEMPLATE, classCss, url, thumbNailUrl, photo.getTitle()));

        return newNews;
    }
}
