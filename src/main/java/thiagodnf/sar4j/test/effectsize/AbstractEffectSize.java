package thiagodnf.sar4j.test.effectsize;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.base.Preconditions;

import thiagodnf.sar4j.data.Observation;
import thiagodnf.sar4j.result.ResultEffectSize;
import thiagodnf.sar4j.test.AbstractTest;
import thiagodnf.sar4j.util.ConvertUtils;
import thiagodnf.sar4j.util.FileUtils;
import thiagodnf.sar4j.util.ObservationUtils;
import thiagodnf.sar4j.util.RUtils;

public abstract class AbstractEffectSize extends AbstractTest {

    public static final String ESTIMATE = "estimate";
    
    public static final String MAGNITUDE = "magnitude";

    public static final String LEVEL = "level";

    public List<ResultEffectSize> test(List<Observation> observations) {

        Preconditions.checkNotNull(observations, "The observations must not be null");
        Preconditions.checkArgument(!observations.isEmpty(), "The observations must not be empty");

        String script = FileUtils.getFileContentFromResources(getTemplateFile());
        
        List<ResultEffectSize> results = new ArrayList<>();
        
        for (Observation ob1 : observations) {

            for (Observation ob2 : observations) {

                if (!ob1.getName().equalsIgnoreCase(ob2.getName())) {

                    String copiedScript = new String(script); 

                    copiedScript = copiedScript.replaceAll("@VALUES@", ObservationUtils.getValues(Arrays.asList(ob1, ob2)));
                    copiedScript = copiedScript.replaceAll("@GROUPS@", ObservationUtils.getGroups(Arrays.asList(ob1, ob2)));

                    ResultEffectSize result = null;

                    try {
                        String content = RUtils.run(copiedScript.toString());

                        result = ConvertUtils.toEffectSizeResult(ob1.getName(), ob2.getName(), content);
                        
                        results.add(result);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return results;
    }
}
