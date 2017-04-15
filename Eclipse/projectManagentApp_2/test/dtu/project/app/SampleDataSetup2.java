package dtu.project.app;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;

/**
 * A class the defines a common setUp (e.g. a method with the @Before Annotation) containing
 * sample data.<p>
 * Test classes that want to use that data should inherit from this class.
 * @author group 9
 *
 */
public class SampleDataSetup2 {
	
	ProjectApp projectApp = new ProjectApp();
	/**
	 * Create the sample data for the search test cases.
	 * This method is executed each time one of the test methods is run.
	 * In contrast to old JUnit versions, it is now the @Before annotations that
	 * marks this method to be run before each test case and not the name setUp().
	 * 
	 * This method is inherited by subclasses. Thus any tests that needs sample data,
	 * should inherit from this class and add sample data as necessary.
	 */
	@Before
	public void setUp() {
		
		// Register 6 employees in the system
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
		
		// Register 1 more employee who is a project manager for some projects with some activities
		// Register employee and a new project
		projectApp.registerNewEmployee("yzae");
		projectApp.createNewProject("project 1");
		
		// Create start and end week calendar objects
		Calendar startWeek = new GregorianCalendar();
		Calendar endWeek = new GregorianCalendar();
		
		// Set start and end week
		startWeek.set(startWeek.WEEK_OF_YEAR, 1);
		endWeek.set(endWeek.WEEK_OF_YEAR, 3);
		// Create a new activity for the project
		projectApp.getProjectById(1).createActivity("activity 1 in project 1", 15, startWeek, endWeek);
		
		// Create start and end week calendar objects
		startWeek = new GregorianCalendar();
		endWeek = new GregorianCalendar();
		
		// Create a second activity for the same project and with other start and end weeks
		startWeek.set(startWeek.WEEK_OF_YEAR, 2);
		endWeek.set(endWeek.WEEK_OF_YEAR, 4);
		projectApp.getProjectById(1).createActivity("activity 2 in project 1", 8, startWeek, endWeek);
		
		// Create a second project
		projectApp.createNewProject("project 2");
		
		// Create start and end week calendar objects
		startWeek = new GregorianCalendar();
		endWeek = new GregorianCalendar();
		
		// Create activities for the second project with different start and end weeks
		startWeek.set(startWeek.WEEK_OF_YEAR, 5);
		endWeek.set(endWeek.WEEK_OF_YEAR, 6);
		projectApp.getProjectById(2).createActivity("activity 1 in project 2", 15, startWeek, endWeek);
		
		// Create start and end week calendar objects
		startWeek = new GregorianCalendar();
		endWeek = new GregorianCalendar();
		
		startWeek.set(startWeek.WEEK_OF_YEAR, 5);
		endWeek.set(endWeek.WEEK_OF_YEAR, 7);
		projectApp.getProjectById(2).createActivity("activity 2 in project 2", 8, startWeek, endWeek);
		
		// Add the employees called "abcd" and "efgh" to both activities in project 1
		Activity activity = projectApp.getProjectById(1).getActivityByName("activity 1 in project 1");
		
		Employee employee = projectApp.getEmployeeByInitials("abcd");
		employee.addToActivity(activity);
		
		employee = projectApp.getEmployeeByInitials("efgh");
		employee.addToActivity(activity);
		
		activity = projectApp.getProjectById(1).getActivityByName("activity 2 in project 1");
		
		employee = projectApp.getEmployeeByInitials("abcd");
		employee.addToActivity(activity);
		
		employee = projectApp.getEmployeeByInitials("efgh");
		employee.addToActivity(activity);
		
		// Add the employee called "abcd" to the first activity in project 2
		activity = projectApp.getProjectById(2).getActivityByName("activity 1 in project 2");
		employee = projectApp.getEmployeeByInitials("abcd");
		employee.addToActivity(activity);
		
		// Add the employee called "ijkl" to all activities in project 2
		activity = projectApp.getProjectById(2).getActivityByName("activity 1 in project 2");
		employee = projectApp.getEmployeeByInitials("ijkl");
		employee.addToActivity(activity);
		activity = projectApp.getProjectById(2).getActivityByName("activity 2 in project 2");
		employee.addToActivity(activity);
		
		// Add the employee called "efgh" to all activities in project 2
		activity = projectApp.getProjectById(2).getActivityByName("activity 1 in project 2");
		employee = projectApp.getEmployeeByInitials("efgh");
		employee.addToActivity(activity);
		activity = projectApp.getProjectById(2).getActivityByName("activity 2 in project 2");
		employee.addToActivity(activity);
		
		projectApp.logout();
		
		// Register 1 more employee who is a project manager for a project
		projectApp.registerNewEmployee("ooaa");
		projectApp.createNewProject("project 3");
		
		// Create start and end week calendar objects
		startWeek = new GregorianCalendar();
		endWeek = new GregorianCalendar();
		
		// Set start and end week
		startWeek.set(startWeek.WEEK_OF_YEAR, 2);
		endWeek.set(endWeek.WEEK_OF_YEAR, 2);
		// Create a new activity for the project
		projectApp.getProjectById(3).createActivity("activity 1 in project 3", 4, startWeek, endWeek);
		
		// Create start and end week calendar objects
		startWeek = new GregorianCalendar();
		endWeek = new GregorianCalendar();
		
		// Create a second activity for the same project and with other start and end weeks
		startWeek.set(startWeek.WEEK_OF_YEAR, 1);
		endWeek.set(endWeek.WEEK_OF_YEAR, 4);
		projectApp.getProjectById(3).createActivity("activity 2 in project 3", 8, startWeek, endWeek);
		
		// Create a second project
		projectApp.createNewProject("project 4");
		
		// Create start and end week calendar objects
		startWeek = new GregorianCalendar();
		endWeek = new GregorianCalendar();
		
		// Create activities for the second project with different start and end weeks
		startWeek.set(startWeek.WEEK_OF_YEAR, 5);
		endWeek.set(endWeek.WEEK_OF_YEAR, 6);
		projectApp.getProjectById(4).createActivity("activity 1 in project 4", 15, startWeek, endWeek);
		
		// Create start and end week calendar objects
		startWeek = new GregorianCalendar();
		endWeek = new GregorianCalendar();
		
		startWeek.set(startWeek.WEEK_OF_YEAR, 5);
		endWeek.set(endWeek.WEEK_OF_YEAR, 7);
		projectApp.getProjectById(4).createActivity("activity 2 in project 4", 8, startWeek, endWeek);
		
		// Add the employees called "abcd" and "ijkl" to both activities in project 3
		activity = projectApp.getProjectById(3).getActivityByName("activity 1 in project 3");
		
		employee = projectApp.getEmployeeByInitials("abcd");
		employee.addToActivity(activity);
		
		employee = projectApp.getEmployeeByInitials("ijkl");
		employee.addToActivity(activity);
		
		activity = projectApp.getProjectById(3).getActivityByName("activity 2 in project 3");
		
		employee = projectApp.getEmployeeByInitials("abcd");
		employee.addToActivity(activity);
		
		employee = projectApp.getEmployeeByInitials("ijkl");
		employee.addToActivity(activity);
		
		// Add the employee called "efgh" to all activities in project 4
		activity = projectApp.getProjectById(4).getActivityByName("activity 1 in project 4");
		employee = projectApp.getEmployeeByInitials("efgh");
		employee.addToActivity(activity);
		activity = projectApp.getProjectById(4).getActivityByName("activity 2 in project 4");
		employee.addToActivity(activity);
		
		// Add the employee called "ijkl" to all activities in project 4
		activity = projectApp.getProjectById(4).getActivityByName("activity 1 in project 4");
		employee = projectApp.getEmployeeByInitials("ijkl");
		employee.addToActivity(activity);
		activity = projectApp.getProjectById(4).getActivityByName("activity 2 in project 4");
		employee.addToActivity(activity);
		
		projectApp.logout();
		
		// "abcd" creates a new project with an activity without assigning people to it:
		projectApp.login("abcd");
		projectApp.createNewProject("project 5");
		// Create activities for the fifth project with same start and end weeks
		// Create start and end week calendar objects
		startWeek = new GregorianCalendar();
		endWeek = new GregorianCalendar();
		
		startWeek.set(startWeek.WEEK_OF_YEAR, 5);
		endWeek.set(endWeek.WEEK_OF_YEAR, 5);
		projectApp.getProjectById(5).createActivity("activity 1 in project 5", 15, startWeek, endWeek);
		
		// Create start and end week calendar objects
		startWeek = new GregorianCalendar();
		endWeek = new GregorianCalendar();
		
		startWeek.set(startWeek.WEEK_OF_YEAR, 5);
		endWeek.set(endWeek.WEEK_OF_YEAR, 5);
		projectApp.getProjectById(5).createActivity("activity 2 in project 5", 15, startWeek, endWeek);
		
		projectApp.logout();
	}


}
