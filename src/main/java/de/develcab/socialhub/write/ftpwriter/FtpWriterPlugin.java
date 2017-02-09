package de.develcab.socialhub.write.ftpwriter;

import de.develcab.socialhub.WritePlugin;
import de.develcab.socialhub.model.NewsStream;
import de.develcab.socialhub.write.common.HtmlGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This WritePlugin writes a HTML output file with your news to an FTP server.
 * ATM it assumes the FTP server speaks FTP-SSL with explicit AUTH TLS. At least Cyberduck calls
 * it like this.
 *
 * Created by jb on 29.01.17.
 */
@Service
public class FtpWriterPlugin implements WritePlugin {


    @Autowired
    private HtmlGenerator htmlGenerator;

    @Autowired(required = false)
    private FtpConfiguration.FtpGateway ftpGateway;

    @Override
    public void write(List<NewsStream> news) {
        if (ftpGateway == null) { // not configured
            return;
        }

        String html = htmlGenerator.generateHtml(news);
        ftpGateway.sendHtmlOutput(html);
    }
}
