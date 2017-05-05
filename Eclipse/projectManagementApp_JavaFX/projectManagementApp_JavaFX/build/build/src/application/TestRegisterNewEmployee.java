package application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

/**
 * This class contains tests, testing the functionality of adding new employees to the system.
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
	public void testRegisterEmployee() {

		ProjectApp projectApp = new ProjectApp();
		
		List<Employee> employees = projectApp.getEmployees();
		assertEquals(0, employees.size());
		
		assertFalse(projectApp.employeeLoggedIn());
		
		try {
			projectApp.registerNewEmployee("abcd");
		} catch (InitialsInvalidException e) {
			assertEquals("Input invalid. Initials does not comply with one or more of the following rules:\n1: Contains only letters (uppercase or lowercase).\n2: Minimum 1 letter.\n3: Maximum 4 letters.\n4: Initials must not already be registered.", e
					.getMessage());
			assertEquals("RegisterNewEmployee", e.getOperation());
			fail("An exception was thrown");
		}

		assertTrue(projectApp.employeeLoggedIn());
		
		assertEquals(1, employees.size());
	}
	
	/** 
	 * Tests the scenario when an unregistered user tries to create a new employee account with wrong initials.
	 * <ol>
	 *  <li> The user provides initials that does not comply with the rules
	 * 	<li> The project application check if the initials comply to the rules
	 *  <li> The project application knows that the user entered wrong initials and notifies the user of the fact
	 * </ol>
	 */
	@Test
	public void testRegisterWithWrongInitials() {

		ProjectApp projectApp = new ProjectApp();
		
		List<Employee> employees = projectApp.getEmployees();
		assertEquals(0, employees.size());
		
		// Check first that the user is not logged in as an employee.
		
		assertFalse(projectApp.employeeLoggedIn());
		
		// Step 1) Try to register with different initials
		
		try {
			projectApp.registerNewEmployee("");		 //Should not pass
			fail("An exception should have been thrown");
		} catch (InitialsInvalidException e) {
			assertEquals("Input invalid. Initials does not comply with one or more of the following rules:\n1: Contains only letters (uppercase or lowercase).\n2: Minimum 1 letter.\n3: Maximum 4 letters.\n4: Initials must not already be registered.", e
					.getMessage());
			assertEquals("RegisterNewEmployee", e.getOperation());
		}
		
		try {
			projectApp.registerNewEmployee(" ");		 //Should not pass
			fail("An exception should have been thrown");
		} catch (InitialsInvalidException e) {
			assertEquals("Input invalid. Initials does not comply with one or more of the following rules:\n1: Contains only letters (uppercase or lowercase).\n2: Minimum 1 letter.\n3: Maximum 4 letters.\n4: Initials must not already be registered.", e
					.getMessage());
			assertEquals("RegisterNewEmployee", e.getOperation());
		}
		
		try {
			projectApp.registerNewEmployee("ab-c");		 //Should not pass
			fail("An exception should have been thrown");
		} catch (InitialsInvalidException e) {
			assertEquals("Input invalid. Initials does not comply with one or more of the following rules:\n1: Contains only letters (uppercase or lowercase).\n2: Minimum 1 letter.\n3: Maximum 4 letters.\n4: Initials must not already be registered.", e
					.getMessage());
			assertEquals("RegisterNewEmployee", e.getOperation());
		}
		
		try {
			projectApp.registerNewEmployee("ab+c");		 //Should not pass
			fail("An exception should have been thrown");
		} catch (InitialsInvalidException e) {
			assertEquals("Input invalid. Initials does not comply with one or more of the following rules:\n1: Contains only letters (uppercase or lowercase).\n2: Minimum 1 letter.\n3: Maximum 4 letters.\n4: Initials must not already be registered.", e
					.getMessage());
			assertEquals("RegisterNewEmployee", e.getOperation());
		}
		
		try {
			projectApp.registerNewEmployee("a.b.");		 //Should not pass
			fail("An exception should have been thrown");
		} catch (InitialsInvalidException e) {
			assertEquals("Input invalid. Initials does not comply with one or more of the following rules:\n1: Contains only letters (uppercase or lowercase).\n2: Minimum 1 letter.\n3: Maximum 4 letters.\n4: Initials must not already be registered.", e
					.getMessage());
			assertEquals("RegisterNewEmployee", e.getOperation());
		}
		
		try {
			projectApp.registerNewEmployee("a  b");		 //Should not pass
			fail("An exception should have been thrown");
		} catch (InitialsInvalidException e) {
			assertEquals("Input invalid. Initials does not comply with one or more of the following rules:\n1: Contains only letters (uppercase or lowercase).\n2: Minimum 1 letter.\n3: Maximum 4 letters.\n4: Initials must not already be registered.", e
					.getMessage());
			assertEquals("RegisterNewEmployee", e.getOperation());
		}
		
		try {
			projectApp.registerNewEmployee("a12b");		 //Should not pass
			fail("An exception should have been thrown");
		} catch (InitialsInvalidException e) {
			assertEquals("Input invalid. Initials does not comply with one or more of the following rules:\n1: Contains only letters (uppercase or lowercase).\n2: Minimum 1 letter.\n3: Maximum 4 letters.\n4: Initials must not already be registered.", e
					.getMessage());
			assertEquals("RegisterNewEmployee", e.getOperation());
		}
		
		try {
			projectApp.registerNewEmployee("1ab2");		 //Should not pass
			fail("An exception should have been thrown");
		} catch (InitialsInvalidException e) {
			assertEquals("Input invalid. Initials does not comply with one or more of the following rules:\n1: Contains only letters (uppercase or lowercase).\n2: Minimum 1 letter.\n3: Maximum 4 letters.\n4: Initials must not already be registered.", e
					.getMessage());
			assertEquals("RegisterNewEmployee", e.getOperation());
		}
		
		try {
			projectApp.registerNewEmployee("12");		 //Should not pass
			fail("An exception should have been thrown");
		} catch (InitialsInvalidException e) {
			assertEquals("Input invalid. Initials does not comply with one or more of the following rules:\n1: Contains only letters (uppercase or lowercase).\n2: Minimum 1 letter.\n3: Maximum 4 letters.\n4: Initials must not already be registered.", e
					.getMessage());
			assertEquals("RegisterNewEmployee", e.getOperation());
		}
		
		try {
			projectApp.registerNewEmployee("1234");		 //Should not pass
			fail("An exception should have been thrown");
		} catch (InitialsInvalidException e) {
			assertEquals("Input invalid. Initials does not comply with one or more of the following rules:\n1: Contains only letters (uppercase or lowercase).\n2: Minimum 1 letter.\n3: Maximum 4 letters.\n4: Initials must not already be registered.", e
					.getMessage());
			assertEquals("RegisterNewEmployee", e.getOperation());
		}
		
		try {
			projectApp.registerNewEmployee("abcde");     //Should not pass
			fail("An exception should have been thrown");
		} catch (InitialsInvalidException e) {
			assertEquals("Input invalid. Initials does not comply with one or more of the following rules:\n1: Contains only letters (uppercase or lowercase).\n2: Minimum 1 letter.\n3: Maximum 4 letters.\n4: Initials must not already be registered.", e
					.getMessage());
			assertEquals("RegisterNewEmployee", e.getOperation());
		}
		
		assertFalse(projectApp.employeeLoggedIn());
		
		assertEquals(0, employees.size());
	}

	/** 
	 * Tests registering a new employee while already logged in.
	 * <ol>
	 *  <li> The user registers a new employee
	 * 	<li> The user registers another employee
	 *  <li> The project application knows that the user can not register an employee while logged in as one
	 * </ol>
	 */
	@Test
	public void testRegisterMultipleEmployees() {

		ProjectApp projectApp = new ProjectApp();
		
		List<Employee> employees = projectApp.getEmployees();
		assertEquals(0, employees.size());
		
		// Check first that the user is not logged in as an employee.
		
		assertFalse(projectApp.employeeLoggedIn());
		
		// Step 1) Register an employee
		
		try {
			projectApp.registerNewEmployee("abcd");
		} catch (InitialsInvalidException e) {
			assertEquals("Input invalid. Initials does not comply with one or more of the following rules:\n1: Contains only letters (uppercase or lowercase).\n2: Minimum 1 letter.\n3: Maximum 4 letters.\n4: Initials must not already be registered.", e
					.getMessage());
			assertEquals("RegisterNewEmployee", e.getOperation());
			fail("An exception was thrown");
		}
		
		assertTrue(projectApp.employeeLoggedIn());
		
		assertEquals(1, employees.size());
		
		// Step 2) Try to register another employee
		
		try {
			projectApp.registerNewEmployee("efgh");
			fail("An exception should have been thrown");
		} catch (InitialsInvalidException e) {
			assertEquals("Input invalid. Initials does not comply with one or more of the following rules:\n1: Contains only letters (uppercase or lowercase).\n2: Minimum 1 letter.\n3: Maximum 4 letters.\n4: Initials must not already be registered.", e
					.getMessage());
			assertEquals("RegisterNewEmployee", e.getOperation());
		}
		
		assertTrue(projectApp.employeeLoggedIn());
		
		assertEquals(1, employees.size());
	}
	
	/** 
	 * Tests registering multiple employees successfully.
	 * <ol>
	 *  <li> The user registers a new employee
	 * 	<li> The user logs out
	 * 	<li> The user registers another employee
	 * </ol>
	 */
	@Test
	public void testRegisterMultipleEmployeesSuccesfully() {

		ProjectApp projectApp = new ProjectApp();
		
		List<Employee> employees = projectApp.getEmployees();
		assertEquals(0, employees.size());
		
		// Check first that the user is not logged in as an employee.
		
		assertFalse(projectApp.employeeLoggedIn());
		
		// Step 1) Register an employee
		
		try {
			projectApp.registerNewEmployee("abcd");
		} catch (InitialsInvalidException e) {
			assertEquals("Input invalid. Initials does not comply with one or more of the following rules:\n1: Contains only letters (uppercase or lowercase).\n2: Minimum 1 letter.\n3: Maximum 4 letters.\n4: Initials must not already be registered.", e
					.getMessage());
			assertEquals("RegisterNewEmployee", e.getOperation());
			fail("An exception was thrown");
		}
		
		assertTrue(projectApp.employeeLoggedIn());
		
		assertEquals(1, employees.size());
		
		// Step 2) Logout and register another employee
		
		projectApp.logout();
		
		try {
			projectApp.registerNewEmployee("efgh");
		} catch (InitialsInvalidException e) {
			assertEquals("Input invalid. Initials does not comply with one or more of the following rules:\n1: Contains only letters (uppercase or lowercase).\n2: Minimum 1 letter.\n3: Maximum 4 letters.\n4: Initials must not already be registered.", e
					.getMessage());
			assertEquals("RegisterNewEmployee", e.getOperation());
			fail("An exception was thrown");
		}
		
		assertTrue(projectApp.employeeLoggedIn());
		
		assertEquals(2, employees.size());
	}

}
