package allTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import testsImage.AllTestsSuiteImage;
import testsInstance.AllTestsSuiteInstance;

@RunWith(Suite.class)
@Suite.SuiteClasses({AllTestsSuiteImage.class, AllTestsSuiteInstance.class})

public class AllTests {

}