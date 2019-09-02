package thiagodnf.sar4j.result;

import lombok.Getter;
import lombok.Setter;
import thiagodnf.sar4j.util.ConvertUtils;

@Getter
@Setter
public class TestResult {

    private double chiSquared;

    private double df;

    private double pValue;

    private PostHocResult postHocResult;

    public String toString() {
        return ConvertUtils.toJson(this);
    }
}
