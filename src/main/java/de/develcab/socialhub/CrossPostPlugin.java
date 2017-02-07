package de.develcab.socialhub;

import de.develcab.socialhub.model.News;

/**
 * Created by jbruester on 13.10.16.
 */
public interface CrossPostPlugin {

    void post(News link);
}
