package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class MainMenuController {

    @FXML
    void logTimePane(ActionEvent event) {
    	ProjectAppNavigator.loadWindow("LogTimeMenu.fxml");
    }
    @FXML
    void employeesPane(ActionEvent event) {
    	ProjectAppNavigator.loadWindow("EmployeesMenu.fxml");
    }
    @FXML
    void createNewProjectPane(ActionEvent event) {
    	ProjectAppNavigator.loadWindow("CreateNewProjectMenu.fxml");
    }
    @FXML
    void listProjectsPane(ActionEvent event) {
    	ProjectAppNavigator.loadWindow("ListProjectsMenu.fxml");
    }
    @FXML
    void listMyPersonalActivitiesPane(ActionEvent event) {
    	ProjectAppNavigator.loadWindow("ListPersonalActivitiesMenu.fxml");
    }
    @FXML
    void editMyActivityLimitPane(ActionEvent event) {
    	ProjectAppNavigator.loadWindow("EditActivityLimitMenu.fxml");
    }

}