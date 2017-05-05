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
// By: Michael Mortensen	
	public Project(String name, int id, Employee projectManager) {
		this.name = name;
		this.id = id;
		this.projectManager = projectManager;
	}
// By: Andreas T. Christensen	
	public String getName() {
		return this.name;
	}
// By: Mark Christensen	
	public void setName(String name) {
		this.name = name;
	}
// By: Michael Mortensen	
	public int getId() {
		return this.id;
	}
// By: Andreas T. Christensen	
	public Employee getProjectManager() {
		return this.projectManager;
	}
// By: Mark Christensen	
	public void setProjectManager(Employee employee) {
		this.projectManager = employee;
	}
// By: Michael Mortensen	
	public List<Activity> getActivities() {
		return this.activities;
	}
// By: Andreas T. Christensen	
	public void addActivity(Activity activity) {
		this.activities.add(activity);
	}
// By: Mark Christensen	
	public void removeActivity(Activity activity) {
		for (Employee employee : this.projectApp.getEmployees()) {
			employee.removeFromActivity(activity);
		}
		this.activities.remove(activity);
	}
// By: Michael Mortensen	
	public void createActivity(String name, int budgetedTime, Calendar startWeek, Calendar endWeek) {
		if (name.trim().length() > 0 && budgetedTime >= 0 && endWeek.compareTo(startWeek) >= 0 && this.projectManager == this.projectApp.getActiveUser()) {
			Activity newActivity = new Activity(name, budgetedTime, startWeek, endWeek, false);
			addActivity(newActivity);
		}
	}
// By: Andreas T. Christensen	
	public Activity getActivityByName(String name) {
		for (Activity activity : this.activities) {
			if (activity.getName().equals(name)) {
				return activity;
			}
		}
		return null;
	}
// By: Mark Christensen	
	void setProjectApp(ProjectApp projectApp) {
		this.projectApp = projectApp;
		this.dateCreated = projectApp.getDate();
	}
// By: Michael Mortensen	
	public void generateReport() {
		Report report = new Report(this, this.projectApp.getDate());
		this.reports.add(report);
	}

}
