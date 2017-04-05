package dtu.project.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;

/**
 * This class contains tests, testing the functionality of checking employees for availability.
 * @author group 9
 *
 */
public class TestCheckIfEmployeeIsAvailable extends SampleDataSetup2 {
	/** 
	 * Tests the scenario when an employee successfully checks that another employee is available.
	 * <ol>
	 *  <li> The employee is logged in
	 * 	<li> The employee checks another employee for availability
	 *  <li> The project application knows that the other employee is available and notifies the user
	 * </ol>
	 */
	@Test
	public void TestOneEmployeeForAvailability() {
		
		assertFalse(projectApp.employeeLoggedIn());
		
		projectApp.login("abcd");
		
		assertTrue(projectApp.employeeLoggedIn());
		
		// Create start and end week calendar objects
		Calendar startWeek = new GregorianCalendar();
		Calendar endWeek = new GregorianCalendar();
		
		// Set start and end week for the period the employee wishes to check for the other employees availability
		startWeek.set(startWeek.WEEK_OF_YEAR, 2);
		endWeek.set(endWeek.WEEK_OF_YEAR, 5);
		
		// Check if the employee with initials "efgh" is available from week 2 to 5:
		boolean EmployeeAvailable = projectApp.getEmployeeByInitials("efgh").isAvailable(startWeek, endWeek);
		
		assertTrue(EmployeeAvailable);
	}
	
	/** 
	 * Tests the scenario when an employee unsuccessfully checks that another employee is available.
	 * <ol>
	 *  <li> The employee is logged in
	 * 	<li> The employee checks another employee for availability
	 *  <li> The project application knows that the other employee is available and notifies the user
	 * </ol>
	 */
	@Test
	public void TestOneEmployeeForAvailabilityWhoIsNotAvailable() {
		
		assertFalse(projectApp.employeeLoggedIn());
		
		projectApp.login("abcd");
		
		assertTrue(projectApp.employeeLoggedIn());
		
		// Create start and end week calendar objects
		Calendar startWeek = new GregorianCalendar();
		Calendar endWeek = new GregorianCalendar();
		
		// Set start and end week for the period the employee wishes to check for the other employees availability
		startWeek.set(startWeek.WEEK_OF_YEAR, 6);
		endWeek.set(endWeek.WEEK_OF_YEAR, 10);
		
		// Check if the employee with initials "efgh" is available from week 6 to 10:
		boolean EmployeeAvailable = projectApp.getEmployeeByInitials("efgh").isAvailable(startWeek, endWeek);
		
		assertFalse(EmployeeAvailable);
	}

}
