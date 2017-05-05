package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class EmployeesMenuController {
	
	public StringBuilder myPersonalActivitiesTextAreaContent = new StringBuilder("");
	
	@FXML
    private TextField initialsField;
	
	@FXML
    private TextField startDateField;
	
	@FXML
    private TextField endDateField;
	
	@FXML
	public TextArea myPersonalActivitiesTextArea;
	
	@FXML
    void backMouseClicked(MouseEvent event) {
    	ProjectAppNavigator.loadWindow("MainMenu.fxml");
    }
	
	@FXML
    void listAvailableEmployeesMouseClicked(MouseEvent event) {
		myPersonalActivitiesTextAreaContent.setLength(0);
		myPersonalActivitiesTextArea.setText(myPersonalActivitiesTextAreaContent.toString());
		myPersonalActivitiesTextArea.setScrollTop(Double.MAX_VALUE);
		Calendar startDate = Calendar.getInstance();
    	Calendar endDate = Calendar.getInstance();
    	SimpleDateFormat sdfStart = new SimpleDateFormat("w yyyy", Locale.ENGLISH);
    	SimpleDateFormat sdfEnd = new SimpleDateFormat("w yyyy", Locale.ENGLISH);
    	Date dateStart;
    	Date dateEnd;
		try {
			dateStart = sdfStart.parse(startDateField.getText().trim());
		} catch (ParseException e) {
			ProjectAppNavigator.appendToOutputTextArea("Wrong start date input format. Use 'w yyyy' ex. '21 2017' meaning week 21 in year 2017.");
			return;
		}
		startDate = sdfStart.getCalendar();
		try {
			dateEnd = sdfEnd.parse(endDateField.getText().trim());
		} catch (ParseException e) {
			ProjectAppNavigator.appendToOutputTextArea("Wrong end date input format. Use 'w yyyy' ex. '21 2017' meaning week 21 in year 2017.");
			return;
		}
		endDate = sdfEnd.getCalendar();
		
		int count = 0;
		for (Employee employee : ProjectAppNavigator.projectApp.listAvailableEmployees(startDate, endDate)) {
			myPersonalActivitiesTextAreaContent.append("- " + employee.getInitials() + "\n");
			count++;
		}
		myPersonalActivitiesTextArea.setText(myPersonalActivitiesTextAreaContent.toString());
		myPersonalActivitiesTextArea.setScrollTop(Double.MAX_VALUE);
		ProjectAppNavigator.appendToOutputTextArea("Found " + count + " available employees from week " + startDateField.getText().trim() + " to " + endDateField.getText().trim() + ".");
    }
	
	@FXML
    void checkEmployeeMouseClicked(MouseEvent event) {
		String initials = initialsField.getText().trim();
		Employee empl = ProjectAppNavigator.projectApp.getEmployeeByInitials(initials);
		if (!ProjectAppNavigator.projectApp.getEmployees().contains(empl)) {
			ProjectAppNavigator.appendToOutputTextArea("No employees with initials \"" + initials + "\" has been registered.");
			return;
		}
		Calendar startDate = Calendar.getInstance();
    	Calendar endDate = Calendar.getInstance();
    	SimpleDateFormat sdfStart = new SimpleDateFormat("w yyyy", Locale.ENGLISH);
    	SimpleDateFormat sdfEnd = new SimpleDateFormat("w yyyy", Locale.ENGLISH);
    	Date dateStart;
    	Date dateEnd;
		try {
			dateStart = sdfStart.parse(startDateField.getText().trim());
		} catch (ParseException e) {
			ProjectAppNavigator.appendToOutputTextArea("Wrong start date input format. Use 'w yyyy' ex. '21 2017' meaning week 21 in year 2017.");
			return;
		}
		startDate = sdfStart.getCalendar();
		try {
			dateEnd = sdfEnd.parse(endDateField.getText().trim());
		} catch (ParseException e) {
			ProjectAppNavigator.appendToOutputTextArea("Wrong end date input format. Use 'w yyyy' ex. '21 2017' meaning week 21 in year 2017.");
			return;
		}
		endDate = sdfEnd.getCalendar();
		
		if (empl.isAvailable(startDate, endDate)) {
			ProjectAppNavigator.appendToOutputTextArea("Employee '" + initials + "' is available from week " + startDateField.getText().trim() + " to " + endDateField.getText().trim() + ".");
		} else {
			ProjectAppNavigator.appendToOutputTextArea("Employee '" + initials + "' is not available from week " + startDateField.getText().trim() + " to " + endDateField.getText().trim() + ".");
		}
    }

}
