package application;

/**
 * This class is the JavaFX Controller for the Outdoor activity Selection page of the application.
 * It controls the selection of an activity
 */

import java.net.URL;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.Action;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * JavaFX class required for JavaFx functionality to run.
 */
public class OutdoorActivityController implements Initializable {
    private String chosenActivity;
	private OutdoorActivity oA = new OutdoorActivity();
	
	
    public String getChosenActivity() {
		return chosenActivity;
	}

	@FXML
    private ListView<String> outdoorActivityListview;
    // ListView that displays a preset list of activities
    
    @FXML
    private javafx.scene.control.Button chooseSelectedActivityBtn;
    // Button "Choose Selected Activity"
    
    @FXML
    ObservableList<String> outdoorActivityCandidatesList = FXCollections.observableArrayList(oA.outdoorActivityCandidateList);
    // List for "Preset list of outdoor activities" ListView
    
    
    /**
     * Method that initializes the Outdoor Activity Selection "window"
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	this.listOutdoorActivityOptions();
    }
    
    /**
     * Action Method - Works upon user pressing 'Enter' when Search Textbox is selected.
     * Calls the 'listPotentialSearchResult' based on the user input search term.
     * <p>Error Handling: <br>
     * Pressing enter when search box is blank, an AlertBox indicating nothing was entered.
     * @param entered - Pressing [Enter]
     */
    public void listOutdoorActivityOptions() {
   
    	outdoorActivityListview.setItems(outdoorActivityCandidatesList);
    	outdoorActivityListview.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    	outdoorActivityListview.getSelectionModel().select(0);    	
    }
    
    
    /**
     * ActionEvent method for "Choose Selected Activity" button. <p>
     * Calls the 'chooseSelectedActivity' method.
     * @param event - Clicking the button.
     */
    public void chooseSelectedActivityButton(Action event) {
    	this.chooseSelectedActivity();
    } 
    
    /**
     * Method for "Choose Selected Activity" button. <p>
     * Sets the selected activity from the choices in the listview.
     * @param event - Clicking the button.
     */
    public void chooseSelectedActivity() {
    	this.chosenActivity = outdoorActivityListview.getSelectionModel().getSelectedItem();
    	System.out.println(this.chosenActivity + " is your activity of choice!");
        Stage stage = (Stage) chooseSelectedActivityBtn.getScene().getWindow();
        MainContollerFX mCFX = new MainContollerFX();
        mCFX.setChosenOutdoorActivity(chosenActivity);
        stage.close();
    }

}

