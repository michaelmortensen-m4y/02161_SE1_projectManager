package application;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * A class the defines a common setUp (e.g. a method with the @Before Annotation) containing
 * sample data 1.<p>
 * Test classes that want to use that data should inherit from this class.
 * @author group 9
 *
 */
public class SampleDataSetup1 {
	
	ProjectApp projectApp = new ProjectApp();

	@Before
	public void setUp() {
		
		// Register 8 employees in the system
		try {
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
		} catch (InitialsInvalidException e) {
			assertEquals("Input invalid. Initials does not comply with one or more of the following rules:\n1: Contains only letters (uppercase or lowercase).\n2: Minimum 1 letter.\n3: Maximum 4 letters.\n4: Initials must not already be registered.", e
					.getMessage());
			assertEquals("RegisterNewEmployee", e.getOperation());
			fail("An exception was thrown");
		}
		
	}


}
