package application;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Log {
	
	private Employee employee; // The employee that the log is about
	private Activity activity; // The activity that the log is about
	private double timeSpent; // The time that the employee has spent on the activity
	private Calendar date; // The date that the log was made
	
	public Log(Employee employee, Activity activity, double timeSpent) {
		this.employee = employee;
		this.activity = activity;
		this.timeSpent = timeSpent;
		this.date = new GregorianCalendar();
	}
	
	public Employee getEmployee() {
		return employee;
	}
	
	public Activity getActivity() {
		return activity;
	}
	
	public double getTimeSpent() {
		return timeSpent;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	
	public void setTimeSpent(double timeSpent) {
		this.timeSpent = timeSpent;
	}

}
