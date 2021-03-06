package application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
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
		
		try {
			projectApp.login("abcd");
		} catch (NoSuchUserException e) {
			assertEquals("No user with initials \"abcd\" exist.", e
					.getMessage());
			assertEquals("Login", e.getOperation());
			fail("An exception was thrown");
		}
		
		assertTrue(projectApp.employeeLoggedIn());
		
		// Create start and end week calendar objects
		Calendar startWeek = new GregorianCalendar();
		Calendar endWeek = new GregorianCalendar();
		
		// Set start and end week for the period the employee wishes to check for the other employees availability
		startWeek.set(startWeek.WEEK_OF_YEAR, 4);
		endWeek.set(endWeek.WEEK_OF_YEAR, 7);
		
		// Check if the employee with initials "efgh" is available from week 4 to 7:
		boolean EmployeeAvailable = projectApp.getEmployeeByInitials("efgh").isAvailable(startWeek, endWeek);
		
		assertTrue(EmployeeAvailable);
	}
	
	/** 
	 * Tests the scenario when an employee unsuccessfully checks if another employee is available.
	 * <ol>
	 *  <li> The employee is logged in
	 * 	<li> The employee checks another employee for availability
	 *  <li> The project application knows that the other employee is unavailable and notifies the user
	 * </ol>
	 */
	@Test
	public void TestOneEmployeeForAvailabilityWhoIsNotAvailable() {
		
		assertFalse(projectApp.employeeLoggedIn());
		
		try {
			projectApp.login("abcd");
		} catch (NoSuchUserException e) {
			assertEquals("No user with initials \"abcd\" exist.", e
					.getMessage());
			assertEquals("Login", e.getOperation());
			fail("An exception was thrown");
		}
		
		assertTrue(projectApp.employeeLoggedIn());
		
		// Create start and end week calendar objects
		Calendar startWeek = new GregorianCalendar();
		Calendar endWeek = new GregorianCalendar();
		
		// Set start and end week for the period the employee wishes to check for the other employees availability
		startWeek.set(startWeek.WEEK_OF_YEAR, 4);
		endWeek.set(endWeek.WEEK_OF_YEAR, 7);
		
		// From SampleDataSetup2.java we see that "efgh" has 1 active project in week 4 and 4 active projects in week 5 and 6 and 2 active projects in week 7. 
		// Check if the employee with initials "efgh" is available from week 4 to 7:
		boolean EmployeeAvailable = projectApp.getEmployeeByInitials("efgh").isAvailable(startWeek, endWeek);
		
		assertTrue(EmployeeAvailable);
		
		// "efgh"'s activityLimit is 5 (per default) so "abcd" adds "efgh" to 1 more activity in week 5 so that he will not be available for this week:
		Activity activity = projectApp.getProjectById(5).getActivityByName("activity 1 in project 5");
		Employee employee = projectApp.getEmployeeByInitials("efgh");
		employee.addToActivity(activity);
		
		// Create start and end week calendar objects
		startWeek = new GregorianCalendar();
		endWeek = new GregorianCalendar();
		
		// Set start and end week for the period the employee wishes to check for the other employees availability
		startWeek.set(startWeek.WEEK_OF_YEAR, 4);
		endWeek.set(endWeek.WEEK_OF_YEAR, 7);
		
		// Check if the employee with initials "efgh" is available from week 4 to 7:
		EmployeeAvailable = projectApp.getEmployeeByInitials("efgh").isAvailable(startWeek, endWeek);
		
		assertFalse(EmployeeAvailable);
	}
	
	/** 
	 * Tests the scenario when an employee tries to add another employee to an activity even though that the other employee is not available.
	 * <ol>
	 *  <li> The employee is logged in
	 * 	<li> The employee tries to ass another employee to an activity
	 *  <li> The project application knows that the other employee is unavailable and notifies the user
	 * </ol>
	 */
	@Test
	public void TestAddEmployeeWhoIsNotAvailable() {
		
		assertFalse(projectApp.employeeLoggedIn());
		
		try {
			projectApp.login("abcd");
		} catch (NoSuchUserException e) {
			assertEquals("No user with initials \"abcd\" exist.", e
					.getMessage());
			assertEquals("Login", e.getOperation());
			fail("An exception was thrown");
		}
		
		assertTrue(projectApp.employeeLoggedIn());
		
		// Create start and end week calendar objects
		Calendar startWeek = new GregorianCalendar();
		Calendar endWeek = new GregorianCalendar();
		
		// Set start and end week for the period the employee wishes to check for the other employees availability
		startWeek.set(startWeek.WEEK_OF_YEAR, 4);
		endWeek.set(endWeek.WEEK_OF_YEAR, 7);
		
		// From SampleDataSetup2.java we see that "efgh" has 1 active project in week 4 and 4 active projects in week 5 and 6 and 2 active projects in week 7. 
		// Check if the employee with initials "efgh" is available from week 4 to 7:
		boolean EmployeeAvailable = projectApp.getEmployeeByInitials("efgh").isAvailable(startWeek, endWeek);
		
		assertTrue(EmployeeAvailable);
		
		// "efgh"'s activityLimit is 5 (per default) so "abcd" adds "efgh" to 1 more activity in week 5 so that he will not be available for this week:
		Activity activity = projectApp.getProjectById(5).getActivityByName("activity 1 in project 5");
		Employee employee = projectApp.getEmployeeByInitials("efgh");
		employee.addToActivity(activity);
		
		int numberOfActivities = employee.getActivities().size();

		assertEquals(employee.getActivities().size(), numberOfActivities);
		
		// "efgh"'s activityLimit is 5 (per default) so "abcd" tries to add "efgh" to 1 more activity in week 5 which should not be possible:
		activity = projectApp.getProjectById(5).getActivityByName("activity 2 in project 5");
		employee = projectApp.getEmployeeByInitials("efgh");
		employee.addToActivity(activity);

		// Check that the number of joined activities is the same as before
		assertEquals(numberOfActivities, employee.getActivities().size());
		
	}

	/** 
	 * Tests the scenario when an employee removes an activity from another employee to make him available for another activity.
	 * <ol>
	 *  <li> The employee is logged in
	 * 	<li> The employee tries to add another employee to an activity
	 *  <li> The project application knows that the other employee is unavailable and notifies the user
	 *  <li> The employee removes an activity from the other employee to make him available
	 *  <li> The employee adds the other employee to another activity
	 *  <li> The project application knows that the other employee is available and will therefore be added
	 * </ol>
	 */
	@Test
	public void TestAddEmployeeWhoIsNotAvailableButThenHasAnActivityRemoved() {
		
		assertFalse(projectApp.employeeLoggedIn());
		
		try {
			projectApp.login("abcd");
		} catch (NoSuchUserException e) {
			assertEquals("No user with initials \"abcd\" exist.", e
					.getMessage());
			assertEquals("Login", e.getOperation());
			fail("An exception was thrown");
		}
		
		assertTrue(projectApp.employeeLoggedIn());
		
		// Create start and end week calendar objects
		Calendar startWeek = new GregorianCalendar();
		Calendar endWeek = new GregorianCalendar();
		
		// Set start and end week for the period the employee wishes to check for the other employees availability
		startWeek.set(startWeek.WEEK_OF_YEAR, 4);
		endWeek.set(endWeek.WEEK_OF_YEAR, 7);
		
		// From SampleDataSetup2.java we see that "efgh" has 1 active project in week 4 and 4 active projects in week 5 and 6 and 2 active projects in week 7. 
		// Check if the employee with initials "efgh" is available from week 4 to 7:
		boolean EmployeeAvailable = projectApp.getEmployeeByInitials("efgh").isAvailable(startWeek, endWeek);
		
		assertTrue(EmployeeAvailable);
		
		// "efgh"'s activityLimit is 5 (per default) so "abcd" adds "efgh" to 1 more activity in week 5 so that he will not be available for this week:
		Activity activity1 = projectApp.getProjectById(5).getActivityByName("activity 1 in project 5");
		Employee employee = projectApp.getEmployeeByInitials("efgh");
		employee.addToActivity(activity1);
		
		int numberOfActivities = employee.getActivities().size();

		assertEquals(employee.getActivities().size(), numberOfActivities);
		
		// "efgh"'s activityLimit is 5 (per default) so "abcd" tries to add "efgh" to 1 more activity in week 5 which should not be possible:
		Activity activity2 = projectApp.getProjectById(5).getActivityByName("activity 2 in project 5");
		employee = projectApp.getEmployeeByInitials("efgh");
		employee.addToActivity(activity2);

		// Check that the number of joined activities is the same as before
		assertEquals(numberOfActivities, employee.getActivities().size());
		
		// "abcd" removes "efgh" from activity1 in project 5
		employee.removeFromActivity(activity1);
		
		// Check that the number of joined activities is one less than before
		assertEquals(numberOfActivities-1, employee.getActivities().size());
		
		// Then he tries again:
		employee.addToActivity(activity2);

		// Check that the number of joined activities has increased by 1 again
		assertEquals(numberOfActivities, employee.getActivities().size());
		
	}
	
	/** 
	 * Tests the scenario when an employee removes an activity from another employee to make him available for another activity.
	 * <ol>
	 *  <li> The employee is logged in
	 * 	<li> The employee tries to add another employee to an activity
	 *  <li> The project application knows that the other employee is unavailable and notifies the user
	 *  <li> The employee removes an activity from the other employee to make him available
	 *  <li> The employee adds the other employee to another activity
	 *  <li> The project application knows that the other employee is available and will therefore be added
	 * </ol>
	 */
	@Test
	public void TestListAvailableEmployees() {
		
		assertFalse(projectApp.employeeLoggedIn());
		
		try {
			projectApp.login("abcd");
		} catch (NoSuchUserException e) {
			assertEquals("No user with initials \"abcd\" exist.", e
					.getMessage());
			assertEquals("Login", e.getOperation());
			fail("An exception was thrown");
		}
		
		assertTrue(projectApp.employeeLoggedIn());
		
		// Create start and end week calendar objects
		Calendar startWeek = new GregorianCalendar();
		Calendar endWeek = new GregorianCalendar();
		
		// Set start and end week for the period the employee wishes to check for the other employees availability
		startWeek.set(startWeek.WEEK_OF_YEAR, 4);
		endWeek.set(endWeek.WEEK_OF_YEAR, 7);
		
		// List available employees:
		List<Employee> AvailableEmployees = new ArrayList<Employee>();
		AvailableEmployees = projectApp.listAvailableEmployees(startWeek, endWeek);
		
		assertEquals(8, AvailableEmployees.size());
		
		Employee employee = projectApp.getEmployeeByInitials("efgh");
		boolean EmployeeAvailable = employee.isAvailable(startWeek, endWeek);
		assertTrue(EmployeeAvailable);
		
		// "efgh"'s activityLimit is 5 (per default) so "abcd" adds "efgh" to 1 more activity in week 5 so that he will not be available for this week:
		Activity activity = projectApp.getProjectById(5).getActivityByName("activity 1 in project 5");
		employee.addToActivity(activity);
		
		// Create start and end week calendar objects
		startWeek = new GregorianCalendar();
		endWeek = new GregorianCalendar();
		
		// Set start and end week for the period the employee wishes to check for the other employees availability
		startWeek.set(startWeek.WEEK_OF_YEAR, 4);
		endWeek.set(endWeek.WEEK_OF_YEAR, 7);
		
		// Check if the employee with initials "efgh" is available from week 4 to 7:
		EmployeeAvailable = employee.isAvailable(startWeek, endWeek);
		
		assertFalse(EmployeeAvailable);
		
		// List available employees:
		AvailableEmployees = projectApp.listAvailableEmployees(startWeek, endWeek);
		assertEquals(7, AvailableEmployees.size());
	}
	
	/** 
	 * Tests the scenario when an employee checks if another employee is available when he is not because of personal activity.
	 * <ol>
	 *  <li> The employee is logged in
	 * 	<li> The employee check if another employee is available
	 *  <li> The project application knows that the other employee is unavailable because of personal activity and notifies the user
	 * </ol>
	 */
	@Test
	public void TestCheckEmployeeForPersonalActivity() {
		
		assertFalse(projectApp.employeeLoggedIn());
		
		try {
			projectApp.login("abcd");
		} catch (NoSuchUserException e) {
			assertEquals("No user with initials \"abcd\" exist.", e
					.getMessage());
			assertEquals("Login", e.getOperation());
			fail("An exception was thrown");
		}
		
		assertTrue(projectApp.employeeLoggedIn());
		
		// Create start and end week calendar objects
		Calendar startWeek = new GregorianCalendar();
		Calendar endWeek = new GregorianCalendar();
		
		// Set start and end week for the period the employee wishes to check for the other employees availability
		startWeek.set(startWeek.WEEK_OF_YEAR, 4);
		endWeek.set(endWeek.WEEK_OF_YEAR, 7);
		
		// From SampleDataSetup2.java we see that "ooaa" has 0 active projects from week 4 to 7 and thus should be available. 
		// Check if the employee with initials "ooaa" is available from week 4 to 7:
		boolean EmployeeAvailable = projectApp.getEmployeeByInitials("ooaa").isAvailable(startWeek, endWeek);
		
		assertTrue(EmployeeAvailable);
		
		projectApp.logout();
		
		// "ooaa"'s activityLimit is 5 (per default) so then he adds himself to 1 personal activity in week 5 so that he will not be available for this week:
		try {
			projectApp.login("ooaa");
		} catch (NoSuchUserException e) {
			assertEquals("No user with initials \"abcd\" exist.", e
					.getMessage());
			assertEquals("Login", e.getOperation());
			fail("An exception was thrown");
		}
		Employee activeEmployee = projectApp.getActiveUser();
		
		// Create start and end week calendar objects
		startWeek = new GregorianCalendar();
		endWeek = new GregorianCalendar();
		
		// Set start and end week for the personal activity
		startWeek.set(startWeek.WEEK_OF_YEAR, 2);
		endWeek.set(endWeek.WEEK_OF_YEAR, 6);
		
		activeEmployee.createPersonalActivity("Holiday in Spain", startWeek, endWeek);
		
		projectApp.logout();
		
		try {
			projectApp.login("abcd");
		} catch (NoSuchUserException e) {
			assertEquals("No user with initials \"abcd\" exist.", e
					.getMessage());
			assertEquals("Login", e.getOperation());
			fail("An exception was thrown");
		}
		
		// Create start and end week calendar objects
		startWeek = new GregorianCalendar();
		endWeek = new GregorianCalendar();
		
		// Set start and end week for the period the employee wishes to check for the other employees availability
		startWeek.set(startWeek.WEEK_OF_YEAR, 4);
		endWeek.set(endWeek.WEEK_OF_YEAR, 7);
		
		// Check if the employee with initials "ooaa" is available from week 4 to 7:
		EmployeeAvailable = projectApp.getEmployeeByInitials("ooaa").isAvailable(startWeek, endWeek);
		
		assertFalse(EmployeeAvailable);
	}
}
