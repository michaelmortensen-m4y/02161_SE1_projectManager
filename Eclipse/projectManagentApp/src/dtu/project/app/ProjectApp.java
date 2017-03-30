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
	boolean logi = false;

	public Calendar getDate() {
		return ds.getDate();
	}

	public void setDateServer(DateServer dateServer) {
		this.ds = dateServer;
	}
	
	void registerNewEmployee(String initials) {
		// register a new employee and call login for that employee
		activeUser = new Employee(initials);
		employees.add(activeUser);
		login();
	}
	
	void login() {
		logi = true;
		// set active user as the employee
	}
	
	void logout() {
		logi = false;
		// set active user as unregistered
	}

	public List<Employee> getEmployees() {
		// TODO Auto-generated method stub
		return employees;
	}

	public boolean employeeLoggedIn() {
		// TODO Auto-generated method stub
		if (logi) {
			return true;
		}
		return false;
	}

	public boolean employeeRegister(String string) {
		// TODO Auto-generated method stub
		return false;
	}
}
