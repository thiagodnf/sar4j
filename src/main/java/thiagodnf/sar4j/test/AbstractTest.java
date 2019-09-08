package thiagodnf.sar4j.test;

import com.google.common.base.Preconditions;

import lombok.Getter;
import lombok.Setter;
import thiagodnf.sar4j.util.FileUtils;

@Getter
@Setter
public abstract class AbstractTest {

    protected String template;

    protected boolean showScript = false;

    public AbstractTest() {

        String templateFile = getTemplateFile();

        Preconditions.checkNotNull(templateFile, "The templateFile must not be null");
        Preconditions.checkArgument(!templateFile.isEmpty(), "The templateFile must not be empty");

        template = FileUtils.getFileContentFromResources(templateFile);
    }

    protected abstract String getTemplateFile();
}
