package de.develcab.socialhub.wordpress;

import com.google.common.collect.Lists;
import de.develcab.socialhub.model.News;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * Created by jb on 27.01.17.
 */
public class ReadWordpressPluginTest {

    @Test
    public void testReadOneSite() {
        ReadWordpressPlugin plugin = new ReadWordpressPlugin();
        ReflectionTestUtils.setField(plugin, "siteNames", Lists.newArrayList("levinte.wordpress.com"));
        ReflectionTestUtils.setField(plugin, "restTemplate", new RestTemplate());
        List<News> ret = plugin.read();
        assertNotNull(ret);
        assertFalse(ret.isEmpty());
        assertFalse(ret.get(0).getHeadline().isEmpty());
        assertFalse(ret.get(0).getRawHtml().isEmpty());
        assertFalse(ret.get(0).getUrl().isEmpty());

        System.out.println(ret);
    }

    @Test
    public void testReadTwoSites() {

    }

    @Test
    public void testNoConfig() {

    }
}
