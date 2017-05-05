package application;

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
 * This class contains tests, testing the functionality of adding projects to the system.
 * @author group 9
 *
 */
public class TestCreateNewProject extends SampleDataSetup1 {
	/** 
	 * Tests the scenarios when an employee tries to create a new project with: a valid name while logged in, an invalid name, and valid name but not logged in.
	 * <ol>
	 *  <li> The employee is logged in
	 * 	<li> The employee provides a valid name for the project
	 *  <li> The project application creates a new project with that name and generates a unique serial number
	 *  <li> Try to create a new project this time with an invalid name
	 *  <li> The application knows the input is invalid
	 *  <li> Employee logs out
	 *  <li> User tries to create a new project
	 *  <li> The application knows that the user has to be logged in to create projects
	 * </ol>
	 */
	@Test
	public void testWhiteBoxCreateProject() { // createNewProject() white-box test A-C
		
		List<Project> projects = projectApp.getProjects();

		try {
			projectApp.login("abcd");
		} catch (NoSuchUserException e) {
			assertEquals("No user with initials \"abcd\" exist.", e
					.getMessage());
			assertEquals("Login", e.getOperation());
			fail("An exception was thrown");
		}
		
		// Check with valid name and logged in
		projectApp.createNewProject("project 1");
		assertEquals(1, projects.size());
		
		// Check that the fields contains the correct information
		assertEquals("project 1", projectApp.getProjectById(projectApp.serialNumberCounter).getName());
		assertEquals(projectApp.activeUser, projectApp.getProjectById(projectApp.serialNumberCounter).getProjectManager());
		
		// Check when invalid name and logged in
		projectApp.createNewProject(" ");
		assertEquals(1, projects.size());
		
		// Check when logged out
		projectApp.logout();
		projectApp.createNewProject("project 2");
		assertEquals(1, projects.size());
	}
	
	/** 
	 * Tests the scenario when an employee successfully creates a new project and is automatically set as project manager and then changes the project manager to another employee.
	 * <ol>
	 *  <li> The employee is logged in
	 * 	<li> The employee provides a name for the project
	 *  <li> The project application creates a new project with that name and generates a unique serial number and sets the project manager as the employee
	 * 	<li> The employee sets a new project manager for the existing project
	 *  <li> The project application sets the new project manager
	 * </ol>
	 */
	@Test
	public void testSetNewManager() { 
		
		List<Project> projects = projectApp.getProjects();
		assertEquals(0, projects.size());
		
		try {
			projectApp.login("abcd");
		} catch (NoSuchUserException e) {
			assertEquals("No user with initials \"abcd\" exist.", e
					.getMessage());
			assertEquals("Login", e.getOperation());
			fail("An exception was thrown");
		}
		
		projectApp.createNewProject("project 1");
		assertEquals(1, projects.size());
		
		// Check that the project object returns the correct project manager "abcd"
		assertEquals(projectApp.getEmployeeByInitials("abcd"), projectApp.getProjectById(projectApp.serialNumberCounter).getProjectManager());
		
		// Set another employee as project manager
		projectApp.setProjectManager(projectApp.getProjectById(projectApp.serialNumberCounter), projectApp.getEmployeeByInitials("efgh"));
		
		// Check that the project object returns the new correct project manager "efgh"
		assertEquals(projectApp.getEmployeeByInitials("efgh"), projectApp.getProjectById(projectApp.serialNumberCounter).getProjectManager());
		
	}
	
