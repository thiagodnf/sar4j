package thiagodnf.sar4j;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import thiagodnf.sar4j.data.Observation;
import thiagodnf.sar4j.output.TestForLatexOutput;
import thiagodnf.sar4j.result.TestResult;
import thiagodnf.sar4j.test.nonparametric.KruskalWallisTest;

public class Explorer {

    public static void main(String[] args) {

        List<Observation> observations = new ArrayList<>();

//        observations.add(new Observation("Data 1", Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0)));
//        observations.add(new Observation("Data 2", Arrays.asList(6.0, 7.0, 8.0, 9.0, 10.0)));
//        observations.add(new Observation("Data 3", Arrays.asList(6.0, 7.0, 8.0, 9.0, 10.0)));

        
        observations.add(new Observation("A", Arrays.asList(0.28551035, 0.338524035, 0.088313218, 0.205930807, 0.363240102)));
        observations.add(new Observation("B", Arrays.asList(0.52173913, 0.763358779, 0.32546786, 0.425305688, 0.378071834)));
        observations.add(new Observation("C", Arrays.asList(0.989119683, 1.192718142, 0.788288288, 0.549176236, 0.544588155)));
        observations.add(new Observation("D", Arrays.asList(1.26705653, 1.625320787, 1.266108976, 1.154187629, 1.268498943, 1.069518717)));
        
        KruskalWallisTest kruskal = new KruskalWallisTest();

        TestResult testResult = kruskal.test(observations);
        
        
        TestForLatexOutput output = new TestForLatexOutput();
        
        System.out.println(output.getOutput(testResult));

        
        
        
//    System.out.println(FileUtils.getFileContentFromResources("templates/kruskal.r"));
    }
}
