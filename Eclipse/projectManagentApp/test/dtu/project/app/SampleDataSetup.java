package dtu.project.app;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

/**
 * A class the defines a common setUp (e.g. a method with the @Before Annotation) containing
 * sample data.<p>
 * Test classes that want to use that data should inherit from this class.
 * @author hub
 *
 */
public class SampleDataSetup {
	
	ProjectApp projectApp = new ProjectApp();
	/**
	 * Create the sample data for the search test cases.
	 * This method is executed each time one of the test methods is run.
	 * In contrast to old JUnit versions, it is now the @Before annotations that
	 * marks this method to be run before each test case and not the name setUp().
	 * 
	 * This method is inherited by subclasses. Thus any tests that needs sample data,
	 * should inherit from this class and add sample data as necessary.
	 */
	@Before
	public void setUp() throws Exception {
		projectApp.registerNewEmployee("abcd");	
		projectApp.logout();
		projectApp.registerNewEmployee("efgh");
		projectApp.logout();
		projectApp.registerNewEmployee("ijkl");
		projectApp.logout();
		projectApp.registerNewEmployee("mnop");
		projectApp.logout();
		projectApp.registerNewEmployee("qrst");
		projectApp.logout();
		projectApp.registerNewEmployee("uvwx");
		projectApp.logout();
		projectApp.registerNewEmployee("yzae");
		projectApp.logout();
		projectApp.registerNewEmployee("ooaa");
		projectApp.logout();
	}


}
