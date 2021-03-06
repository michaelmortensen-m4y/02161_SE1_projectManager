package application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

/**
 * This class contains tests, testing the functionality of adding projects to the system.
 * @author group 9
 *
 */
public class TestCreateNewProject extends SampleDataSetup1 {
	/** 
	 * Tests the scenario when an employee successfully creates a new project and is automatically set as project manager.
	 * <ol>
	 *  <li> The employee is logged in
	 * 	<li> The employee provides a name for the project
	 *  <li> The project application creates a new project with that name and generates a unique serial number
	 * </ol>
	 */
	@Test
	public void testCreateProject() {
		
		List<Project> projects = projectApp.getProjects();
		assertEquals(0, projects.size());
		
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
		
		projectApp.createNewProject("project 1");
		
		assertEquals(1, projects.size());
		assertEquals("project 1", projectApp.getProjectById(projectApp.serialNumberCounter).getName());
		assertEquals(projectApp.activeUser, projectApp.getProjectById(projectApp.serialNumberCounter).getProjectManager());
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
		
		projectApp.createNewProject("project 1");
		
		assertEquals(1, projects.size());
		
		assertEquals(projectApp.getEmployeeByInitials("abcd"), projectApp.getProjectById(projectApp.serialNumberCounter).getProjectManager());
		
		projectApp.setProjectManager(projectApp.getProjectById(projectApp.serialNumberCounter), projectApp.getEmployeeByInitials("efgh"));
		
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
		
		projectApp.createNewProject("project 1");
		
		assertEquals(1, projects.size());
		
		assertEquals(projectApp.getProjectById(projectApp.serialNumberCounter), projectApp.getProjectsByManager(projectApp.getEmployeeByInitials("abcd")).get(0));
		assertEquals(0, projectApp.getProjectsByManager(projectApp.getEmployeeByInitials("efgh")).size());
		
		projectApp.setProjectManager(projectApp.getProjectById(projectApp.serialNumberCounter), projectApp.getEmployeeByInitials("efgh"));
		
		assertEquals(projectApp.getProjectById(projectApp.serialNumberCounter), projectApp.getProjectsByManager(projectApp.getEmployeeByInitials("efgh")).get(0));
		assertEquals(0, projectApp.getProjectsByManager(projectApp.getEmployeeByInitials("abcd")).size());
		
	}

}
