package thiagodnf.sar4j.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RUtils {

    public static String run(String script) throws IOException, InterruptedException {

        File scriptFile = File.createTempFile("script-", ".r");

        try (FileWriter scriptWriter = new FileWriter(scriptFile)) {
            scriptWriter.append(script);
        }

        String command = "";

        if (OSUtils.isWindows()) {
            command = "R.exe";
        } else if (OSUtils.isUnix()) {
            command = "R";
        } else if (OSUtils.isMac()) {
            command = "/Library/Frameworks/R.framework/Resources/R";
        }

        String result = ProcessUtils.run(command, "--slave", "-f", scriptFile.getAbsolutePath());

        scriptFile.delete();

        if (result == null || result.isEmpty()) {
            throw new RuntimeException("The script could be wrong: \n" + script);
        }

        return result;
    }
}
