package thiagodnf.sar4j.result;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import thiagodnf.sar4j.util.ConvertUtils;

@Getter
@Setter
public class PostHocResult {

    private List<String> rows;

    private List<String> columns;

    private List<Double> values;

    private List<Integer> dimensions;

    public String toString() {
        return ConvertUtils.toJson(this);
    }
}
