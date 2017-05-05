package application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Project {
	
	private String name;
	private int id;
	public Employee projectManager;
	public List<Activity> activities = new ArrayList<Activity>(); // Activities in the application
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
	
	public int getId() {
		return id;
	}
	
	public Employee getProjectManager() {
		return projectManager;
	}
	
	public void setProjectManager(Employee employee) {
		projectManager = employee;
	}
	
	public void addActivity(Activity activity) {
		activities.add(activity);
	}
	
	public void removeActivity(Activity activity) {
		activities.remove(activity);
	}
	
	public void createActivity(String name, int budgetedTime, Calendar startWeek, Calendar endWeek) {
		Activity newActivity = new Activity(name, budgetedTime, startWeek, endWeek, false);
		addActivity(newActivity);
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
