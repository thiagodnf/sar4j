package thiagodnf.sar4j.test;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractTest {

    protected boolean showScript = false;
    
    protected abstract String getTemplate();
}
