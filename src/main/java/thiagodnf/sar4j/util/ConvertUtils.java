package thiagodnf.sar4j.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import thiagodnf.sar4j.result.PostHocResult;
import thiagodnf.sar4j.result.TestResult;
import thiagodnf.sar4j.test.nonparametric.AbstractNonparametric;
import thiagodnf.sar4j.test.posthoc.AbstractPostHocTest;

public class ConvertUtils {

    public static double toDouble(String value) {

        Preconditions.checkNotNull(value, "The value must not be null");
        Preconditions.checkArgument(!value.isEmpty(), "The value must not be empty");

        if (value.equalsIgnoreCase("NA")) {
            return Double.NaN;
        }

        return Double.valueOf(value);
    }

    public static int toInteger(String value) {

        Preconditions.checkNotNull(value, "The value must not be null");
        Preconditions.checkArgument(!value.isEmpty(), "The value must not be empty");

        if (value.equalsIgnoreCase("NA")) {
            return 0;
        }

        return Integer.valueOf(value);
    }

    public static List<Double> toDoubleList(List<String> values) {

        Preconditions.checkNotNull(values, "The values must not be null");

        return values.stream().map(e -> ConvertUtils.toDouble(e)).collect(Collectors.toList());
    }

    public static List<Double> toDoubleList(String[] values) {
        return toDoubleList(Arrays.asList(values));
    }

    public static List<Integer> toIntegerList(List<String> values) {

        Preconditions.checkNotNull(values, "The values must not be null");

        return values.stream().map(e -> ConvertUtils.toInteger(e)).collect(Collectors.toList());
    }

    public static List<Integer> toIntegerList(String[] values) {
        return toIntegerList(Arrays.asList(values));
    }
    
    public static String toString(double value, int decimalPlaces) {
        
        Preconditions.checkArgument(decimalPlaces >= 1, "The decimalPlaces must be >= 1");
        
        NumberFormat formatter = new DecimalFormat("#0."+Strings.repeat("0", decimalPlaces));     
        
        return formatter.format(value);
    }

    public static List<String> toStringList(List<Double> values) {

        Preconditions.checkNotNull(values, "The values must not be null");

        return values.stream().map(v -> String.valueOf(v)).collect(Collectors.toList());
    }

    public static List<String> trim(List<String> values) {

        Preconditions.checkNotNull(values, "The values must not be null");

        return values.stream().map(e -> e.trim()).collect(Collectors.toList());
    }

    public static List<String> trim(String[] values) {
        return trim(Arrays.asList(values));
    }

    public static String toJson(Object object) {

        Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues().create();

        return gson.toJson(object);
    }
    
    public static PostHocResult toPostHocResult(String content) {

        Preconditions.checkNotNull(content, "The content must not be null");
        Preconditions.checkArgument(!content.isEmpty(), "The content must not be empty");

        PostHocResult postHocResult = new PostHocResult();

        String[] lines = content.split("\n");

        for (String line : lines) {

            String[] parts = line.split(":");

            String key = parts[0];
            String[] value = parts[1].trim().split(";");

            if (key.equalsIgnoreCase(AbstractPostHocTest.VALUES)) {
                postHocResult.setValues(ConvertUtils.toDoubleList(value));
            } else if (key.equalsIgnoreCase(AbstractPostHocTest.DIMENSIONS)) {
                postHocResult.setDimensions(ConvertUtils.toIntegerList(value));
            } else if (key.equalsIgnoreCase(AbstractPostHocTest.ROWS)) {
                postHocResult.setRows(ConvertUtils.trim(value));
            } else if (key.equalsIgnoreCase(AbstractPostHocTest.COLUMNS)) {
                postHocResult.setColumns(ConvertUtils.trim(value));
            }
        }

        return postHocResult;
    }

    public static TestResult toStatResult(String content) {

        Preconditions.checkNotNull(content, "The content must not be null");
        Preconditions.checkArgument(!content.isEmpty(), "The content must not be empty");

        TestResult statsResult = new TestResult();

        String[] lines = content.split("\n");

        for (String line : lines) {

            String[] parts = line.split(":");

            String key = parts[0];

            if (key.equalsIgnoreCase(AbstractNonparametric.CHI_SQUARED)) {
                statsResult.setChiSquared(ConvertUtils.toDouble(parts[1]));
            } else if (key.equalsIgnoreCase(AbstractNonparametric.P_VALUE)) {
                statsResult.setPValue(ConvertUtils.toDouble(parts[1]));
            } else if (key.equalsIgnoreCase(AbstractNonparametric.DF)) {
                statsResult.setDf(ConvertUtils.toDouble(parts[1]));
            }
        }

        return statsResult;
    }
}
