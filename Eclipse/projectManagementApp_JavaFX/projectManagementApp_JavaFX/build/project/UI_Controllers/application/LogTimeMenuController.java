package application;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import javafx.scene.*;


public class LogTimeMenuController implements Initializable {
	
	public StringBuilder myPersonalActivitiesTextAreaContent = new StringBuilder("");
	public double hoursArg = -1.0;
	public TextField textFieldElementX;
	public Text textElementX;
	
	@FXML
    private TextField initialsField;
	
	@FXML
    private TextField startDateField;
	
	@FXML
    private TextField endDateField;
	
	@FXML
	public TextArea myPersonalActivitiesTextArea;
	
	@FXML
	public VBox activitiesList;
	
	@FXML
	public Label labelElement;
	
	@FXML
	public TextField textFieldElement;
	
	@FXML
	public Button buttonElement;
	
	@FXML
	public Text textElement;
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
		if (ProjectAppNavigator.projectApp.getActiveUser().getActivities().size() <= 0) {
			ProjectAppNavigator.appendToOutputTextArea("You are not currently enrolled in any activities.");
		}
		int i = 0;
		for (Activity act : ProjectAppNavigator.projectApp.getActiveUser().getActivities()) {
			HBox newHBox = new HBox();
			newHBox.setId("HBox" + i);
			newHBox.setSpacing(5);
			activitiesList.getChildren().add(newHBox);
			Label newLabel = new Label("Activity '" + act.getName() + "':");
			TextField newTextField = new TextField("log time (hours)");
			newTextField.setId("loggedTime" + i);
			newTextField.setPrefWidth(100);
			Button newButton = new Button("log time");
			Activity activityArg = act;
			int k = i;
			newButton.setOnMouseClicked(e -> logTimeMouseClicked(activityArg, k));
			Text newText;
			String loggedTimeString = "";
			if (ProjectAppNavigator.projectApp.getActiveUser().unloggedActivities(ProjectAppNavigator.projectApp.getDate()).contains(act)) {
				newText = new Text("Unlogged");
			} else {
				for (Log log : ProjectAppNavigator.projectApp.getActiveUser().logs) {
					if (log.getActivity().equals(act)) {
						loggedTimeString += log.getTimeSpent();
					}
				}
				newText = new Text(loggedTimeString);
			}
			newText.setId("loggedTimeText" + i);
			newHBox.getChildren().addAll(newLabel, newTextField, newButton, newText);
			i++;
		}
    }
	
	@FXML
    void okMouseClicked(MouseEvent event) {
    	ProjectAppNavigator.loadWindow("MainMenu.fxml");
    }
	
	@FXML
    void listAllActivitiesMouseClicked(MouseEvent event) {
		if (ProjectAppNavigator.projectApp.getActiveUser().getActivities().size() <= 0) {
			ProjectAppNavigator.appendToOutputTextArea("You are not currently enrolled in any activities.");
		}
		activitiesList.getChildren().clear();
		int i = 0;
		for (Activity act : ProjectAppNavigator.projectApp.getActiveUser().getActivities()) {
			HBox newHBox = new HBox();
			newHBox.setId("HBox" + i);
			newHBox.setSpacing(5);
			activitiesList.getChildren().add(newHBox);
			Label newLabel = new Label("Activity '" + act.getName() + "':");
			TextField newTextField = new TextField("log time (hours)");
			newTextField.setId("loggedTime" + i);
			newTextField.setPrefWidth(100);
			Button newButton = new Button("log time");
			Activity activityArg = act;
			int k = i;
			newButton.setOnMouseClicked(e -> logTimeMouseClicked(activityArg, k));
			Text newText;
			String loggedTimeString = "";
			if (ProjectAppNavigator.projectApp.getActiveUser().unloggedActivities(ProjectAppNavigator.projectApp.getDate()).contains(act)) {
				newText = new Text("Unlogged");
			} else {
				for (Log log : ProjectAppNavigator.projectApp.getActiveUser().logs) {
					if (log.getActivity().equals(act)) {
						loggedTimeString += log.getTimeSpent();
					}
				}
				newText = new Text(loggedTimeString);
			}
			newText.setId("loggedTimeText" + i);
			newHBox.getChildren().addAll(newLabel, newTextField, newButton, newText);
			i++;
		}
    }
	
	@FXML
    void listAllUnloggedActivitiesMouseClicked(MouseEvent event) {
		if (ProjectAppNavigator.projectApp.getActiveUser().getActivities().size() <= 0) {
			ProjectAppNavigator.appendToOutputTextArea("You are not currently enrolled in any activities.");
		}
		activitiesList.getChildren().clear();
		int i = 0;
		for (Activity act : ProjectAppNavigator.projectApp.getActiveUser().getActivities()) {
			HBox newHBox = new HBox();
			newHBox.setId("HBox" + i);
			newHBox.setSpacing(5);
			activitiesList.getChildren().add(newHBox);
			Label newLabel = new Label("Activity '" + act.getName() + "':");
			TextField newTextField = new TextField("log time (hours)");
			newTextField.setId("loggedTime" + i);
			newTextField.setPrefWidth(100);
			Button newButton = new Button("log time");
			Activity activityArg = act;
			int k = i;
			newButton.setOnMouseClicked(e -> logTimeMouseClicked(activityArg, k));
			Text newText;
			String loggedTimeString = "";
			if (ProjectAppNavigator.projectApp.getActiveUser().unloggedActivities(ProjectAppNavigator.projectApp.getDate()).contains(act)) {
				newText = new Text("Unlogged");
				newText.setId("loggedTimeText" + i);
				newHBox.getChildren().addAll(newLabel, newTextField, newButton, newText);
				i++;
			}
		}
    }
	
    private void logTimeMouseClicked(Activity activity, int i) {
    	textFieldElementX = getChildByID(activitiesList, "loggedTime" + i);
    	textElementX = getChildByID(activitiesList, "loggedTimeText" + i);
    	try {
			hoursArg = Double.parseDouble(textFieldElementX.getText());
		} catch (NumberFormatException e) {
			ProjectAppNavigator.appendToOutputTextArea("Invalid input number for hours worked. Has to be Double, ex. '3.5'.");
			return;
		}
    	Log tempLog = new Log(ProjectAppNavigator.projectApp.getActiveUser(), activity, 0);
    	for (Log log : ProjectAppNavigator.projectApp.getActiveUser().logs) {
			if (log.getActivity().equals(activity)) {
				tempLog = log;
				ProjectAppNavigator.projectApp.getActiveUser().logActivityTime(tempLog, hoursArg);
		    	textElementX.setText("" + hoursArg);
			}
		}
    	ProjectAppNavigator.appendToOutputTextArea("Work time for activity '" + activity.getName() + "' has been logged successfully.");
    }
    