	/** 
	 * Tests get project by manager even when the manager changes.
	 * <ol>
	 *  <li> The employee is logged in
	 * 	<li> The employee provides a name for the project
	 *  <li> The project application creates a new project with that name and generates a unique serial number and sets the project manager as the employee
	 * 	<li> The employee sets a new project manager for the existing project
	 *  <li> The project application sets the new project manager
	 * </ol>
	 */
	@Test
	public void testGetProjectByManagerEvenWhenSettingANewManager() {
		
		List<Project> projects = projectApp.getProjects();
		assertEquals(0, projects.size());
		
		try {
			projectApp.login("abcd");
		} catch (NoSuchUserException e) {
			assertEquals("No user with initials \"abcd\" exist.", e
					.getMessage());
			assertEquals("Login", e.getOperation());
			fail("An exception was thrown");
		}
		
		projectApp.createNewProject("project 1");
		assertEquals(1, projects.size());
		
		// Check that the project object returns the correct project manager
		assertEquals(projectApp.getProjectById(projectApp.serialNumberCounter), projectApp.getProjectsByManager(projectApp.getEmployeeByInitials("abcd")).get(0));
		// Check that "abcd" is project manager of one project
		assertEquals(1, projectApp.getProjectsByManager(projectApp.getEmployeeByInitials("abcd")).size());
		
		// Set another employee as project manager
		projectApp.setProjectManager(projectApp.getProjectById(projectApp.serialNumberCounter), projectApp.getEmployeeByInitials("efgh"));
		
		// Check that the project object returns the correct project manager
		assertEquals(projectApp.getProjectById(projectApp.serialNumberCounter), projectApp.getProjectsByManager(projectApp.getEmployeeByInitials("efgh")).get(0));
		// Check that "abcd" is no longer project manager for any projects
		assertEquals(0, projectApp.getProjectsByManager(projectApp.getEmployeeByInitials("abcd")).size());
		
	}
	
	/** 
	 * Tests that creating a new activity adds the activity to the project.
	 * <ol>
	 *  <li> The employee is logged in and creates a new project
	 *  <li> The employee provides a valid name for a new activity and creates it
	 *  <li> Check that the new activity returns correct information
	 * </ol>
	 */
	@Test
	public void testCreateNewActivity() { // Black-box test A
		
		try {
			projectApp.login("abcd");
		} catch (NoSuchUserException e) {
			assertEquals("No user with initials \"abcd\" exist.", e
					.getMessage());
			assertEquals("Login", e.getOperation());
			fail("An exception was thrown");
		}
		
		projectApp.createNewProject("project 1");
		
		// Check that active employee is project manager for the newly created project
		assertEquals("abcd", projectApp.getActiveUser().getInitials());
		
		// Create start and end week calendar objects
		Calendar startWeek = new GregorianCalendar();
		Calendar endWeek = new GregorianCalendar();
		
		// Set start and end week
		startWeek.set(startWeek.WEEK_OF_YEAR, 1);
		endWeek.set(endWeek.WEEK_OF_YEAR, 3);
		
		// Check that no activities have been added yet
		assertEquals(0, projectApp.getProjectById(1).getActivities().size());
		
		// Create a new activity for the project
		projectApp.getProjectById(1).createActivity("activity 1", 15, startWeek, endWeek);
	
		// Check that 1 activity have been added and that the fields in the activity object is set with the correct information
		assertEquals(1, projectApp.getProjectById(1).getActivities().size());
		assertEquals("activity 1", projectApp.getProjectById(1).getActivities().get(0).getName());
		assertEquals(projectApp.getProjectById(1).getActivities().get(0), projectApp.getProjectById(1).getActivityByName("activity 1"));
		assertEquals(15, projectApp.getProjectById(1).getActivities().get(0).getBudgetedTime());
		assertEquals(startWeek, projectApp.getProjectById(1).getActivities().get(0).getStartWeek());
		assertEquals(endWeek, projectApp.getProjectById(1).getActivities().get(0).getEndWeek());
		
		// Check that no activities with invalid names can be added // Black-box test B
		projectApp.getProjectById(1).createActivity(" ", 15, startWeek, endWeek);
		assertEquals(1, projectApp.getProjectById(1).getActivities().size());
	}
	
