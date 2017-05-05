package application;

import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ProjectApp {
	
	public static final int INITIALS_MAX_LENGTH = 4;
	public static final int INITIALS_MIN_LENGTH = 1;
	
	public int serialNumberCounter;
	public List<Employee> employees = new ArrayList<Employee>(); // Employees registered in the application
	public List<Project> projects = new ArrayList<Project>(); // Projects in the application
	public Employee activeUser;
	public DateServer ds = new DateServer();
// By: Michael Mortensen
	public Calendar getDate() {
		return this.ds.getDate();
	}
// By: Andreas T. Christensen
	public void setDateServer(DateServer dateServer) {
		this.ds = dateServer;
	}
// By: Mark Christensen	
	public Employee getActiveUser() {
		return this.activeUser;
	}
	
// By: Michael Mortensen
	public void registerNewEmployee(String initials) throws InitialsInvalidException {
		// Check initials and register a new employee and call login for that employee
		if (initialsAccepted(initials)) {
			Employee newEmployee = new Employee(initials);
			this.employees.add(newEmployee);
			try {
				login(initials);
			} catch (NoSuchUserException e) {
				System.out.println(e.getMessage());
			}
		} else {
			throw new InitialsInvalidException("Input invalid. Initials does not comply with one or more of the following rules:\n1: Contains only letters (uppercase or lowercase).\n2: Minimum 1 letter.\n3: Maximum 4 letters.\n4: Initials must not already be registered.", "RegisterNewEmployee");
		}
	}
// By: Andreas T. Christensen
	public boolean initialsAccepted(String initials) {
		// return true if user is not logged in and initials comply with the rules and are not already in the system
		return (!employeeLoggedIn() && 
				initials.matches("[a-zA-Z]+") && 
				initials.length() >= INITIALS_MIN_LENGTH && 
				initials.length() <= INITIALS_MAX_LENGTH) &&
				getEmployeeByInitials(initials) == null;
	}
// By: Mark Christensen
	public void login(String initials) throws NoSuchUserException {
		if (!employeeLoggedIn()) {
			if (getEmployeeByInitials(initials) == null) {
				throw new NoSuchUserException("No user with initials \"" + initials + "\" exist.", "Login");
			} else {
				this.activeUser = getEmployeeByInitials(initials); // set active user as the employee
			}
		}
	}
// By: Michael Mortensen
	public void logout() {
		if (employeeLoggedIn()) {
			this.activeUser = null; // set active user as unregistered
		}
	}
// By: Andreas T. Christensen
	public List<Employee> getEmployees() {
		return this.employees;
	}
// By: Mark Christensen
	public boolean employeeLoggedIn() {
		return (this.activeUser != null);
	}
// By: Michael Mortensen
	public Employee getEmployeeByInitials(String initials) {
		for (Employee employee : this.employees) {
			if (employee.getInitials().equals(initials)) {
				return employee;
			}
		}
		return null;
	}
// By: Andreas T. Christensen
	public List<Project> getProjects() {
		return this.projects;
	}
// By: Mark Christensen
	public void createNewProject(String name) {
		if (employeeLoggedIn() && name.trim().length() > 0) {
			Project newProject = new Project(name, generateId(), this.activeUser);
			newProject.setProjectApp(this);
			this.projects.add(newProject);
		}
	}
// By: Michael Mortensen
	private int generateId() {
		this.serialNumberCounter++;
		return this.serialNumberCounter;
	}
// By: Andreas T. Christensen
	public void setProjectManager(Project project, Employee employee) {
		project.setProjectManager(employee);
	}
// By: Mark Christensen	
	public Project getProjectById(int id) {
		for (Project project : this.projects) {
			if (project.getId() == id) {
				return project;
			}
		}
		return null;
	}
// By: Michael Mortensen
	public List<Project> getProjectsByManager(Employee employee) {
		List<Project> projectsResult = new ArrayList<Project>();
		for (Project project : this.projects) {
			if (project.getProjectManager().equals(employee)) {
				projectsResult.add(project);
			}
		}
		return projectsResult;
	}
// By: Andreas T. Christensen
	public List<Employee> listAvailableEmployees(Calendar startWeek, Calendar endWeek) {
		List<Employee> employeesResult = new ArrayList<Employee>();
		for (Employee employee : this.employees) {
			Calendar startCheckWeek = new GregorianCalendar();
			Calendar endCheckWeek = new GregorianCalendar();
			startCheckWeek.setTime(startWeek.getTime());
			endCheckWeek.setTime(endWeek.getTime());
			if (employee.isAvailable(startCheckWeek, endCheckWeek)) {
				employeesResult.add(employee);
			}
		}
		return employeesResult;	
	}
	
}
