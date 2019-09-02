package thiagodnf.sar4j.test.nonparametric;

import java.util.List;

import com.google.common.base.Preconditions;

import lombok.Getter;
import lombok.Setter;
import thiagodnf.sar4j.data.Observation;
import thiagodnf.sar4j.result.TestResult;
import thiagodnf.sar4j.test.AbstractTest;
import thiagodnf.sar4j.test.posthoc.AbstractPostHocTest;
import thiagodnf.sar4j.util.ConvertUtils;
import thiagodnf.sar4j.util.FileUtils;
import thiagodnf.sar4j.util.ObservationUtils;
import thiagodnf.sar4j.util.RUtils;

@Getter
@Setter
public abstract class AbstractNonparametric extends AbstractTest {

    public static final String P_VALUE = "p-value";

    public static final String CHI_SQUARED = "chi-squared";

    public static final String DF = "df";
    
    private double alpha = 0.05;

    private AbstractPostHocTest postHocTest;

    public AbstractNonparametric(AbstractPostHocTest postHocTest) {
        this.postHocTest = postHocTest;
    }

    public TestResult test(List<Observation> observations) {

        Preconditions.checkNotNull(observations, "The observations must not be null");
        Preconditions.checkArgument(!observations.isEmpty(), "The observations must not be empty");

        String script = FileUtils.getFileContentFromResources(getTemplate());

        script = script.replaceAll("@VALUES@", ObservationUtils.getValues(observations));
        script = script.replaceAll("@GROUPS@", ObservationUtils.getGroups(observations));

        TestResult result = null;

        try {
            String content = RUtils.run(script.toString());

            result = ConvertUtils.toStatResult(content);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result.getPValue() < alpha && postHocTest != null) {
            result.setPostHocResult(postHocTest.test(observations));
        }

        if (showScript) {
            System.out.println(script);
        }

        return result;
    }
}
