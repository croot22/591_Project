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
import javafx.scene.layout.HBox;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/**
 * JavaFX class required for JavaFx functionality to run.
 */
public class WeatherOutputControllerFX implements Initializable {	

	@FXML
    private HBox rankedUWWeatherTextFlow;
    // TextFlow that displays ranked weather info   
	
	@FXML
    private HBox rankedNWSWeatherTextFlow;
    // TextFlow that displays ranked weather info 
    
    
    /**
     * Method that initializes the Outdoor Activity Selection "window"
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	this.rankedUWWeatherList();
    	this.rankedNWSWeatherList();
    }
    
    /**
     * Action Method - Works upon user pressing 'Enter' when Search Textbox is selected.
     * Calls the 'listPotentialSearchResult' based on the user input search term.
     * <p>Error Handling: <br>
     * Pressing enter when search box is blank, an AlertBox indicating nothing was entered.
     * @param entered - Pressing [Enter]
     */
    public void rankedUWWeatherList() {
    	
		//takes the location info and calls the ranked forecast output method to give the information for the weather
    	
    	rankedUWWeatherTextFlow.getChildren().add(MainContollerFX.rankedForecastOutput(MainContollerFX.locationCoordinates,"WU"));	
    }
    
    /**
     * Action Method - Works upon user pressing 'Enter' when Search Textbox is selected.
     * Calls the 'listPotentialSearchResult' based on the user input search term.
     * <p>Error Handling: <br>
     * Pressing enter when search box is blank, an AlertBox indicating nothing was entered.
     * @param entered - Pressing [Enter]
     */
    public void rankedNWSWeatherList() {
   
    	rankedNWSWeatherTextFlow.getChildren().add(MainContollerFX.rankedForecastOutput(MainContollerFX.locationCoordinates,"NWS"));  	
    }
}

