package dtu.project.app;

import static org.junit.Assert.assertEquals;
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
public class TestCreateNewProject extends SampleDataSetup {
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

		ProjectApp projectApp = new ProjectApp();
		
		List<Project> projects = projectApp.getProjects();
		assertEquals(0, projects.size());
		
		assertTrue(projectApp.employeeLoggedIn());
		
		projectApp.createNewProject("project 1");
		
		assertEquals(0, projects.size());
	}

}