	/** 
	 * Tests that creating a new activity while not project manager does not add the activity to the project.
	 * <ol>
	 *  <li> The employee is logged in and creates a new project
	 *  <li> The employee logs out and another employee logs in
	 *  <li> The other employee tries to add a new activity to the project created earlier
	 *  <li> The application knows that the other employee is not the project manager and no activities are added
	 * </ol>
	 */
	@Test
	public void testCreateNewActivityNotManager() { // Black-box test C
		
		try {
			projectApp.login("abcd");
		} catch (NoSuchUserException e) {
			assertEquals("No user with initials \"abcd\" exist.", e.getMessage());
			assertEquals("Login", e.getOperation());
			fail("An exception was thrown");
		}
		
		projectApp.createNewProject("project 1");
		
		projectApp.logout();
		
		// Login as another employee "efgh"
		try {
			projectApp.login("efgh");
		} catch (NoSuchUserException e) {
			assertEquals("No user with initials \"efgh\" exist.", e.getMessage());
			assertEquals("Login", e.getOperation());
			fail("An exception was thrown");
		}
		
		// Create start and end week calendar objects
		Calendar startWeek = new GregorianCalendar();
		Calendar endWeek = new GregorianCalendar();
		
		// Set start and end week
		startWeek.set(startWeek.WEEK_OF_YEAR, 1);
		endWeek.set(endWeek.WEEK_OF_YEAR, 3);
		
		// Create a new activity for the project that "abcd" created
		projectApp.getProjectById(1).createActivity("activity 1", 15, startWeek, endWeek);
	
		// Check that no activity have been added
		assertEquals(0, projectApp.getProjectById(1).getActivities().size());
	}
	
	/** 
	 * Tests that an available employee successfully can be added to an activity that he/she has not already joined
	 * <ol>
	 *  <li> Employee logs in
	 *  <li> Employee creates a new activity
	 *  <li> Employee joins activity
	 *  <li> The application knows the employee is available and has not already joined the activity
	 * </ol>
	 */
	@Test
	public void testSuccesfullyAddAvailableEmployeeToActivityNotJoined() { // addToActivity() white-box test A
		
		try {
			projectApp.login("abcd");
		} catch (NoSuchUserException e) {
			assertEquals("No user with initials \"abcd\" exist.", e.getMessage());
			assertEquals("Login", e.getOperation());
			fail("An exception was thrown");
		}
		
		projectApp.createNewProject("project 1");

		// Create start and end week calendar objects
		Calendar startWeek = new GregorianCalendar();
		Calendar endWeek = new GregorianCalendar();
		
		// Set start and end week
		startWeek.set(startWeek.WEEK_OF_YEAR, 1);
		endWeek.set(endWeek.WEEK_OF_YEAR, 3);
		
		// Create a new activity for the project that "abcd" created
		projectApp.getProjectById(1).createActivity("activity 1", 15, startWeek, endWeek);
		
		// Check that "abcd" has not not joined the activity
		assertFalse(projectApp.getActiveUser().getActivities().contains(projectApp.getProjectById(projectApp.serialNumberCounter).getActivityByName("activity 1")));
		
		// Check that "abcd" is available
		assertTrue(projectApp.getActiveUser().isAvailable(startWeek, endWeek));
		
		// Add "abcd" to activity
		projectApp.getActiveUser().addToActivity(projectApp.getProjectById(projectApp.serialNumberCounter).getActivityByName("activity 1"));
		
		// Check that "abcd" has been added
		assertTrue(projectApp.getActiveUser().getActivities().contains(projectApp.getProjectById(projectApp.serialNumberCounter).getActivityByName("activity 1")));
	}
	
