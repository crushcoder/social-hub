package de.develcab.socialhub.writer.discwriter;

import de.develcab.socialhub.model.News;
import de.develcab.socialhub.model.NewsStream;
import de.develcab.socialhub.write.common.HtmlGenerator;
import de.develcab.socialhub.write.discwriter.LocalFileWriterPlugin;
import de.develcab.socialhub.writer.common.AbstractWriterTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jb on 26.01.17.
 */
public class LocalFileWriterPluginTest extends AbstractWriterTest {

    private LocalFileWriterPlugin plugin;
    private String filename = "./target/test_output.html";

    @Before
    public void init() throws IOException {
        plugin = new LocalFileWriterPlugin();
        ReflectionTestUtils.setField(plugin, "filename", filename);
        HtmlGenerator htmlGenerator = new HtmlGenerator();
        ReflectionTestUtils.setField(plugin, "htmlGenerator", htmlGenerator);
    }

    @After
    public void after() {
        // delete the output file
        getFile().delete();
    }

    @Test
    public void testWriteVanilla() throws IOException {
        plugin.write(createNews());
        File outputFile = getFile();
        Assert.assertTrue(outputFile.exists());
        List<String> lines = Files.readAllLines(outputFile.toPath());
        Assert.assertEquals("Not enough lines", 11, lines.size());
    }

    @Test
    public void testNoHeadline() throws IOException {
        List<NewsStream> allNews = createNews();
        allNews.get(1).setHeadline("");

        plugin.write(allNews);
        File outputFile = getFile();
        Assert.assertTrue(outputFile.exists());
        List<String> lines = Files.readAllLines(outputFile.toPath());
        Assert.assertEquals("Not enough lines", 10, lines.size());
    }

    @Test
    public void testNullHeadline() throws IOException {
        List<NewsStream> allNews = createNews();
        allNews.get(1).setHeadline(null);

        plugin.write(allNews);
        File outputFile = getFile();
        Assert.assertTrue(outputFile.exists());
        List<String> lines = Files.readAllLines(outputFile.toPath());
        Assert.assertEquals("Not enough lines", 10, lines.size());
    }

    @Test
    public void testNoConfigEmpty() {
        ReflectionTestUtils.setField(plugin, "filename", "");
        plugin.write(createNews());
        Assert.assertFalse(getFile().exists());
    }

    @Test
    public void testNoConfigNull() {
        ReflectionTestUtils.setField(plugin, "filename", null);
        plugin.write(createNews());
        Assert.assertFalse(getFile().exists());
    }

    @Test
    public void testEmptyNewslist() {
        plugin.write(new ArrayList<>());
        Assert.assertFalse(getFile().exists());
    }

    private File getFile() {
        return new File(filename);
    }


}
