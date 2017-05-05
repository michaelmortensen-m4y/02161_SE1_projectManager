package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class EditActivityLimitMenuController implements Initializable {

    @FXML
    private TextField limitField;
	
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	int actLim = ProjectAppNavigator.projectApp.getActiveUser().getActivityLimit();
    	ProjectAppNavigator.appendToOutputTextArea("Current activity limit is set to " + actLim + ".");
    }
    
    @FXML
    private void editActivityLimitMouseClicked(MouseEvent evt) {
        try {
        	int limit = Integer.parseInt(limitField.getText());
        	ProjectAppNavigator.projectApp.getActiveUser().setActivityLimit(limit);
        	ProjectAppNavigator.appendToOutputTextArea("Activity limit set to " + limit + ".");
        	ProjectAppNavigator.loadWindow("MainMenu.fxml");
        } catch (NumberFormatException e) {
			ProjectAppNavigator.appendToOutputTextArea("Invalid input for activity limit. Number has to be integer.");
			return;
		}
    }
    
    @FXML
    private void cancelMouseClick(MouseEvent evt) {
    	ProjectAppNavigator.loadWindow("MainMenu.fxml");
    }

}