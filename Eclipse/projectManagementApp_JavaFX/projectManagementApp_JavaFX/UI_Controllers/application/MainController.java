package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.application.Platform;

// Main controller class for the entire application layout.
 // By: Michael Mortensen
public class MainController {
	
	public StringBuilder textAreaContent = new StringBuilder("");
	
	@FXML
	public MenuBar MainNavBar;
	@FXML
	public TextArea outputTextArea;
	
	public void appendToOutputTextArea(String string) {
    	textAreaContent.append("> " + string + "\n");
    	outputTextArea.setText(textAreaContent.toString());
    	outputTextArea.setScrollTop(Double.MAX_VALUE);
    }
	
    @FXML
    private void logoutMenuEvent(ActionEvent evt) {
    	ProjectAppNavigator.projectApp.logout();
        ProjectAppNavigator.appendToOutputTextArea("Logged out.");
        ProjectAppNavigator.loadWindow("LoginMenu.fxml");
    }
    
    @FXML
    private void notImplementedMenuEvent(ActionEvent evt) {
        ProjectAppNavigator.appendToOutputTextArea("This function has not been implemented.");
    }
    
    @FXML
    private void closeStage(ActionEvent evt) {
    	Platform.exit();
    	System.exit(0);
    }
	
    @FXML
    private StackPane windowHolder;

    public void setWindow(Node node) {
        windowHolder.getChildren().setAll(node);
    }

}