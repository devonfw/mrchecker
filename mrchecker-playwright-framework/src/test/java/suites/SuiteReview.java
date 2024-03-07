package suites;

import com.capgemini.framework.tags.Status;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@SelectPackages("com.capgemini.demoQA")
@IncludeTags(Status.REVIEW)
@Suite
public class SuiteReview {

}

