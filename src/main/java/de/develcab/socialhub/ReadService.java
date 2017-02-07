package de.develcab.socialhub;

import de.develcab.socialhub.model.News;
import de.develcab.socialhub.model.NewsStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by jbruester on 13.10.16.
 */
@Service
public class ReadService {
    private static final Logger LOGGER = Logger.getLogger(ReadService.class.getName());

    @Autowired
    private List<ReadPlugin> readPlugins;

    public List<NewsStream> read() {
        LOGGER.info("Reading feeds");
        List<NewsStream> newsStreams = new ArrayList<>();
        readPlugins.forEach(readPlugin -> {
            List<News> news = readPlugin.read();
            if(!news.isEmpty()) {
                NewsStream stream = new NewsStream(readPlugin.getHeadline(), news);
                newsStreams.add(stream);
            }
        });
        LOGGER.info("All feeds read");

        return newsStreams;
    }
}
