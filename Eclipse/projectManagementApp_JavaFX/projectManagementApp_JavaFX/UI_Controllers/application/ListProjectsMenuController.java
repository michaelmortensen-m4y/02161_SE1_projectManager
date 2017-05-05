package application;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Calendar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class ListProjectsMenuController {
    
	public StringBuilder projectsTextAreaContent = new StringBuilder("");
	
    @FXML
    private TextField managerInitialsField;
    
    @FXML
    private TextField idField;
    
    @FXML
	public TextArea projectsTextArea;

    @FXML
    void backMouseClicked(MouseEvent event) {
    	ProjectAppNavigator.loadWindow("MainMenu.fxml");
    }
    
    @FXML
    void listAllProjectsMouseClicked(MouseEvent event) {
    	if (ProjectAppNavigator.projectApp.getProjects().isEmpty()) {
    		ProjectAppNavigator.appendToOutputTextArea("No projects has been created.");
    		return;
    	}
    	projectsTextAreaContent.setLength(0);
    	projectsTextArea.setText(projectsTextAreaContent.toString());
		projectsTextArea.setScrollTop(Double.MAX_VALUE);
		int count = 0;
    	for (Project project : ProjectAppNavigator.projectApp.getProjects()) {
    		projectsTextAreaContent.append("- " + project.getId() + " | " + project.getName() + " | '" + project.getProjectManager().getInitials() + "'\n");
    		count++;
		}
    	projectsTextArea.setText(projectsTextAreaContent.toString());
		projectsTextArea.setScrollTop(Double.MAX_VALUE);
    	ProjectAppNavigator.appendToOutputTextArea("Found " + count + " projects.");
    }
    
    @FXML
    void searchByManagerMouseClicked(MouseEvent event) {
    	if (ProjectAppNavigator.projectApp.getProjects().isEmpty()) {
    		ProjectAppNavigator.appendToOutputTextArea("No projects has been created.");
    		return;
    	}
    	if (ProjectAppNavigator.projectApp.getEmployeeByInitials(managerInitialsField.getText().trim()) == null) {
    		ProjectAppNavigator.appendToOutputTextArea("No employee with initials '"+ managerInitialsField.getText().trim() + "' has been registered.");
    		return;
    	}
    	Employee employee = ProjectAppNavigator.projectApp.getEmployeeByInitials(managerInitialsField.getText().trim());
    	projectsTextAreaContent.setLength(0);
    	projectsTextArea.setText(projectsTextAreaContent.toString());
		projectsTextArea.setScrollTop(Double.MAX_VALUE);
		if (ProjectAppNavigator.projectApp.getProjectsByManager(employee).isEmpty()) {
    		ProjectAppNavigator.appendToOutputTextArea("No results.");
    		return;
    	}
    	int count = 0;
    	for (Project project : ProjectAppNavigator.projectApp.getProjectsByManager(employee)) {
    		projectsTextAreaContent.append("- " + project.getId() + " | " + project.getName() + " | '" + project.getProjectManager().getInitials() + "'\n");
    		count++;
		}
    	projectsTextArea.setText(projectsTextAreaContent.toString());
		projectsTextArea.setScrollTop(Double.MAX_VALUE);
    	ProjectAppNavigator.appendToOutputTextArea("Found " + count + " projects.");
    }
    
    @FXML
    void editByIdMouseClicked(MouseEvent event) {
    	try {
    		ProjectAppNavigator.activeProject = ProjectAppNavigator.projectApp.getProjectById(Integer.parseInt(idField.getText().trim()));
    	} catch (NumberFormatException e) {
    		ProjectAppNavigator.appendToOutputTextArea("Invalid serialnumber. It has to be an integer.");
    		return;
    	}
    	if (ProjectAppNavigator.activeProject == null) {
    		ProjectAppNavigator.appendToOutputTextArea("No project with serialnumber '" + idField.getText().trim() + "' exist.");
    		return;
    	}
    	if (!ProjectAppNavigator.activeProject.getProjectManager().equals(ProjectAppNavigator.projectApp.activeUser)) {
    		ProjectAppNavigator.appendToOutputTextArea("You are not the project manager for the project with ID '" + idField.getText().trim() + "'.");
    		return;
    	}
    	ProjectAppNavigator.loadWindow("AddNewActivityMenu.fxml");
    }
    
}