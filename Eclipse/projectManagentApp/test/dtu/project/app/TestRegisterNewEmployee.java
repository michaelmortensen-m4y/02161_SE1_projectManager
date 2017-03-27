package dtu.project.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

/**
 * This class contains tests, testing the functionality of adding books to the library.
 * @author group 9
 *
 */
public class TestRegisterNewEmployee {
	/** 
	 * Tests the scenario when an unregistered user successfully creates a new employee account.
	 * <ol>
	 *  <li> The user provides initials
	 * 	<li> The project application check if the initials comply to the rules
	 *  <li> The project application knows that the user is logged in as the new employee 
	 * </ol>
	 */
	@Test
	public void testLogin() {

		projectApp libApp = new projectApp();
		
		// Check first that the user is not logged in as an employee.
		
		assertFalse(libApp.employeeLoggedIn());
		
		// Step 1)
		
		boolean login = libApp.EmployeeLogin("abcd");
		
		// Step 2) Check that the method returned true and check that user is logged in as the new employee.
		assertTrue(login);
		assertTrue(libApp.employeeLoggedIn());
	}

}