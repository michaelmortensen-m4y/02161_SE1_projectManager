package application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import java.util.List;

import org.junit.Test;

/**
 * This class contains tests, testing the functionality of searching for employees by their initials.
 * @author group 9
 *
 */
public class TestGetEmployeeByInitial extends SampleDataSetup1 {
	/** 
	 * Tests searching for employees by initials.
	 * <ol>
	 *  <li> The user provides initials
	 * 	<li> The project application check if an employee with the initials is registered in the system
	 * </ol>
	 */
	@Test
	public void testSearchEmployeeByInitial() {
		
		List<Employee> employeesResults = new ArrayList<Employee>();
		assertEquals(0, employeesResults.size());
		
		employeesResults.add(projectApp.getEmployeeByInitials("abcd"));
		assertEquals(1, employeesResults.size());
		
	}
	
	/** 
	 * Tests searching for employees by initials not in the system.
	 * <ol>
	 *  <li> The user provides initials
	 * 	<li> Check that an empty list is returned because of no match
	 * </ol>
	 */
	@Test
	public void testSearchEmployeeByInitialNoResults() {
		
		assertEquals(null, projectApp.getEmployeeByInitials("xxxx"));
		
	}
	
	/** 
	 * Tests registering multiple employees with the same initials.
	 * <ol>
	 * 	<li> The user tries to register a new employee with initials already used by another employee
	 *  <li> The application knows that there can not exist multiple employees with the same initials
	 * </ol>
	 */
	@Test
	public void testRegisterAnAlreadyExistingEmployee() {

		//ProjectApp projectApp = new ProjectApp();
		
		List<Employee> employees = projectApp.getEmployees();
		assertEquals(8, employees.size()); // Comes from SampleDataSetup1.java
		
		// Check first that the user is not logged in as an employee.
		
		assertFalse(projectApp.employeeLoggedIn());
		
		// Step 1) Register an employee with initials already used by another employee
		
		try {
			projectApp.registerNewEmployee("abcd");
			fail("An exception should have been thrown");
		} catch (InitialsInvalidException e) {
			assertEquals("Input invalid. Initials does not comply with one or more of the following rules:\n1: Contains only letters (uppercase or lowercase).\n2: Minimum 1 letter.\n3: Maximum 4 letters.\n4: Initials must not already be registered.", e
					.getMessage());
			assertEquals("RegisterNewEmployee", e.getOperation());
		}
		
		assertFalse(projectApp.employeeLoggedIn());
		
		assertEquals(8, employees.size());
	
	}

}
