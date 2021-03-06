package de.develcab.socialhub;

import de.develcab.socialhub.model.News;

import java.util.List;

/**
 * Interface for plugins that read out a social service or one specific stream in a service.
 * This also creates the Html snippets to include.
 *
 * Created by jbruester on 13.10.16.
 */
public interface ReadPlugin {
    /**
     * Read out the content and create the output file.
     *
     * @return the link to the generated snippets or an empty list
     */
    List<News> read();

    /**
     * Gets the headline for all News, used mainly for the HTML representation on the webpage.
     *
     *
     * @return the headline or an empty String
     */
    String getHeadline();

    /**
     * A number that defines the ordering of the output of all plugins.
     * Lowest number will be first, equal numbers will be in random order, eventually different from run to run.
     *
     * @return the order, lowest number will be first in output
     */
    int gerOrder();
}
