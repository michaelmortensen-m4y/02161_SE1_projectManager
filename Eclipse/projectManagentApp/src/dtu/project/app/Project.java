package dtu.project.app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Project {
	
	private String name;
	private int id;
	public Employee projectManager;
	public List<Activity> activities = new ArrayList<Activity>(); // Activities in the application
	
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

}
