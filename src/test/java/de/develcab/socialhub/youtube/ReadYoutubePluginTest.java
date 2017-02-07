package de.develcab.socialhub.youtube;

import de.develcab.socialhub.model.News;
import de.develcab.socialhub.youtube.ReadYoutubePlugin;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.EnvironmentTestUtils;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

/**
 * Created by jbruester on 13.10.16.
 */
public class ReadYoutubePluginTest {

    @Test
    public void testReadYoutubeVanilla() {
        ReadYoutubePlugin readPlugin = new ReadYoutubePlugin();
        NewsMapper mapper = new NewsMapper();
        RestTemplate restTemplate = new RestTemplate();

        ReflectionTestUtils.setField(readPlugin, "apiKey", System.getenv("youtube_apikey"));
        ReflectionTestUtils.setField(readPlugin, "playlistId", "UUpLVP1s0eE8sL5-246C4ZNg");
        ReflectionTestUtils.setField(readPlugin, "restTemplate", restTemplate);
        ReflectionTestUtils.setField(readPlugin, "mapper", mapper);

        List<News> ret = readPlugin.read();
        assertNotNull(ret);
        assertFalse(ret.isEmpty());
        assertNotNull(ret.get(0).getHeadline());
        assertFalse(ret.get(0).getHeadline().isEmpty());

        System.out.println(ret);
    }

    @Test
    public void testPluginNoConfig() {
        ReadYoutubePlugin readPlugin = new ReadYoutubePlugin();
        NewsMapper mapper = new NewsMapper();
        RestTemplate restTemplate = new RestTemplate();

        ReflectionTestUtils.setField(readPlugin, "restTemplate", restTemplate);
        ReflectionTestUtils.setField(readPlugin, "mapper", mapper);

        List<News> ret = readPlugin.read();
        assertNotNull(ret);
        assertTrue(ret.isEmpty());
    }
}
