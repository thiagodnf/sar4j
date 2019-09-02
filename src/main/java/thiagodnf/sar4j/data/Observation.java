package thiagodnf.sar4j.data;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Preconditions;

import lombok.Getter;
import lombok.Setter;
import thiagodnf.sar4j.util.ConvertUtils;

@Getter
@Setter
public class Observation {

    private String name;

    private List<Double> values;

    public Observation(String name, List<Double> values) {

        Preconditions.checkNotNull(name, "The name must not be null");
        Preconditions.checkNotNull(values, "The values must not be null");
        Preconditions.checkArgument(!name.isEmpty(), "The name must not be empty");

        this.name = name;
        this.values = values;
    }

    public Observation(String name) {
        this(name, new ArrayList<>());
    }

    public List<String> getValuesAsString() {
        return ConvertUtils.toStringList(values);
    }
}
