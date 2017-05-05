package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;

import java.io.IOException;

// This works as a utility class for controlling navigation between windows.
// By: Michael Mortensen
public class ProjectAppNavigator {

	public static ProjectApp projectApp = new ProjectApp(); // The application layer main object used throughout the application
	public static Project activeProject;
	
	public static void appendToOutputTextArea(String string) {
		mainController.appendToOutputTextArea(string);
    }

    private static MainController mainController;

    public static void setMainController(MainController mainController) {
    	ProjectAppNavigator.mainController = mainController;
    	ProjectAppNavigator.appendToOutputTextArea("Welcome! Type your initials to login or register as a new employee.");
    }

    public static void loadWindow(String fxml) {
        try {
            mainController.setWindow(FXMLLoader.load(ProjectAppNavigator.class.getResource(fxml)));
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }

}