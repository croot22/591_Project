package application;

/**
 * This class is the JavaFX Controller for the Weather Output page of the application.
 * It displays the ranked weather information
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
import javafx.fxml.FXMLLoader;
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
public class WeatherOutputControllerFX implements Initializable {	

	@FXML
    private ListView<String> rankedWeatherListview;
    // ListView that displays a preset list of activities    
    
    
    /**
     * Method that initializes the Outdoor Activity Selection "window"
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	this.rankedWeatherList();
    	this.
    }
    
    /**
     * Action Method - Works upon user pressing 'Enter' when Search Textbox is selected.
     * Calls the 'listPotentialSearchResult' based on the user input search term.
     * <p>Error Handling: <br>
     * Pressing enter when search box is blank, an AlertBox indicating nothing was entered.
     * @param entered - Pressing [Enter]
     */
    public void rankedWeatherList() {
   
    	rankedWeatherListview.setItems(outdoorActivityCandidatesList);
    	rankedWeatherListview.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    	rankedWeatherListview.getSelectionModel().select(0);    	
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
        Stage stage = (Stage) chooseSelectedActivityBtn.getScene().getWindow();
        stage.close();
    }
    
    public void getPreviousStageInfo() {
    	  FXMLLoader loader = new FXMLLoader(getClass().getResource("Main_Weather.fxml"));
    	  MainContollerFX controller = loader.getController();
    	  controller.setChosenOutdoorActivity(chosenOutdoorActivity);
    }

}

