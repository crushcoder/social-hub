package de.develcab.socialhub.flickr;

import de.develcab.socialhub.model.News;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by jb on 25.01.17.
 */
public class ReadFlickrPluginTest {

    @Test
    public void testReadFlickrVanilla() {
        ReadFlickrPlugin readPlugin = new ReadFlickrPlugin();

        PhotoMapper mapper = new PhotoMapper();
        RestTemplate restTemplate = new RestTemplate();


        ReflectionTestUtils.setField(readPlugin, "userId", "99480279@N08");
        ReflectionTestUtils.setField(mapper, "userId", "99480279@N08");
        ReflectionTestUtils.setField(readPlugin, "apiKey", System.getenv("flickr_apikey"));
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
        ReadFlickrPlugin readPlugin = new ReadFlickrPlugin();

        PhotoMapper mapper = new PhotoMapper();
        RestTemplate restTemplate = new RestTemplate();

        ReflectionTestUtils.setField(mapper, "userId", "99480279@N08");
        ReflectionTestUtils.setField(readPlugin, "restTemplate", restTemplate);
        ReflectionTestUtils.setField(readPlugin, "mapper", mapper);

        List<News> ret = readPlugin.read();
        assertNotNull(ret);
        assertTrue(ret.isEmpty());
    }
}
