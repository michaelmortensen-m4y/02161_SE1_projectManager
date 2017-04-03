package dtu.project.app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Employee {
	
	public static final int DEFAUL_ACTIVITY_LIMIT = 10;
	
	private String initials;
	public int activityLimit;
	public List<Activity> joinedActivities = new ArrayList<Activity>(); // Activities the employee is in (including personal)
	
	public Employee(String initials) {
		this.initials = initials;
		activityLimit = DEFAUL_ACTIVITY_LIMIT;
	}
	
	public String getInitials() {
		return initials;
	}
	
	public int getActivityLimit() {
		return activityLimit;
	}

	public void setActivityLimit(int limit) {
		activityLimit = limit;
	}
	
	public List<Activity> getActivities() {
		return joinedActivities;
	}
	
	public void addToActivity(Activity activity) {
		if (joinedActivities.size() < activityLimit) {
			joinedActivities.add(activity);
		}
	}
	
	public void removeFromActivity(Activity activity) {
		joinedActivities.remove(activity);
	}
	
	public void createPersonalActivity(String name, Calendar startWeek, Calendar endWeek) {
		Activity newActivity = new Activity(name, -1, startWeek, endWeek, true);
		addToActivity(newActivity);
	}
}
