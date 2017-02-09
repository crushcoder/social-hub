package de.develcab.socialhub.writer.ftpwriter;

import de.develcab.socialhub.Application;
import de.develcab.socialhub.model.NewsStream;
import de.develcab.socialhub.write.common.HtmlGenerator;
import de.develcab.socialhub.write.ftpwriter.FtpConfiguration;
import de.develcab.socialhub.write.ftpwriter.FtpWriterPlugin;
import de.develcab.socialhub.writer.common.AbstractWriterTest;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by jb on 30.01.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FtpWriterPluginTest extends AbstractWriterTest {

    @Value("${ftp.writer.test.httpurl}")
    private String testUrl;

    @Autowired
    private FtpWriterPlugin ftpWriterPlugin;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HtmlGenerator htmlGenerator;

    @Test
    public void test1WriteFtpVanilla() {
        List<NewsStream> news = this.createNews();
        ftpWriterPlugin.write(news);

        // load file via http
        String remoteFile = restTemplate.getForObject(testUrl, String.class);
        Assert.assertNotNull(remoteFile);
        Assert.assertFalse(remoteFile.isEmpty());
        Assert.assertEquals("files are not equal", htmlGenerator.generateHtml(news), remoteFile);
    }

    @Test
    public void test2NoConfig() {
        FtpConfiguration.FtpGateway ftpGateway = (FtpConfiguration.FtpGateway) ReflectionTestUtils.getField(ftpWriterPlugin, "ftpGateway");
        ReflectionTestUtils.setField(ftpWriterPlugin, "ftpGateway", null);

        List<NewsStream> news = this.createNews();
        ftpWriterPlugin.write(news);
        ReflectionTestUtils.setField(ftpWriterPlugin, "ftpGateway", ftpGateway);

        // load file via http
        String remoteFile = restTemplate.getForObject(testUrl, String.class);
        Assert.assertNotEquals("files are equal", htmlGenerator.generateHtml(news), remoteFile);


    }
}
