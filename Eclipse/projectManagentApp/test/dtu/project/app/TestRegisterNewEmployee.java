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
	public void testRegisterEmployee() {

		projectApp projectApp = new projectApp();
		
		List<Employee> employees = projectApp.getEmployees();
		assertEquals(0, employees.size());
		
		assertFalse(projectApp.employeeLoggedIn());
		
		boolean registered = projectApp.employeeRegister("abcd");

		assertTrue(registered);
		assertTrue(projectApp.employeeLoggedIn());
		
		employees = projectApp.getEmployees();
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

		projectApp projectApp = new projectApp();
		
		List<Employee> employees = projectApp.getEmployees();
		assertEquals(0, employees.size());
		
		// Check first that the user is not logged in as an employee.
		
		assertFalse(projectApp.employeeLoggedIn());
		
		// Step 1) Try to register with different initials
		
		boolean registered_try1 = projectApp.employeeRegister("");		 //Should not pass
		boolean registered_try2 = projectApp.employeeRegister(" "); 	 //Should not pass
		boolean registered_try3 = projectApp.employeeRegister("a.b."); 	 //Should not pass
		boolean registered_try4 = projectApp.employeeRegister("a  b"); 	 //Should not pass
		boolean registered_try5 = projectApp.employeeRegister("a12b"); 	 //Should not pass
		boolean registered_try6 = projectApp.employeeRegister("12"); 	 //Should not pass
		boolean registered_try7 = projectApp.employeeRegister("1234"); 	 //Should not pass
		boolean registered_try8 = projectApp.employeeRegister("abcde");  //Should not pass
		
		// Step 2) Check that the method returns false or true depending on the rules.
		assertFalse(registered_try1||registered_try2
					||registered_try3||registered_try4
					||registered_try5||registered_try6
					||registered_try7||registered_try8);
		
		assertFalse(projectApp.employeeLoggedIn());
		
		employees = projectApp.getEmployees();
		assertEquals(0, employees.size());
	}

}
