package de.develcab.socialhub;

import de.develcab.socialhub.model.NewsStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by jb on 29.01.17.
 */
@Service
public class SocialHub {

    @Autowired
    private ReadService readService;

    @Autowired
    private List<WritePlugin> writePlugins;

    public void collectAndSpreadNews() {
        List<NewsStream> news  = readService.read();
        Collections.sort(news, Comparator.comparing(o -> o.getOrder()));
        writePlugins.forEach(plugin -> plugin.write(news));
    }
}
