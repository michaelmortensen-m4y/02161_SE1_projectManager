package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class CreateNewProjectMenuController {
	
    @FXML
    private TextField newProjectNameField;
	
    @FXML
    void createNewProjectMouseClicked(MouseEvent event) {
    	String newProjectName = newProjectNameField.getText();
    	ProjectAppNavigator.projectApp.createNewProject(newProjectName);
    	ProjectAppNavigator.activeProject = ProjectAppNavigator.projectApp.getProjectById(ProjectAppNavigator.projectApp.serialNumberCounter);
    	ProjectAppNavigator.appendToOutputTextArea("New project with name \"" + newProjectName + "\" and ID \"" + ProjectAppNavigator.projectApp.serialNumberCounter + "\" has been created.");
    	ProjectAppNavigator.loadWindow("AddNewActivityMenu.fxml");
    }

    @FXML
    void cancelMouseClicked(MouseEvent event) {
    	ProjectAppNavigator.loadWindow("MainMenu.fxml");
    }
    
}