package application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Project {
	
	private String name;
	private int id;
	public Employee projectManager;
	public List<Activity> activities = new ArrayList<Activity>(); // Activities in the project
	public List<Report> reports = new ArrayList<Report>(); // Report generated for the project
	private ProjectApp projectApp;
	private Calendar dateCreated; // The date that the project was generated
	
	public Project(String name, int id, Employee projectManager) {
		this.name = name;
		this.id = id;
		this.projectManager = projectManager;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public Employee getProjectManager() {
		return projectManager;
	}
	
	public void setProjectManager(Employee employee) {
		projectManager = employee;
	}
	
	public List<Activity> getActivities() {
		return this.activities;
	}
	
	public void addActivity(Activity activity) {
		activities.add(activity);
	}
	
	public void removeActivity(Activity activity) {
		for (Employee employee : projectApp.getEmployees()) {
			employee.removeFromActivity(activity);
		}
		activities.remove(activity);
	}
	
	public void createActivity(String name, int budgetedTime, Calendar startWeek, Calendar endWeek) {
		if (name.trim().length() > 0 && budgetedTime >= 0 && endWeek.compareTo(startWeek) >= 0 && projectManager == this.projectApp.getActiveUser()) {
			Activity newActivity = new Activity(name, budgetedTime, startWeek, endWeek, false);
			addActivity(newActivity);
		}
	}
	
	public Activity getActivityByName(String name) {
		for (Activity activity : activities) {
			if (activity.getName().equals(name)) {
				return activity;
			}
		}
		return null;
	}
	
	void setProjectApp(ProjectApp projectApp) {
		this.projectApp = projectApp;
		this.dateCreated = projectApp.getDate();
	}
	
	public void generateReport() {
		Report report = new Report(this, projectApp.getDate());
		reports.add(report);
	}

}
