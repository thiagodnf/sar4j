package thiagodnf.sar4j.util;

import java.util.HashMap;
import java.util.Map;

import thiagodnf.sar4j.result.ResultTest;

public class ResultTestUtils {

    public static String getPostHocValue(Map<String, String> parsed, String source, String target) {

        if (parsed.containsKey(source + "_" + target)) {
            return parsed.get(source + "_" + target);
        }

        return parsed.get(target + "_" + source);
    }

    public static Map<String, String> parsePostHocValue(ResultTest resultTest) {

        Map<String, String> map = new HashMap<>();

        for (String source : resultTest.getGroupNames()) {

            for (String target : resultTest.getGroupNames()) {

                if (!source.equalsIgnoreCase(target)) {

                    int rows = resultTest.getPostHocDimensions().get(0);

                    int rowIndex = resultTest.getPostHocRows().indexOf(source);
                    int columnIndex = resultTest.getPostHocColumns().indexOf(target);

                    if (rowIndex == -1 || columnIndex == -1) {
                        rowIndex = resultTest.getPostHocRows().indexOf(target);
                        columnIndex = resultTest.getPostHocColumns().indexOf(source);
                    }

                    int index = columnIndex * rows + rowIndex;

                    String value = resultTest.getPostHocValues().get(index);

                    if (!value.equalsIgnoreCase("NA")) {
                        map.put(source + "_" + target, value);
                    }
                }
            }
        }

        return map;
    }
}
