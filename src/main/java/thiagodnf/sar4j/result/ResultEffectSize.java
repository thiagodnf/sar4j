package thiagodnf.sar4j.result;

import lombok.Getter;
import lombok.Setter;
import thiagodnf.sar4j.util.ConvertUtils;

@Setter
@Getter
public class ResultEffectSize {

    protected String source;
    
    protected String target;
    
    protected double estimate;

    protected int magnitude;

    protected String level;
    
    public String toString() {
        return ConvertUtils.toJson(this);
    }
}
