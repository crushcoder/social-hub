package de.develcab.socialhub.write.discwriter;

import de.develcab.socialhub.WritePlugin;
import de.develcab.socialhub.model.NewsStream;
import de.develcab.socialhub.write.common.HtmlGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import static java.lang.System.lineSeparator;

/**
 * Created by jb on 25.01.17.
 */
@Service
public class LocalFileWriterPlugin implements WritePlugin {

    @Value("${local.filewriter.filename:}")
    private String filename;

    @Autowired
    private HtmlGenerator htmlGenerator;

    @Override
    public void write(List<NewsStream> allNews) {
        if(filename == null || filename.isEmpty() || allNews == null || allNews.isEmpty()) {
            return;
        }

        try (FileWriter fw = new FileWriter(filename)) {
            Writer writer = new BufferedWriter(fw);
            writer.write(htmlGenerator.generateHtml(allNews));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
