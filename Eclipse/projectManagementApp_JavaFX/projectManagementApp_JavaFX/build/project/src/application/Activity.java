package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Activity {
	
	private String name;
	private int budgetedTime;
	private Calendar startWeek;
	private Calendar endWeek;
	private boolean isPersonal;
	
	public Activity(String name, int budgetedTime, Calendar startWeek, Calendar endWeek, boolean isPersonal) {
		setName(name);
		setBudgetedTime(budgetedTime);
		setStartWeek(startWeek);
		setEndWeek(endWeek);
		this.isPersonal = isPersonal;
	}
	
	public String getName() {
		return name;
	}
	
	public int getBudgetedTime() {
		return budgetedTime;
	}
	
	public Calendar getStartWeek() {
		return startWeek;
	}
	
	public Calendar getEndWeek() {
		return endWeek;
	}	
	
	public boolean isPersonal() {
		return isPersonal;
	}
	
	public void setStartWeek(Calendar startWeek) {
		this.startWeek = startWeek;
	}
	
	public void setEndWeek(Calendar endWeek) {
		this.endWeek = endWeek;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setBudgetedTime(int budgetedTime) {
		this.budgetedTime = budgetedTime;
	}

}
