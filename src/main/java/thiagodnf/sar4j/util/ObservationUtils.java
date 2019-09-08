package thiagodnf.sar4j.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import com.google.common.base.Preconditions;

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
    
    public static String[][] getMatrix(List<Observation> observations) {

        Preconditions.checkNotNull(observations, "The observations must not be null");
        Preconditions.checkArgument(!observations.isEmpty(), "The observations must not be empty");

        String[][] matrix = new String[observations.size()][3];

        for (int i = 0; i < observations.size(); i++) {

            DescriptiveStatistics desc = new DescriptiveStatistics();

            for (Double value : observations.get(i).getValues()) {
                desc.addValue(value);
            }

            matrix[i][0] = observations.get(i).getName();
            matrix[i][1] = String.valueOf(desc.getMean());
            matrix[i][2] = String.valueOf(desc.getStandardDeviation());
        }

        return matrix;
    }
    
    /**
     * 
     * @param means Means
     * @param sds Standard Deviations
     * @return
     */
    public static List<Integer> getBestIndexes(List<Observation> observations, boolean bestIsLower) {

        Preconditions.checkNotNull(observations, "The observations must not be null");
        Preconditions.checkArgument(!observations.isEmpty(), "The observations must not be empty");

        List<Double> means = new ArrayList<>();
        List<Double> sds = new ArrayList<>();

        for (Observation observation : observations) {

            DescriptiveStatistics desc = new DescriptiveStatistics();

            for (Double value : observation.getValues()) {
                desc.addValue(value);
            }

            means.add(desc.getMean());
            sds.add(desc.getStandardDeviation());
        }
        
        double bestMean = means.get(0);
        double bestSd = sds.get(0);

        for (int i = 1; i < means.size(); i++) {

            double mean = means.get(i);
            double sd = sds.get(i);

            if (bestIsLower) {
                if (mean < bestMean) {
                    bestMean = mean;
                    bestSd = sd;
                } else if (mean == bestMean && sd < bestSd) {
                    bestMean = mean;
                    bestSd = sd;
                }
            } else {
                if (mean > bestMean) {
                    bestMean = mean;
                    bestSd = sd;
                } else if (mean == bestMean && sd < bestSd) {
                    bestMean = mean;
                    bestSd = sd;
                }
            }
        }

        List<Integer> indexes = new ArrayList<>();

        for (int i = 0; i < means.size(); i++) {
            
            if (means.get(i) == bestMean && sds.get(i) == bestSd) {
                indexes.add(i);
            }
        }

        return indexes;
    }
}
