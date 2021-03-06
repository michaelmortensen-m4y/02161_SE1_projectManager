package dtu.project.app;

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

	public Calendar getDate() {
		return ds.getDate();
	}

	public void setDateServer(DateServer dateServer) {
		this.ds = dateServer;
	}
	
	public Employee getActiveUser() {
		return activeUser;
	}
	
	void registerNewEmployee(String initials) {
		// Check initials and register a new employee and call login for that employee
		if (initialsAccepted(initials)) {
			Employee newEmployee = new Employee(initials);
			employees.add(newEmployee);
			login(initials);
		}
	}
	
	public boolean initialsAccepted(String initials) {
		// return true if user is not logged in and initials comply with the rules and are not already in the system
		return (!employeeLoggedIn() && 
				initials.matches("[a-zA-Z]+") && 
				initials.length() >= INITIALS_MIN_LENGTH && 
				initials.length() <= INITIALS_MAX_LENGTH) &&
				getEmployeeByInitials(initials) == null;
	}
	
	void login(String initials) {
		if (!employeeLoggedIn()) {
			activeUser = getEmployeeByInitials(initials); // set active user as the employee
		}
	}
	
	void logout() {
		if (employeeLoggedIn()) {
			activeUser = null; // set active user as unregistered
		}
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public boolean employeeLoggedIn() {
		if (activeUser != null) {
			return true;
		}
		return false;
	}

	public Employee getEmployeeByInitials(String initials) {
		for (Employee employee : employees) {
			if (employee.getInitials().equals(initials)) {
				return employee;
			}
		}
		return null;
	}
	
	public List<Project> getProjects() {
		return projects;
	}
	
	void createNewProject(String name) {
		if (employeeLoggedIn()) {
			Project newProject = new Project(name, generateId(), activeUser);
			newProject.setProjectApp(this);
			projects.add(newProject);
		}
	}
	
	private int generateId() {
		serialNumberCounter++;
		return serialNumberCounter;
	}

	void setProjectManager(Project project, Employee employee) {
		project.setProjectManager(employee);
	}
	
	public Project getProjectById(int id) {
		for (Project project : projects) {
			if (project.getId() == id) {
				return project;
			}
		}
		return null;
	}
	
	public List<Project> getProjectsByManager(Employee employee) {
		List<Project> projectsResult = new ArrayList<Project>();
		for (Project project : projects) {
			if (project.getProjectManager().equals(employee)) {
				projectsResult.add(project);
			}
		}
		return projectsResult;
	}
	
	public List<Employee> listAvailableEmployees(Calendar startWeek, Calendar endWeek) {
		List<Employee> employeesResult = new ArrayList<Employee>();
		for (Employee employee : employees) {
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
	
	private String getCalendarString(Calendar cal){
		return "(" + cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH)+1) + ") " + cal.get(Calendar.HOUR_OF_DAY) + ":"+ cal.get(Calendar.MINUTE);
	}
	
	
}
