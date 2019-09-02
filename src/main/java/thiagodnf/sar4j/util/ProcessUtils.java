package thiagodnf.sar4j.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class ProcessUtils {

    public static String run(String... commands) throws IOException, InterruptedException {

        File outputFile = File.createTempFile("output-", ".r");

        ProcessBuilder processBuilder = new ProcessBuilder(commands);

        processBuilder.redirectOutput(outputFile);

        Process process = processBuilder.start();
        process.waitFor();

        List<String> lines = Files.readLines(outputFile, Charsets.UTF_8);

        outputFile.delete();

        return String.join("\n", lines);
    }
}
