package dtu.project.app;

import java.util.ArrayList;
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

}
