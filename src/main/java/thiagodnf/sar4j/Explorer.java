package thiagodnf.sar4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import thiagodnf.sar4j.data.Observation;
import thiagodnf.sar4j.output.TestForLatexOutput;
import thiagodnf.sar4j.result.TestResult;
import thiagodnf.sar4j.test.effectsize.VarghaDelaneyTest;
import thiagodnf.sar4j.test.nonparametric.KruskalWallisTest;

public class Explorer {

    public static void main(String[] args) {

        List<Observation> observations = new ArrayList<>();
        
        observations.add(new Observation("A", Arrays.asList(0.285, 0.338, 0.088, 0.205, 0.363)));
        observations.add(new Observation("B", Arrays.asList(0.521, 0.763, 0.325, 0.425, 0.378)));
        observations.add(new Observation("C", Arrays.asList(0.989, 1.192, 0.788, 0.549, 0.544)));
        
        KruskalWallisTest kruskal = new KruskalWallisTest();

//        TestResult testResult = kruskal.test(observations);
//        
//        System.out.println(testResult);
        
        VarghaDelaneyTest effectSizeTest = new VarghaDelaneyTest();
        
        System.out.println(effectSizeTest.test(observations));
        
//        TestForLatexOutput output = new TestForLatexOutput();
//        
//        System.out.println(output.getOutput(testResult));
    }
}
