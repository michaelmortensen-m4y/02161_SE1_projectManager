package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * Controller class for the first vista.
 */
public class LoginMenuController {

    @FXML
    private TextField loginField;
	
    @FXML
    private void registerMouseClick(MouseEvent evt) {
        String initials = loginField.getText().trim();
        try {
	        ProjectAppNavigator.projectApp.registerNewEmployee(initials);
	        ProjectAppNavigator.appendToOutputTextArea("New employee with initials \"" + initials + "\" has been registered and logged in.");
	        ProjectAppNavigator.loadWindow("MainMenu.fxml");
        } catch (InitialsInvalidException e) {
        	ProjectAppNavigator.appendToOutputTextArea(e.getMessage());
        }
    }
    
    @FXML
    private void loginMouseClick(MouseEvent evt) {
        String initials = loginField.getText().trim();
        try {
        	ProjectAppNavigator.projectApp.login(initials);
            ProjectAppNavigator.appendToOutputTextArea("Logged in as \"" + initials + "\".");
            ProjectAppNavigator.loadWindow("MainMenu.fxml");
        } catch (NoSuchUserException e) {
        	ProjectAppNavigator.appendToOutputTextArea(e.getMessage());
        }
    }

}