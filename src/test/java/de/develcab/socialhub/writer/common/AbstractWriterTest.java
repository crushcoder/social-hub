package de.develcab.socialhub.writer.common;

import de.develcab.socialhub.model.News;
import de.develcab.socialhub.model.NewsStream;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jb on 30.01.17.
 */
public class AbstractWriterTest {
    protected List<NewsStream> createNews() {
        List<NewsStream> allNews = new ArrayList<>();
        List<News> streamOne = new ArrayList<>();
        News one = new News();
        one.setRawHtml("<div>first News. Time: " + LocalDateTime.now() + "</div>");
        News two = new News();
        two.setRawHtml("<div>second News</div>");
        streamOne.add(one);
        streamOne.add(two);
        NewsStream newsStreamOne = new NewsStream("Headline One", streamOne);
        allNews.add(newsStreamOne);

        List<News> streamTwo = new ArrayList<>();
        News three = new News();
        three.setRawHtml("<div>third News</div>");
        News four = new News();
        four.setRawHtml("<div>fourth News</div>");
        News five = new News();
        five.setRawHtml("<div>fifth News</div>");
        streamTwo.add(three);
        streamTwo.add(four);
        streamTwo.add(five);
        NewsStream newsStreamTwo = new NewsStream("Headline Two", streamTwo);
        allNews.add(newsStreamTwo);

        return allNews;
    }
}
