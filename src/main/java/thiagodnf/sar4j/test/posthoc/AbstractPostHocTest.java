package thiagodnf.sar4j.test.posthoc;

import java.util.List;

import com.google.common.base.Preconditions;

import thiagodnf.sar4j.data.Observation;
import thiagodnf.sar4j.result.PostHocResult;
import thiagodnf.sar4j.test.AbstractTest;
import thiagodnf.sar4j.util.ConvertUtils;
import thiagodnf.sar4j.util.FileUtils;
import thiagodnf.sar4j.util.ObservationUtils;
import thiagodnf.sar4j.util.RUtils;

public abstract class AbstractPostHocTest extends AbstractTest {

    public static final String COLUMNS = "columns";

    public static final String ROWS = "rows";

    public static final String VALUES = "values";

    public static final String DIMENSIONS = "dimensions";

    public PostHocResult test(List<Observation> observations) {

        Preconditions.checkNotNull(observations, "The observations must not be null");
        Preconditions.checkArgument(!observations.isEmpty(), "The observations must not be empty");

        String script = FileUtils.getFileContentFromResources(getTemplate());

        script = script.replaceAll("@VALUES@", ObservationUtils.getValues(observations));
        script = script.replaceAll("@GROUPS@", ObservationUtils.getGroups(observations));

        PostHocResult result = null;

        try {
            String content = RUtils.run(script.toString());

            result = ConvertUtils.toPostHocResult(content);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (showScript) {
            System.out.println(script);
        }

        return result;
    }
}
