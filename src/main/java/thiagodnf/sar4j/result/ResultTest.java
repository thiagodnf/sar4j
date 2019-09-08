package thiagodnf.sar4j.result;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;
import thiagodnf.sar4j.util.ConvertUtils;

@Getter
@Setter
public class ResultTest {

    @SerializedName("chi-squared")
    private String chiSquared;

    private int df;

    @SerializedName("p-value")
    private String pValue;
    
    @SerializedName("group-names")
    private List<String> groupNames;

    @SerializedName("post-hoc-available")
    private boolean postHocAvailable;

    @SerializedName("post-hoc-dimensions")
    private List<Integer> postHocDimensions;

    @SerializedName("post-hoc-rows")
    private List<String> postHocRows;

    @SerializedName("post-hoc-columns")
    private List<String> postHocColumns;

    @SerializedName("post-hoc-values")
    private List<String> postHocValues;

    public String toString() {
        return ConvertUtils.toJson(this);
    }
}