	/** 
	 * Tests that an available employee is not added to an activity that he/she has already joined
	 * <ol>
	 *  <li> Employee logs in
	 *  <li> Employee creates a new activity
	 *  <li> Employee joins activity
	 *  <li> Employee tries to join activity again
	 *  <li> The application knows the employee can not join an activity that he/she has already joined
	 * </ol>
	 */
	@Test
	public void testAddAvailableEmployeeToActivityAlreadyJoined() { // addToActivity() white-box test B
		
		try {
			projectApp.login("abcd");
		} catch (NoSuchUserException e) {
			assertEquals("No user with initials \"abcd\" exist.", e.getMessage());
			assertEquals("Login", e.getOperation());
			fail("An exception was thrown");
		}
		
		projectApp.createNewProject("project 1");

		// Create start and end week calendar objects
		Calendar startWeek = new GregorianCalendar();
		Calendar endWeek = new GregorianCalendar();
		
		// Set start and end week
		startWeek.set(startWeek.WEEK_OF_YEAR, 1);
		endWeek.set(endWeek.WEEK_OF_YEAR, 3);
		
		// Create a new activity for the project that "abcd" created
		projectApp.getProjectById(1).createActivity("activity 1", 15, startWeek, endWeek);
		
		// Add "abcd" to activity
		projectApp.getActiveUser().addToActivity(projectApp.getProjectById(projectApp.serialNumberCounter).getActivityByName("activity 1"));
		
		// Check that "abcd" has been added
		assertTrue(projectApp.getActiveUser().getActivities().contains(projectApp.getProjectById(projectApp.serialNumberCounter).getActivityByName("activity 1")));
		assertEquals(1, projectApp.getActiveUser().getActivities().size());
		
		// Try to add "abcd" to the activity again
		projectApp.getActiveUser().addToActivity(projectApp.getProjectById(projectApp.serialNumberCounter).getActivityByName("activity 1"));
		
		// Check that "abcd" has not been added to the same activity twice
		assertTrue(projectApp.getActiveUser().getActivities().contains(projectApp.getProjectById(projectApp.serialNumberCounter).getActivityByName("activity 1")));
		assertEquals(1, projectApp.getActiveUser().getActivities().size());
	}
	
	/** 
	 * Tests that an unavailable employee can not be added to an activity even if he/she has not already joined the activity
	 * <ol>
	 *  <li> Employee logs in
	 *  <li> Employee creates a new activity
	 *  <li> Employee is unavailable
	 *  <li> Employee tries to join activity
	 *  <li> The application knows the employee can not join an activity if he/she is not available
	 * </ol>
	 */
	@Test
	public void testAddEmployeeNotAvailableToActivityNotJoined() { // addToActivity() white-box test C
		
		try {
			projectApp.login("abcd");
		} catch (NoSuchUserException e) {
			assertEquals("No user with initials \"abcd\" exist.", e.getMessage());
			assertEquals("Login", e.getOperation());
			fail("An exception was thrown");
		}
		
		projectApp.createNewProject("project 1");

		// Create start and end week calendar objects
		Calendar startWeek = new GregorianCalendar();
		Calendar endWeek = new GregorianCalendar();
		
		// Set start and end week
		startWeek.set(startWeek.WEEK_OF_YEAR, 1);
		endWeek.set(endWeek.WEEK_OF_YEAR, 3);
		
		// Create a new activity for the project that "abcd" created
		projectApp.getProjectById(1).createActivity("activity 1", 15, startWeek, endWeek);
		
		// Make "abcd" unavailable by setting his/her activity limit to 0
		assertEquals(5, projectApp.getActiveUser().getActivityLimit()); // 5 is default
		projectApp.getActiveUser().setActivityLimit(0); // Always unavailable when 0
		
		// Try to add "abcd" to the activity
		projectApp.getActiveUser().addToActivity(projectApp.getProjectById(projectApp.serialNumberCounter).getActivityByName("activity 1"));
		
		// Check that "abcd" has not been added to the activity
		assertFalse(projectApp.getActiveUser().getActivities().contains(projectApp.getProjectById(projectApp.serialNumberCounter).getActivityByName("activity 1")));
		assertEquals(0, projectApp.getActiveUser().getActivities().size());
		
	}
	
