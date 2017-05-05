package application;

import java.util.Date;
import java.io.*;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import java.util.Calendar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class AddNewActivityMenuController implements Initializable {

	public StringBuilder addedActivitiesTextAreaContent = new StringBuilder("");
	public List<String> activityNames = new ArrayList<String>();
	public List<Integer> budgetedTimes = new ArrayList<Integer>();
	public List<Calendar> startDates = new ArrayList<Calendar>();
	public List<Calendar> endDates = new ArrayList<Calendar>();
	public List<List<Employee>> listOfEmployees = new ArrayList<List<Employee>>();
    
    @FXML
    private TextField activityField;
    
    @FXML
    private TextField budgetedTimeField;
    
    @FXML
    private TextField startDateField;
    
    @FXML
    private TextField endDateField;
    
    @FXML
    private TextField employeesField;
    
    @FXML
	public TextArea addedActivitiesTextArea;

    @FXML
    void cancelMouseClicked(MouseEvent event) {
    	ProjectAppNavigator.loadWindow("MainMenu.fxml");
    }
    
    @FXML
    void exportMouseClicked(MouseEvent event) {
    	try {
    		PrintWriter writer = new PrintWriter("ProjectReport_" + ProjectAppNavigator.activeProject.getId() + ".txt", "UTF-8");
    		ProjectAppNavigator.appendToOutputTextArea("Exported project report for project '" + ProjectAppNavigator.activeProject.getName() + "' to '" + "ProjectReport_" + ProjectAppNavigator.activeProject.getId() + ".txt" + ".");
	    	writer.println("---------------------------------------------------------------\n");
	    	writer.println("Project name:  " + ProjectAppNavigator.activeProject.getName() + "\n");
	    	writer.println("---------------------------------------------------------------\n\n");
	    	double projectBudgetedTime = 0.0;
	    	double projectHoursSpent = 0.0;
	    	for (Activity act : ProjectAppNavigator.activeProject.activities) {
	    		String emplString = "";
	    		Double hoursSpent = 0.0;
	    		List<Employee> employees = new ArrayList<Employee>();
	    		for (Employee employeeInApp : ProjectAppNavigator.projectApp.getEmployees()) {
	    			if (employeeInApp.getActivities().contains(act)) {
	    				employees.add(employeeInApp);
	    			}
	    			for (Log log : employeeInApp.logs) {
	    				if (log.getActivity().equals(act)) {
	    					hoursSpent += log.getTimeSpent();
	    				}
	    			}
	    			
	    		}    		
	    		for (Employee empl : employees) {
	        		emplString += empl.getInitials() + " ";
	        	}
	    		String startWeekString = "" + act.getStartWeek().get(Calendar.WEEK_OF_YEAR) + " " + act.getStartWeek().get(Calendar.YEAR);
	        	String endWeekString = "" + act.getEndWeek().get(Calendar.WEEK_OF_YEAR) + " " + act.getEndWeek().get(Calendar.YEAR);
	        	writer.println("Activity name:  " + act.getName() + "\n");
	        	writer.println("Budgeted time:  " + act.getBudgetedTime() + "\n");
	        	writer.println("Start week:  " + startWeekString + "\n");
	        	writer.println("End week:  " + endWeekString + "\n");
	        	writer.println("Currently enroled employees:  " + emplString + "\n");
	        	writer.println("Hours spent working on this activity:  " + hoursSpent + "\n");
	    		if (act.getBudgetedTime() >= hoursSpent) {
	    			writer.println("Expected remaining work for this activity:  " + (act.getBudgetedTime() - hoursSpent) + "\n");
	    		} else {
	    			writer.println("Expected remaining work for this activity:  Overdue with " + (hoursSpent - act.getBudgetedTime()) + " hours.\n");
	    		}
	    		writer.println("---------------------------------------------------------------\n\n");
	    		projectBudgetedTime += act.getBudgetedTime();
	    		projectHoursSpent += hoursSpent;
	    	}
	    	writer.println("---------------------------------------------------------------\n");
	    	writer.println("Budgeted time for project:  " + projectBudgetedTime + "\n");
	    	writer.println("Hours spent working on this project:  " + projectHoursSpent + "\n");
	    	if (projectBudgetedTime >= projectHoursSpent) {
	    		writer.println("Expected remaining work for this activity:  " + (projectBudgetedTime - projectHoursSpent) + "\n");
			} else {
				writer.println("Expected remaining work for this activity:  Overdue with " + (projectHoursSpent - projectBudgetedTime) + " hours.\n");
			}
	    	writer.println("---------------------------------------------------------------\n");
	    	writer.close();
    	} catch (IOException iox) {
			iox.printStackTrace();
		}
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	for (Activity act : ProjectAppNavigator.activeProject.activities) {
    		String emplString = "";
    		List<Employee> employees = new ArrayList<Employee>();
    		for (Employee employeeInApp : ProjectAppNavigator.projectApp.getEmployees()) {
    			if (employeeInApp.getActivities().contains(act)) {
    				employees.add(employeeInApp);
    			}
    		}    		
    		for (Employee empl : employees) {
        		emplString += empl.getInitials() + " ";
        	}
        	String startWeekString = "" + act.getStartWeek().get(Calendar.WEEK_OF_YEAR) + " " + act.getStartWeek().get(Calendar.YEAR);
        	String endWeekString = "" + act.getEndWeek().get(Calendar.WEEK_OF_YEAR) + " " + act.getEndWeek().get(Calendar.YEAR);
        	addedActivitiesTextAreaContent.append("- " + act.getName() + " | " + act.getBudgetedTime() + " | " + startWeekString + " | " + endWeekString + " | " + emplString + "\n");
    	}
    	addedActivitiesTextArea.setText(addedActivitiesTextAreaContent.toString());
    	addedActivitiesTextArea.setScrollTop(Double.MAX_VALUE);
    } 
    
    @FXML
    void addActivityMouseClicked(MouseEvent event) {
    	if (activityField.getText().isEmpty()) {
    		ProjectAppNavigator.appendToOutputTextArea("Invalid input string for activity name. Has to contain at least 1 char.");
			return;
    	}
    	String activityName = activityField.getText();
    	int budgetedTime;
    	Calendar startDate = Calendar.getInstance();
    	Calendar endDate = Calendar.getInstance();
    	SimpleDateFormat sdfStart = new SimpleDateFormat("w yyyy", Locale.ENGLISH);
    	SimpleDateFormat sdfEnd = new SimpleDateFormat("w yyyy", Locale.ENGLISH);
    	Date dateStart;
    	Date dateEnd;
    	try {
			budgetedTime = Integer.parseInt(budgetedTimeField.getText());
		} catch (NumberFormatException e) {
			ProjectAppNavigator.appendToOutputTextArea("Invalid input number for budgeted time. Has to be only one integer.");
			return;
		}
		try {
			dateStart = sdfStart.parse(startDateField.getText());
		} catch (ParseException e) {
			ProjectAppNavigator.appendToOutputTextArea("Wrong start date input format. Use 'w yyyy' ex. '21 2017' meaning week 21 in year 2017.");
			return;
		}
		startDate = sdfStart.getCalendar();
		try {
			dateEnd = sdfEnd.parse(endDateField.getText());
		} catch (ParseException e) {
			ProjectAppNavigator.appendToOutputTextArea("Wrong end date input format. Use 'w yyyy' ex. '21 2017' meaning week 21 in year 2017.");
			return;
		}
		endDate = sdfEnd.getCalendar();
		
		List<Employee> employees = new ArrayList<Employee>();
		if (!(employeesField.getText().equals("Assign employees (aaaa bbbb ...)") || employeesField.getText().isEmpty())) {
    		String[] employeesInitials = employeesField.getText().split(" ");
    		Employee employee;
    		for (String employeeInitials : employeesInitials) {
    			employee = ProjectAppNavigator.projectApp.getEmployeeByInitials(employeeInitials);
    			if (ProjectAppNavigator.projectApp.getEmployees().contains(employee)) {
    				employees.add(employee);
    			} else {
    				ProjectAppNavigator.appendToOutputTextArea("No user with initials \"" + employeeInitials + "\" exist.");
    			}
    		}
    	}
    	
    	activityNames.add(activityName);
    	budgetedTimes.add(budgetedTime);
    	startDates.add(startDate);
    	endDates.add(endDate);
    	listOfEmployees.add(employees);
    	
    	String emplString = "";
    	for (Employee empl : employees) {
    		emplString += empl.getInitials() + " ";
    	}
    	
    	addedActivitiesTextAreaContent.append("- " + activityField.getText() + " | " + budgetedTimeField.getText() + " | " + startDateField.getText() + " | " + endDateField.getText() + " | " + emplString + "\n");
    	addedActivitiesTextArea.setText(addedActivitiesTextAreaContent.toString());
    	addedActivitiesTextArea.setScrollTop(Double.MAX_VALUE);
    }
    
    @FXML
    void removeActivityMouseClicked(MouseEvent event) {
    	if (activityField.getText().isEmpty()) {
    		ProjectAppNavigator.appendToOutputTextArea("No such activity has been added to the activity list.");
			return;
    	}
    	String activityName = activityField.getText();
    	int budgetedTime;
    	Calendar startDate = Calendar.getInstance();
    	Calendar endDate = Calendar.getInstance();
    	SimpleDateFormat sdfStart = new SimpleDateFormat("w yyyy", Locale.ENGLISH);
    	SimpleDateFormat sdfEnd = new SimpleDateFormat("w yyyy", Locale.ENGLISH);
    	Date dateStart;
    	Date dateEnd;
    	try {
			budgetedTime = Integer.parseInt(budgetedTimeField.getText());
		} catch (NumberFormatException e) {
			ProjectAppNavigator.appendToOutputTextArea("No such activity has been added to the activity list.");
			return;
		}
		try {
			dateStart = sdfStart.parse(startDateField.getText());
		} catch (ParseException e) {
			ProjectAppNavigator.appendToOutputTextArea("No such activity has been added to the activity list.");
			return;
		}
		startDate = sdfStart.getCalendar();
		try {
			dateEnd = sdfEnd.parse(endDateField.getText());
		} catch (ParseException e) {
			ProjectAppNavigator.appendToOutputTextArea("No such activity has been added to the activity list.");
			return;
		}
		endDate = sdfEnd.getCalendar();
		
		for (Activity act : ProjectAppNavigator.activeProject.activities) {
			if (act.getName().equals(activityName) && act.getBudgetedTime() == budgetedTime && act.getStartWeek().equals(startDate) && act.getEndWeek().equals(endDate)) {
				ProjectAppNavigator.activeProject.removeActivity(act);
				String emplString = "";
	    		List<Employee> employees = new ArrayList<Employee>();
	    		for (Employee employeeInApp : ProjectAppNavigator.projectApp.getEmployees()) {
	    			if (employeeInApp.getActivities().contains(act)) {
	    				employees.add(employeeInApp);
	    			}
	    		}    		
	    		for (Employee empl : employees) {
	        		emplString += empl.getInitials() + " ";
	        	}
	        	String startWeekString = "" + act.getStartWeek().get(Calendar.WEEK_OF_YEAR) + " " + act.getStartWeek().get(Calendar.YEAR);
	        	String endWeekString = "" + act.getEndWeek().get(Calendar.WEEK_OF_YEAR) + " " + act.getEndWeek().get(Calendar.YEAR);
	        	String activityString = "- " + act.getName() + " | " + act.getBudgetedTime() + " | " + startWeekString + " | " + endWeekString + " | " + emplString + "\n";
	        	int i = addedActivitiesTextAreaContent.indexOf(activityString);
	        	if (i != -1) {
	        		addedActivitiesTextAreaContent.delete(i, i+activityString.length());
	        	}
	        	addedActivitiesTextArea.setText(addedActivitiesTextAreaContent.toString());
	        	addedActivitiesTextArea.setScrollTop(Double.MAX_VALUE);
	        	ProjectAppNavigator.appendToOutputTextArea("Activity '" + act.getName() + "' removed from project '" + ProjectAppNavigator.activeProject.getName() + " (ID " + ProjectAppNavigator.activeProject.getId() + ")'.");
	        	return;
			}
		}
    	
    	if ((activityNames.contains(activityName) && budgetedTimes.contains(budgetedTime) && startDates.contains(startDate) && endDates.contains(endDate)) &&
    		(activityNames.indexOf(activityName) == budgetedTimes.indexOf(budgetedTime)) &&
    		(activityNames.indexOf(activityName) == startDates.indexOf(startDate)) &&
    		(activityNames.indexOf(activityName) == endDates.indexOf(endDate)))
    	{	
    		activityNames.remove(activityName);
	    	budgetedTimes.remove(budgetedTime);
	    	startDates.remove(startDate);
	    	endDates.remove(endDate);
	    	listOfEmployees.remove(activityNames.indexOf(activityName));
    	} else {
	    	ProjectAppNavigator.appendToOutputTextArea("No such activity has been added to the activity list.");
    		return;
    	}
    	
    	String emplString = "";
    	for (Employee empl : listOfEmployees.get(activityNames.indexOf(activityName))) {
    		emplString += empl.getInitials() + " ";
    	}
    	
    	String activityString = "- " + activityField.getText() + " | " + budgetedTimeField.getText() + " | " + startDateField.getText() + " | " + endDateField.getText() + " | " + emplString + "\n";
    	int i = addedActivitiesTextAreaContent.indexOf(activityString);
    	if (i != -1) {
    		addedActivitiesTextAreaContent.delete(i, i+activityString.length());
    	}
    	addedActivitiesTextArea.setText(addedActivitiesTextAreaContent.toString());
    	addedActivitiesTextArea.setScrollTop(Double.MAX_VALUE);
    }
    
    @FXML
    void okMouseClicked(MouseEvent event) {
    	for (int i = 0; i < activityNames.size(); i++) {
			ProjectAppNavigator.activeProject.createActivity(activityNames.get(i), budgetedTimes.get(i), startDates.get(i), endDates.get(i));
			for (Employee employee : listOfEmployees.get(i)) {
				employee.addToActivity(ProjectAppNavigator.activeProject.getActivityByName(activityNames.get(i)));
			}
			ProjectAppNavigator.appendToOutputTextArea("Added new activity '"+ activityNames.get(i) +"' to the project '" + ProjectAppNavigator.activeProject.getName() + " (ID: " + ProjectAppNavigator.activeProject.getId() + ")'.");
		}
    	ProjectAppNavigator.loadWindow("MainMenu.fxml");
    }
    
}