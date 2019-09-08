package thiagodnf.sar4j.output;

import java.util.Map;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

import lombok.Getter;
import lombok.Setter;
import thiagodnf.sar4j.result.ResultTest;
import thiagodnf.sar4j.util.ConvertUtils;
import thiagodnf.sar4j.util.ResultTestUtils;

@Getter
@Setter
public class ResultTestForLatexOutput {

    private double alpha = 0.05;

    private int decimalPlaces = 5;

    private boolean showBoldLessThanAlpha = true;
    
    private boolean showTableEnvironment = true;

    public String getOutput(ResultTest resultTest) {

        Preconditions.checkNotNull(resultTest, "The resultTest must not be null");

        if (!resultTest.isPostHocAvailable()) {
            return "There is no significant difference";
        }

        StringBuilder builder = new StringBuilder();

        builder.append("\\begin{tabular}{|l|");
        builder.append(Strings.repeat("l", resultTest.getGroupNames().size()));
        builder.append("|}").append("\n");
        builder.append("\\hline").append("\n");

        // Define the header

        for (String groupName : resultTest.getGroupNames()) {
            builder.append(" & ").append(groupName);
        }

        builder.append(" \\\\").append("\n");
        builder.append("\\hline").append("\n");

        Map<String, String> parsed = ResultTestUtils.parsePostHocValue(resultTest);

        for (String source : resultTest.getGroupNames()) {

            builder.append(source);

            for (String target : resultTest.getGroupNames()) {

                String valueAsString = "-";

                if (!source.equalsIgnoreCase(target)) {

                    valueAsString = ResultTestUtils.getPostHocValue(parsed, source, target);

                    if (valueAsString.equalsIgnoreCase("NA") || valueAsString.equalsIgnoreCase("NAN")) {
                        valueAsString = "-";
                    } else {

                        double value = ConvertUtils.toDouble(valueAsString);

                        valueAsString = ConvertUtils.toString(value, decimalPlaces);

                        if (value <= alpha && showBoldLessThanAlpha) {
                            valueAsString = "\\textbf{" + valueAsString + "}";
                        }
                    }
                }

                builder.append(" & ").append(valueAsString);
            }

            builder.append(" \\\\").append("\n");
        }

        builder.append("\\hline").append("\n");
        builder.append("\\end{tabular}");
        
        if (showTableEnvironment) {
            return "\\begin{table}[h!]\n \\centering\n \\small \n \\caption{} \n \\label{} \n" + builder.toString() + "\n \\end{table}";
        }

        return builder.toString();
    }
}
