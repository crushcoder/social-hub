package de.develcab.socialhub;

import de.develcab.socialhub.model.News;
import de.develcab.socialhub.model.NewsStream;

import java.util.List;
import java.util.Map;

/**
 * Plugins to output all news.
 * E.g. writes a file to a webserver to be included in a webpage.
 *
 * Created by jb on 20.11.16.
 */
public interface WritePlugin {

    /**
     * Write the news in a usable form.
     *
     * @param news Object with headline for a news type and a list of the news.
     */
    void write(List<NewsStream> news);
}
