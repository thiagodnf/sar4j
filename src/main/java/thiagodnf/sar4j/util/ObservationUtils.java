package thiagodnf.sar4j.util;

import java.util.ArrayList;
import java.util.List;

import thiagodnf.sar4j.data.Observation;

public class ObservationUtils {
    
   
    public static String getGroups(List<Observation> observations) {

        StringBuilder builder = new StringBuilder();

        builder.append("as.factor(c(");
        
        for (int i = 0; i < observations.size(); i++) {

            List<String> groups = new ArrayList<>();

            for (int j = 0; j < observations.get(i).getValues().size(); j++) {
                groups.add("\"" + observations.get(i).getName() + "\"");
            }

            builder.append(String.join(",", groups));

            if (i + 1 != observations.size()) {
                builder.append(",");
            }
        }
        
        builder.append("))");
        
        return builder.toString();
    }

    public static String getValues(List<Observation> observations) {

        StringBuilder builder = new StringBuilder();

        builder.append("c(");

        for (int i = 0; i < observations.size(); i++) {

            builder.append(String.join(",", observations.get(i).getValuesAsString()));

            if (i + 1 != observations.size()) {
                builder.append(",");
            }
        }

        builder.append(")");

        return builder.toString();
    }
}
