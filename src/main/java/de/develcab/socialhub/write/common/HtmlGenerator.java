package de.develcab.socialhub.write.common;

import de.develcab.socialhub.model.NewsStream;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.System.lineSeparator;

/**
 * Generates HTML output for the writer.
 *
 * Created by jb on 29.01.17.
 */
@Service
public class HtmlGenerator {
    private static final String HEADLINE_TEMPLATE = "<h3>%s</h3>";
    private static final String DIV_START = "<div>";
    private static final String DIV_END   = "</div>";

    public String generateHtml(List<NewsStream> allNews) {
        StringBuilder builder = new StringBuilder();

        allNews.forEach((currNews) -> {
            if(currNews.getHeadline() != null && !currNews.getHeadline().isEmpty()) {
                builder.append(String.format(HEADLINE_TEMPLATE, currNews.getHeadline()));
                builder.append(lineSeparator());
            }
            builder.append(DIV_START);
            builder.append(lineSeparator());
            currNews.getNews().forEach(news -> builder.append(news.getRawHtml()).append(lineSeparator()));
            builder.append(DIV_END);
            builder.append(lineSeparator());
        });

        return builder.toString();
    }
}
