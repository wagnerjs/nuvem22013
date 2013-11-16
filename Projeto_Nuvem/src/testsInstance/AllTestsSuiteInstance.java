package testsInstance;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ RebootInstances.class, MonitorInstances.class,
		UnmonitorInstances.class, RunInstances.class, StartInstances.class,
		StopInstances.class, DescriptionInstances.class })

public class AllTestsSuiteInstance {

}