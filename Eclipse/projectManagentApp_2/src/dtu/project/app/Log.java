package dtu.project.app;

public class Log {
	
	public static final int DEFAUL_TIMESPENT = 0;
	
	private Employee employee; // The employee that the log is about
	private Activity activity; // The activity that the log is about
	private double timeSpent = DEFAUL_TIMESPENT; // The time that the employee has spent on the activity
	
	public Log(Employee employee, Activity activity, double timeSpent) {
		this.employee = employee;
		this.activity = activity;
		this.timeSpent = timeSpent;
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
