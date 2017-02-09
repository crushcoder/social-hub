package de.develcab.socialhub;

import de.develcab.socialhub.model.News;
import de.develcab.socialhub.model.NewsStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        // sort the plugins according to their order to get the output in correct sequence
        Collections.sort(readPlugins, Comparator.comparing(o -> Integer.valueOf(o.gerOrder())));
        readPlugins.forEach(readPlugin -> {
            List<News> news = readPlugin.read();
            if(!news.isEmpty()) {
                NewsStream stream = new NewsStream(readPlugin.getHeadline(), news, readPlugin.gerOrder());
                newsStreams.add(stream);
            }
        });
        LOGGER.info("All feeds read");

        return newsStreams;
    }
}
