package thiagodnf.sar4j.test.nonparametric;

import lombok.Getter;
import lombok.Setter;
import thiagodnf.sar4j.test.posthoc.NemenyiTest;

@Getter
@Setter
public class KruskalWallisTest extends AbstractNonparametric {

    public KruskalWallisTest() {
        super(new NemenyiTest());
    }

    @Override
    public String getTemplate() {
        return "templates/kruskal-wallis-test.r";
    }
}
