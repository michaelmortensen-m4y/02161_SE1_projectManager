package application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Employee {
	
	public static final int DEFAUL_ACTIVITY_LIMIT = 5;
	
	private String initials;
	public int activityLimit;
	public List<Activity> joinedActivities = new ArrayList<Activity>(); // Activities the employee is in (including personal)
	public List<Log> logs = new ArrayList<Log>(); // Logs the employee has made
	
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
		Calendar startWeek = new GregorianCalendar();
		Calendar endWeek = new GregorianCalendar();
		startWeek.setTime(activity.getStartWeek().getTime());
		endWeek.setTime(activity.getEndWeek().getTime());
		if (isAvailable(startWeek, endWeek)) {
			joinedActivities.add(activity);
			//System.out.println(initials + " blev tilf�jet til aktiviteten '" + activity.getName() + "'.");
		} else {
			//System.out.println("Ikke available");
		}
		Log log = new Log(this, activity, 0);
		logs.add(log);
	}
	
	public void removeFromActivity(Activity activity) {
		if (joinedActivities.size() > 0) {
			joinedActivities.remove(activity);
		}
	}
	
	public void createPersonalActivity(String name, Calendar startWeek, Calendar endWeek) {
		Activity newActivity = new Activity(name, -1, startWeek, endWeek, true);
		addToActivity(newActivity);
	}
	
	public boolean isAvailable(Calendar startWeek, Calendar endWeek) {
		if (joinedActivities.size() < 1) {
			return true;
		}
		Activity Act;
		int maxact = 0;
		Calendar calendarStartCheckWeek = startWeek;
		Calendar calendarStopCheckWeek = endWeek;
		calendarStopCheckWeek.add(Calendar.WEEK_OF_YEAR, 1);
		while(calendarStartCheckWeek.compareTo(calendarStopCheckWeek) != 0) {
			for (int i = 0; i < joinedActivities.size(); i++) {
				Act = joinedActivities.get(i);
				if (timeSpansOverlab(Act.getStartWeek(), Act.getEndWeek(), calendarStartCheckWeek, calendarStartCheckWeek)) {
					if (Act.isPersonal()) {
						return false;
					}
					maxact++;
				}
			}
			if(maxact >= activityLimit) {
				return false;
			}
			maxact = 0;
			calendarStartCheckWeek.add(Calendar.WEEK_OF_YEAR, 1);
		}	
		return true;
	}

	public boolean timeSpansOverlab(Calendar startWeek, Calendar endWeek, Calendar startWeek2, Calendar endWeek2) {
		if (startWeek.compareTo(endWeek2) <= 0 && startWeek.compareTo(startWeek2) >= 0) {
			return true;
		}
		if (endWeek.compareTo(endWeek2) <= 0 && endWeek.compareTo(startWeek2) >= 0) {
			return true;
		}
		if (startWeek2.compareTo(endWeek) <= 0 && startWeek2.compareTo(startWeek) >= 0) {
			return true;
		}
		if (endWeek2.compareTo(endWeek) <= 0 && endWeek2.compareTo(startWeek) >= 0) {
			return true;
		}
		return false;
	}

	public void logActivityTime(Log log, double hours) {
		log.setTimeSpent(hours);
	}
	
	public double unloggedHours(double hoursWorked, Calendar date) {
		double loggedHours = 0;
		for (Log log : logs) {
			if (timeSpansOverlab(log.getActivity().getStartWeek(), log.getActivity().getEndWeek(), date, date)) {
				loggedHours += log.getTimeSpent();
			}
		}
		return (hoursWorked - loggedHours);
	}
	
	public List<Activity> unloggedActivities(Calendar date) {
		List<Activity> unloggedActivities = new ArrayList<Activity>();
		for (Log log : logs) {
			if (timeSpansOverlab(log.getActivity().getStartWeek(), log.getActivity().getEndWeek(), date, date) || log.getTimeSpent() <= 0.1) {
				unloggedActivities.add(log.getActivity());
			}
		}
		return unloggedActivities;
	}
}
