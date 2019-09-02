package thiagodnf.sar4j.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;

public class FileUtils {

    public static String getFileContentFromResources(String filename) {

        Preconditions.checkNotNull(filename, "The filename must not be null");
        Preconditions.checkArgument(!filename.isEmpty(), "The filename must not be empty");

        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

        InputStream inputStream = classLoader.getResourceAsStream(filename);

        StringWriter writer = new StringWriter();

        try {
            IOUtils.copy(inputStream, writer, Charsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        return writer.toString();
    }
}
