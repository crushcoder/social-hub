package de.develcab.socialhub.write.ftpwriter;

import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.file.remote.session.CachingSessionFactory;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.ftp.outbound.FtpMessageHandler;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;
import org.springframework.messaging.MessageHandler;

/**
 * Created by jb on 30.01.17.
 */
@Configuration
@IntegrationComponentScan
public class FtpConfiguration {
    private static final String FTP_CHANNEL = "toFtpChannel";

    @Value("${ftp.writer.server:}")
    private String host;

    @Value("${ftp.writer.username:}")
    private String username;

    @Value("${ftp.writer.password:}")
    private String password;

    @Value("${ftp.writer.port:21}")
    private int port;

    @Value("${ftp.writer.directory:''}")
    private String directory = "";

    @Value("${ftp.writer.filename:'news.html'}")
    private String filename = "news.html";

    @MessagingGateway
    public interface FtpGateway {

        @Gateway(requestChannel = FTP_CHANNEL)
        void sendHtmlOutput(String html);
    }

    @Bean
    public SessionFactory<FTPFile> ftpSessionFactory() {
        if (!isConfigured()) {
            return () -> null;
        }

        DefaultFtpSessionFactory sf = new DefaultFtpSessionFactory();
        sf.setHost(host);
        sf.setPort(port);
        sf.setUsername(username);
        sf.setPassword(password);
        return new CachingSessionFactory<FTPFile>(sf);
    }

    @Bean
    @ServiceActivator(inputChannel = FTP_CHANNEL)
    public MessageHandler handler() {
        if (!isConfigured()) {
            return message -> {
                // do nothing
            };
        }

        FtpMessageHandler handler = new FtpMessageHandler(ftpSessionFactory());
        handler.setRemoteDirectoryExpression(new LiteralExpression(directory));
        handler.setFileNameGenerator(message -> filename);
        return handler;
    }

    private boolean isConfigured() {
        return isConfigured(username) && isConfigured(password) && isConfigured(host);
    }

    private boolean isConfigured(String attribute) {
        return attribute != null && !attribute.isEmpty();
    }
}
