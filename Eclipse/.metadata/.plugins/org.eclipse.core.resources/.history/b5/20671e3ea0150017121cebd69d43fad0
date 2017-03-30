package dtu.project.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

/**
 * This class contains tests, testing the functionality of searching for employees by their initials.
 * @author group 9
 *
 */
public class TestGetEmployeeByInitial extends SampleDataSetup {
	/** 
	 * Tests searching for employees by initials.
	 * <ol>
	 *  <li> The user provides initials
	 * 	<li> The project application check if an employee with the initials is registered in the system
	 * </ol>
	 */
	@Test
	public void testSearchEmployeeByInitial() {
		
		List<Employee> employees = projectApp.getEmployeeByInitial("abcd");
		assertEquals(1,employees.size());
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
		
		List<Employee> employees = projectApp.getEmployeeByInitial("aaaa");
		assertEquals(0,employees.size());
	}

}
