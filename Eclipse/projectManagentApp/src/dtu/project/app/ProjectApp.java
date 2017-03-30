package dtu.project.app;

import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ProjectApp {
	
	private int serialNumberCounter;
	public List<Employee> employees = new ArrayList<Employee>(); // Employees registered in the application
	public Employee activeUser;
	public DateServer ds = new DateServer();

	public Calendar getDate() {
		return ds.getDate();
	}

	public void setDateServer(DateServer dateServer) {
		this.ds = dateServer;
	}
	
	void registerNewEmployee(String initials) {
		//
	}
	
	void login(Employee employee) {
		// set active user as the employee
	}
	
	void logout(Employee employee) {
		// set active user as unregistered
	}
}