	/** 
	 * Tests that removing an employee from an activity works
	 * <ol>
	 *  <li> Employee logs in
	 *  <li> Employee creates a new project and an activity
	 *  <li> Employee joins activity
	 *  <li> The application knows the employee has joined the activity
	 *  <li> Employee leaves the activity
	 *  <li> The application knows the employee is no longer active in the activity
	 * </ol>
	 */
	@Test
	public void testRemoveFromActivity() { 
		
		try {
			projectApp.login("abcd");
		} catch (NoSuchUserException e) {
			assertEquals("No user with initials \"abcd\" exist.", e.getMessage());
			assertEquals("Login", e.getOperation());
			fail("An exception was thrown");
		}
		
		projectApp.createNewProject("project 1");

		// Create start and end week calendar objects
		Calendar startWeek = new GregorianCalendar();
		Calendar endWeek = new GregorianCalendar();
		
		// Set start and end week
		startWeek.set(startWeek.WEEK_OF_YEAR, 1);
		endWeek.set(endWeek.WEEK_OF_YEAR, 3);
		
		// Create a new activity for the project that "abcd" created
		projectApp.getProjectById(1).createActivity("activity 1", 15, startWeek, endWeek);
		
		// Add "abcd" to the activity
		projectApp.getActiveUser().addToActivity(projectApp.getProjectById(projectApp.serialNumberCounter).getActivityByName("activity 1"));
		
		// Check that "abcd" has been added to the activity
		assertTrue(projectApp.getActiveUser().getActivities().contains(projectApp.getProjectById(projectApp.serialNumberCounter).getActivityByName("activity 1")));
		assertEquals(1, projectApp.getActiveUser().getActivities().size());
		
		// Leave the activity
		projectApp.getActiveUser().removeFromActivity(projectApp.getProjectById(projectApp.serialNumberCounter).getActivityByName("activity 1"));
		
		// Check that "abcd" is no longer in the activity
		assertFalse(projectApp.getActiveUser().getActivities().contains(projectApp.getProjectById(projectApp.serialNumberCounter).getActivityByName("activity 1")));
		assertEquals(0, projectApp.getActiveUser().getActivities().size());
		
	}
	
	/** 
	 * Tests that removing an activity from a project works
	 * <ol>
	 *  <li> Employee logs in
	 *  <li> Employee creates a new project and an activity
	 *  <li> Employee joins activity
	 *  <li> Employee removes the activity from the project
	 *  <li> The application knows the activity no longer exist and that no employees can be added to it
	 * </ol>
	 */
	@Test
	public void testRemoveActivityFromProject() {
		
		try {
			projectApp.login("abcd");
		} catch (NoSuchUserException e) {
			assertEquals("No user with initials \"abcd\" exist.", e.getMessage());
			assertEquals("Login", e.getOperation());
			fail("An exception was thrown");
		}
		
		projectApp.createNewProject("project 1");

		// Create start and end week calendar objects
		Calendar startWeek = new GregorianCalendar();
		Calendar endWeek = new GregorianCalendar();
		
		// Set start and end week
		startWeek.set(startWeek.WEEK_OF_YEAR, 1);
		endWeek.set(endWeek.WEEK_OF_YEAR, 3);
		
		// Create a new activity for the project that "abcd" created
		projectApp.getProjectById(1).createActivity("activity 1", 15, startWeek, endWeek);
		assertEquals(1, projectApp.getProjectById(1).getActivities().size());
		
		// Add "abcd" to the activity
		projectApp.getActiveUser().addToActivity(projectApp.getProjectById(projectApp.serialNumberCounter).getActivityByName("activity 1"));
		assertEquals(1, projectApp.getActiveUser().getActivities().size());
		
		// Remove the activity from the project
		projectApp.getProjectById(1).removeActivity(projectApp.getProjectById(projectApp.serialNumberCounter).getActivityByName("activity 1"));
		assertEquals(0, projectApp.getProjectById(1).getActivities().size());
		
		// Check that "abcd" is no longer in the activity
		assertFalse(projectApp.getActiveUser().getActivities().contains(projectApp.getProjectById(projectApp.serialNumberCounter).getActivityByName("activity 1")));
		assertEquals(0, projectApp.getActiveUser().getActivities().size());
		
	}
	
