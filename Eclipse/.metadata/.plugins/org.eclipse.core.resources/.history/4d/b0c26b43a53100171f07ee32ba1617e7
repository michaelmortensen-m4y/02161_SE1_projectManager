package application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
// By: Michael Mortensen	
	public Employee(String initials) {
		this.initials = initials;
		this.activityLimit = DEFAUL_ACTIVITY_LIMIT;
	}
// By: Andreas T. Christensen	
	public String getInitials() {
		return this.initials;
	}
// By: Mark Christensen	
	public int getActivityLimit() {
		return this.activityLimit;
	}
// By: Michael Mortensen
	public void setActivityLimit(int limit) {
		this.activityLimit = limit;
	}
// By: Andreas T. Christensen	
	public List<Activity> getActivities() {
		return this.joinedActivities;
	}
// By: Mark Christensen	
	public void addToActivity(Activity activity) {
		Calendar startWeek = new GregorianCalendar();
		Calendar endWeek = new GregorianCalendar();
		startWeek.setTime(activity.getStartWeek().getTime());
		endWeek.setTime(activity.getEndWeek().getTime());
		if ((isAvailable(startWeek, endWeek) && !this.joinedActivities.contains(activity)) || activity.isPersonal()) {
			this.joinedActivities.add(activity);
		} else {
			return;
		}
		Log log = new Log(this, activity, 0);
		this.logs.add(log);
	}
// By: Michael Mortensen	
	public void removeFromActivity(Activity activity) {
		if (this.joinedActivities.size() > 0) {
			this.joinedActivities.remove(activity);
		}
	}
// By: Andreas T. Christensen	
	public void createPersonalActivity(String name, Calendar startWeek, Calendar endWeek) throws AlreadyJoinedWorkActivity {
		Activity Act;
		int maxact = 0;
		Calendar calendarStartCheckWeek = startWeek;
		Calendar calendarStopCheckWeek = endWeek;
		calendarStopCheckWeek.add(Calendar.WEEK_OF_YEAR, 1);
		while(calendarStartCheckWeek.compareTo(calendarStopCheckWeek) != 0) {
			for (int i = 0; i < this.joinedActivities.size(); i++) {
				Act = this.joinedActivities.get(i);
				if ((timeSpansOverlab(Act.getStartWeek(), Act.getEndWeek(), calendarStartCheckWeek, calendarStartCheckWeek)) && !Act.isPersonal()) {
					maxact++;
				}
			}
			if (maxact > 0) {
				throw new AlreadyJoinedWorkActivity("You can not have overlab between work activity and personal activity.", "createPersonalActivity");
			}
			maxact = 0;
			calendarStartCheckWeek.add(Calendar.WEEK_OF_YEAR, 1);
		}
		addToActivity(new Activity(name, -1, startWeek, endWeek, true));
	}
// By: Mark Christensen	
	public boolean isAvailable(Calendar startWeek, Calendar endWeek) {
		Activity Act;
		int maxact = 0;
		Calendar calendarStartCheckWeek = startWeek;
		Calendar calendarStopCheckWeek = endWeek;
		calendarStopCheckWeek.add(Calendar.WEEK_OF_YEAR, 1);
		while(calendarStartCheckWeek.compareTo(calendarStopCheckWeek) != 0) {
			for (int i = 0; i < this.joinedActivities.size(); i++) {
				Act = this.joinedActivities.get(i);
				if (timeSpansOverlab(Act.getStartWeek(), Act.getEndWeek(), calendarStartCheckWeek, calendarStartCheckWeek)) {
					if (Act.isPersonal()) {
						return false;
					}
					maxact++;
				}
			}
			if(maxact >= this.activityLimit) {
				return false;
			}
			maxact = 0;
			calendarStartCheckWeek.add(Calendar.WEEK_OF_YEAR, 1);
		}	
		return true;
	}
// By: Michael Mortensen
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
// By: Andreas T. Christensen
	public void logActivityTime(Activity activity, double hours, Calendar date) throws InvalidWorkingTimeException {
		Log log = getLogByActivity(activity);
		if (hours < 0 || log == null) {
			throw new InvalidWorkingTimeException("Input has to be a possitive double type number and user has to join the activity before logging.", "logActivityTime");
		} else {
			log.setTimeSpent(hours);
			log.setLastLoggedDate(date);
		}
	}
// By: Mark Christensen	
	public Log getLogByActivity(Activity activity) {
    	for (Log log : this.logs) {
    		if (log.getActivity().equals(activity)) {;
    			return log;
			}
		}
    	return null;
	}
// By: Michael Mortensen	
	public double unloggedHours(double hoursWorked, Calendar date) {
		double loggedHours = 0;
		for (Log log : this.logs) {
			if (timeSpansOverlab(log.getActivity().getStartWeek(), log.getActivity().getEndWeek(), date, date)) {
				loggedHours += log.getTimeSpent();
			}
		}
		return (hoursWorked - loggedHours);
	}
// By: Andreas T. Christensen	
	public List<Activity> unloggedActivities(Calendar date) {
		List<Activity> unloggedActivities = new ArrayList<Activity>();
		Calendar startCheckDate = new GregorianCalendar();
		Calendar endCheckDate = new GregorianCalendar();
		startCheckDate.setTime(date.getTime());
		endCheckDate.setTime(date.getTime());
		startCheckDate.add(Calendar.HOUR_OF_DAY, -12);
		endCheckDate.add(Calendar.HOUR_OF_DAY, 12);
		for (Log log : this.logs) {
			if ((timeSpansOverlab(log.getActivity().getStartWeek(), log.getActivity().getEndWeek(), date, date)) && (!timeSpansOverlab(log.getLastLoggedDate(), log.getLastLoggedDate(), startCheckDate, endCheckDate)) || log.getTimeSpent() <= 0.1) {
				unloggedActivities.add(log.getActivity());
			}
		}
		return unloggedActivities;
	}
}
