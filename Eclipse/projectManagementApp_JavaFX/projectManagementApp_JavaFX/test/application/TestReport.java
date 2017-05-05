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
public class TestReport extends SampleDataSetup2 {
	/** 
	 * Tests that...
	 * <ol>
	 *  <li> 
	 * </ol>
	 */
	@Test
	public void testSuccesfullyGeneratingReport() { 

		try {
			projectApp.login("abcd");
		} catch (NoSuchUserException e) {
			assertEquals("No user with initials \"abcd\" exist.", e.getMessage());
			assertEquals("Login", e.getOperation());
			fail("An exception was thrown");
		}
		
		// From SampleDataSetup2.java we see that "abcd" is project manager for project 5
		
		// Check that no reports have been generated for project 5
		assertEquals(0, projectApp.getProjectById(5).reports.size());
		
		// Generating report for project 5
		projectApp.getProjectById(5).generateReport();
		
		// Check that one report have been generated for project 5
		assertEquals(1, projectApp.getProjectById(5).reports.size());
	}

}