	/** 
	 * Tests that an employee successfully can add a personal activity
	 * <ol>
	 *  <li> Employee logs in
	 *  <li> Employee creates a new personal activity
	 * </ol>
	 */
	@Test
	public void testEmployeePersonalActivity() {
		try {
			projectApp.login("abcd");
		} catch (NoSuchUserException e) {
			assertEquals("No user with initials \"abcd\" exist.", e.getMessage());
			assertEquals("Login", e.getOperation());
			fail("An exception was thrown");
		}
		
		// Create start and end week calendar objects
		Calendar startWeek = new GregorianCalendar();
		Calendar endWeek = new GregorianCalendar();
		
		// Set start and end week
		startWeek.set(startWeek.WEEK_OF_YEAR, 1);
		endWeek.set(endWeek.WEEK_OF_YEAR, 3);
		
		// Create a new personal activity
		try {
			projectApp.getActiveUser().createPersonalActivity("Vacation", startWeek, endWeek);
		} catch (AlreadyJoinedWorkActivity e) {
			assertEquals("You can not have overlab between work activity and personal activity.", e.getMessage());
			assertEquals("createPersonalActivity", e.getOperation());
			fail("An exception was thrown");
		}
		
		// Verify that acitivity is added
		assertEquals(1, projectApp.getActiveUser().getActivities().size());
	}	
	
	/** 
	 * Tests that an available employee adds a personal activity and he/she becomes unavailable
	 * <ol>
	 *  <li> Employee logs in
	 *  <li> Employee creates a new personal activity
	 *  <li> Employee is now unavailable in the period of the personal activity for project activities.
	 *  <li> Employee can not be added to a new project activity
	 * </ol>
	 */
	@Test
	public void testEmployeePersonalActivityProjectActivity() {
		try {
			projectApp.login("abcd");
		} catch (NoSuchUserException e) {
			assertEquals("No user with initials \"abcd\" exist.", e.getMessage());
			assertEquals("Login", e.getOperation());
			fail("An exception was thrown");
		}
		
		// Create the project
		projectApp.createNewProject("project 1");
		
		// Create start and end week calendar objects
		Calendar startWeek = new GregorianCalendar();
		Calendar endWeek = new GregorianCalendar();
		
		// Set start and end week
		startWeek.set(startWeek.WEEK_OF_YEAR, 1);
		endWeek.set(endWeek.WEEK_OF_YEAR, 3);
		
		// Create a new personal activity
		try {	
			projectApp.getActiveUser().createPersonalActivity("Vacation", startWeek, endWeek);
		} catch (AlreadyJoinedWorkActivity e) {
			assertEquals("You can not have overlab between work activity and personal activity.", e.getMessage());
			assertEquals("createPersonalActivity", e.getOperation());
			fail("An exception was thrown");
		}
		// Verify that acitivity is added
		assertEquals(1, projectApp.getActiveUser().getActivities().size());
		
		// Create a new activity for the project that
		projectApp.getProjectById(1).createActivity("activity 1", 15, startWeek, endWeek);
		
		// Check that "abcd" is unavailable
		assertFalse(projectApp.getActiveUser().isAvailable(startWeek, endWeek));
		
		// Add "abcd" to activity
		projectApp.getActiveUser().addToActivity(projectApp.getProjectById(projectApp.serialNumberCounter).getActivityByName("activity 1"));
		
		// Check that "abcd" has not been added
		assertFalse(projectApp.getActiveUser().getActivities().contains(projectApp.getProjectById(projectApp.serialNumberCounter).getActivityByName("activity 1")));
		
		// Check that "abcd" has not not joined the activity
		assertEquals(1, projectApp.getActiveUser().getActivities().size());		
	}	
	
