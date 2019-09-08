package thiagodnf.sar4j.test.nonparametric;

import lombok.Getter;
import lombok.Setter;
import thiagodnf.sar4j.test.posthoc.WilcoxTest;

@Getter
@Setter
public class KruskalWallisTest extends AbstractNonparametric {

    public KruskalWallisTest() {
        super(new WilcoxTest());
    }

    @Override
    public String getTemplateFile() {
        return "templates/nonparametric/kruskal-wallis-test.r";
    }
}