// Following algorithm by: https://gist.github.com/andytill/3024651
	static <T> T getChildByID(Parent parent, String id) {
		
		String nodeId = null;
		
		if(parent instanceof TitledPane) {
			TitledPane titledPane = (TitledPane) parent;
			Node content = titledPane.getContent();
			nodeId = content.idProperty().get();
			
			if(nodeId != null && nodeId.equals(id)) {
				return (T) content;
			}
			
			if(content instanceof Parent) {
				T child = getChildByID((Parent) content, id);
				
				if(child != null) {
					return child;
				}
			}
		}
		
		for (Node node : parent.getChildrenUnmodifiable()) {
			nodeId = node.idProperty().get();
			if(nodeId != null && nodeId.equals(id)) {
				return (T) node;
			}
			
			if(node instanceof SplitPane) {
				SplitPane splitPane = (SplitPane) node;
				for (Node itemNode : splitPane.getItems()) {
					nodeId = itemNode.idProperty().get();
					
					if(nodeId != null && nodeId.equals(id)) {
						return (T) itemNode;
					}
					
					if(itemNode instanceof Parent) {
						T child = getChildByID((Parent) itemNode, id);
						
						if(child != null) {
							return child;
						}
					}
				}
			}
			else if(node instanceof Accordion) {
				Accordion accordion = (Accordion) node;
				for (TitledPane titledPane : accordion.getPanes()) {
					nodeId = titledPane.idProperty().get();
					
					if(nodeId != null && nodeId.equals(id)) {
						return (T) titledPane;
					}
					
					T child = getChildByID(titledPane, id);
					
					if(child != null) {
						return child;
					}
				}
			}
			else   if(node instanceof Parent) {
				T child = getChildByID((Parent) node, id);
				
				if(child != null) {
					return child;
				}
			}
		}
		return null;
	}
}


