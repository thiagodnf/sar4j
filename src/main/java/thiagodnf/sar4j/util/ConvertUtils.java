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

import thiagodnf.sar4j.result.ResultEffectSize;
import thiagodnf.sar4j.test.effectsize.AbstractEffectSize;

public class ConvertUtils {

    public static double toDouble(String value) {

        Preconditions.checkNotNull(value, "The value must not be null");
        Preconditions.checkArgument(!value.isEmpty(), "The value must not be empty");

        value = value.trim();
                
        if (value.equalsIgnoreCase("NA") || value.equalsIgnoreCase("NAN")) {
            return Double.NaN;
        }

        return Double.valueOf(value);
    }

    public static int toInteger(String value) {

        Preconditions.checkNotNull(value, "The value must not be null");
        Preconditions.checkArgument(!value.isEmpty(), "The value must not be empty");

        value = value.trim();
        
        if (value.equalsIgnoreCase("NA") || value.equalsIgnoreCase("NAN")) {
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
    
    public static String toString(String value, int decimalPlaces) {
        
        Preconditions.checkArgument(decimalPlaces >= -1, "The decimalPlaces must be >= 1");
        
        if (decimalPlaces == -1) {
            return value;
        }
        
        return toString(toDouble(value), decimalPlaces);
    }
    
    public static String toString(double value, int decimalPlaces) {

        Preconditions.checkArgument(decimalPlaces >= -1, "The decimalPlaces must be >= 1");

        if (decimalPlaces == -1) {
            return String.valueOf(value);
        }

        NumberFormat formatter = new DecimalFormat("#0." + Strings.repeat("0", decimalPlaces));

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

    public static <T> T fromJson(String content, Class<T> clsObject) {
        return new Gson().fromJson(content, clsObject);
    }
    
    public static String toJson(Object object) {

        Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues().create();

        return gson.toJson(object);
    }
    
    public static ResultEffectSize toEffectSizeResult(String source, String target, String content) {

        Preconditions.checkNotNull(content, "The content must not be null");
        Preconditions.checkArgument(!content.isEmpty(), "The content must not be empty");

        ResultEffectSize result = new ResultEffectSize();

        result.setSource(source);
        result.setTarget(target);
        
        String[] lines = content.split("\n");
        
        for (String line : lines) {

            String[] parts = line.split(":");

            String key = parts[0];

            if (key.equalsIgnoreCase(AbstractEffectSize.ESTIMATE)) {
                result.setEstimate(ConvertUtils.toDouble(parts[1]));
            } else if (key.equalsIgnoreCase(AbstractEffectSize.MAGNITUDE)) {
                result.setMagnitude(ConvertUtils.toInteger(parts[1]));
            } else if (key.equalsIgnoreCase(AbstractEffectSize.LEVEL)) {
                result.setLevel(parts[1].trim());
            }
        }

        return result;
    }
}