	/** 
	 * Tests that an available employee added to an activity, cannot be added
	 * to a personal activity in the same time span.
	 * <ol>
	 *  <li> Employee logs in
	 *  <li> Employee is added to a project activity
	 *  <li> Employee cannot add personal activity in same time span
	 * </ol>
	 */
	@Test
	public void testEmployeeProjectActivityPersonalActivity() {
		try {
			projectApp.login("abcd");
		} catch (NoSuchUserException e) {
			assertEquals("No user with initials \"abcd\" exist.", e.getMessage());
			assertEquals("Login", e.getOperation());
			fail("An exception was thrown");
		}
		
		// Create the project
		projectApp.createNewProject("project 1");
		
		// Create start and end week calendar objects
		Calendar startWeek = new GregorianCalendar();
		Calendar endWeek = new GregorianCalendar();
		
		// Set start and end week
		startWeek.set(startWeek.WEEK_OF_YEAR, 1);
		endWeek.set(endWeek.WEEK_OF_YEAR, 3);
				
		// Create a new activity for the project that
		projectApp.getProjectById(1).createActivity("activity 1", 15, startWeek, endWeek);
		
		// Check that "abcd" is available
		assertTrue(projectApp.getActiveUser().isAvailable(startWeek, endWeek));
		
		// Add "abcd" to activity
		projectApp.getActiveUser().addToActivity(projectApp.getProjectById(projectApp.serialNumberCounter).getActivityByName("activity 1"));
		
		// Check that "abcd" has been added
		assertTrue(projectApp.getActiveUser().getActivities().contains(projectApp.getProjectById(projectApp.serialNumberCounter).getActivityByName("activity 1")));
		
		// Check that "abcd" has joined the activity
		assertEquals(1, projectApp.getActiveUser().getActivities().size());		
		
		// Create a new personal activity and add
		try {
			projectApp.getActiveUser().createPersonalActivity("Vacation", startWeek, endWeek);
			fail("An exception should have been thrown");
		} catch (AlreadyJoinedWorkActivity e) {
			assertEquals("You can not have overlab between work activity and personal activity.", e.getMessage());
			assertEquals("createPersonalActivity", e.getOperation());
		}
		// Verify that activity is not added
		assertEquals(1, projectApp.getActiveUser().getActivities().size());		
	}		

	
	/** 
	 * Tests that an employee can successfully add himself to multiple personal activities in the same period
	 * <ol>
	 *  <li> Employee logs in
	 *  <li> Employee creates a new personal activity
	 *  <li> Employee creates another personal activity in the same time span
	 * </ol>
	 */
	@Test
	public void testEmployeeMultiplePersonalActivity() {
		try {
			projectApp.login("abcd");
		} catch (NoSuchUserException e) {
			assertEquals("No user with initials \"abcd\" exist.", e.getMessage());
			assertEquals("Login", e.getOperation());
			fail("An exception was thrown");
		}
		
		// Create start and end week calendar objects
		Calendar startWeek = new GregorianCalendar();
		Calendar endWeek = new GregorianCalendar();
		
		// Set start and end week
		startWeek.set(startWeek.WEEK_OF_YEAR, 1);
		endWeek.set(endWeek.WEEK_OF_YEAR, 3);
		
		// Create a new personal activity
		try {
			projectApp.getActiveUser().createPersonalActivity("Vacation", startWeek, endWeek);
		} catch (AlreadyJoinedWorkActivity e) {
			assertEquals("You can not have overlab between work activity and personal activity.", e.getMessage());
			assertEquals("createPersonalActivity", e.getOperation());
			fail("An exception was thrown");
		}
		
		// Verify that acitivity is added
		assertEquals(1, projectApp.getActiveUser().getActivities().size());	
		
		// Create a new personal activity
		try {
			projectApp.getActiveUser().createPersonalActivity("Sick Time", startWeek, endWeek);
		} catch (AlreadyJoinedWorkActivity e) {
			assertEquals("You can not have overlab between work activity and personal activity.", e.getMessage());
			assertEquals("createPersonalActivity", e.getOperation());
			fail("An exception was thrown");
		}		
		
		// Verify that acitivity is added
		assertEquals(2, projectApp.getActiveUser().getActivities().size());			
	}	
}
