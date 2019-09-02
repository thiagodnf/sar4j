package thiagodnf.sar4j.output;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

import lombok.Getter;
import lombok.Setter;
import thiagodnf.sar4j.result.PostHocResult;
import thiagodnf.sar4j.result.TestResult;
import thiagodnf.sar4j.util.ConvertUtils;

@Getter
@Setter
public class TestForLatexOutput {
    
    private double alpha = 0.05;
    
    private int decimalPlaces = 5;
    
    private boolean showBoldLessThanAlpha = true;

    public String getOutput(TestResult testResult) {

        Preconditions.checkNotNull(testResult, "The testResult must not be null");

        PostHocResult postHocResult = testResult.getPostHocResult();
        
        if (postHocResult == null) {
            return "there is not significant difference";
        }
        
        StringBuilder builder = new StringBuilder();

        int rows = postHocResult.getDimensions().get(0);
        int columns = postHocResult.getDimensions().get(1);

        builder.append("\\begin{tabular}{|l|");
        builder.append(Strings.repeat("l", columns));
        builder.append("|}").append("\n");
        builder.append("\\hline").append("\n");

        for (String column : postHocResult.getColumns()) {
            builder.append(" & ").append(column);
        }

        builder.append("\\\\").append("\n");
        builder.append("\\hline").append("\n");

        List<List<Double>> split = split(postHocResult.getValues(), columns);
        
        for (int i = 0; i < rows; i++) {

            builder.append(postHocResult.getRows().get(i));
            
            for (int j = 0; j < columns; j++) {

                List<Double> column = split.get(j);

                double value = column.get(i);

                builder.append(" & ");

                if (Double.isNaN(value)) {
                    builder.append("-");
                } else {

                    if (value <= alpha && showBoldLessThanAlpha) {
                        builder.append("\\textbf{");
                        builder.append(ConvertUtils.toString(value, decimalPlaces));
                        builder.append("}");
                    } else {
                        builder.append(ConvertUtils.toString(value, decimalPlaces));
                    }
                }
            }

            builder.append(" \\\\").append("\n");
        }

        builder.append("\\hline").append("\n");
        builder.append("\\end{tabular}");
        
        return builder.toString();
    }
    
    public List<List<Double>> split(List<Double> values, int split) {

        List<List<Double>> result = new ArrayList<>();

        int index = 0;

        while (index < values.size()) {

            List<Double> v = new ArrayList<>();

            while (v.size() != split) {
                v.add(values.get(index++));
            }

            result.add(v);
        }

        return result;
    }
}
