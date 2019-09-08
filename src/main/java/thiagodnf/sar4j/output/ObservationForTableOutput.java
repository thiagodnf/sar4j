package thiagodnf.sar4j.output;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import com.google.common.base.Preconditions;

import lombok.Getter;
import lombok.Setter;
import thiagodnf.sar4j.data.Observation;
import thiagodnf.sar4j.util.ConvertUtils;
import thiagodnf.sar4j.util.ObservationUtils;

@Getter
@Setter
public class ObservationForTableOutput {
    
    private boolean bestIsLower = false;
    
    private boolean showStandardDeviation = true;
    
    private boolean showBoldBestValue = true;
    
    private int decimalPlaces = 5;

    public String getOutput(List<Observation> observations) {

        Preconditions.checkNotNull(observations, "The observations must not be null");
        Preconditions.checkArgument(!observations.isEmpty(), "The observations must not be empty");

        StringBuilder builder = new StringBuilder();
        
        String[][] matrix = ObservationUtils.getMatrix(observations);
        List<Integer> bestIndexes = ObservationUtils.getBestIndexes(observations, bestIsLower);
        
        for (int i = 0; i < matrix.length; i++) {

            StringBuilder b = new StringBuilder();
            
            String mean = ConvertUtils.toString(matrix[i][1], decimalPlaces);
            String sd = ConvertUtils.toString(matrix[i][2], decimalPlaces);

            String value = mean;

            if (showStandardDeviation) {
                value += " +- " + sd;
            }
            
            if(showBoldBestValue && bestIndexes.contains(i)) {
                value = "\\textbf{" + value + "}";
            }
            
            b.append(matrix[i][0]).append(" ").append(value);

            builder.append(b).append("\n");
        }

        return builder.toString();
    }
}
