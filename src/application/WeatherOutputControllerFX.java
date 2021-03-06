package application;

import java.lang.reflect.InvocationTargetException;

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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
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
    
    @FXML
    private Label locationLabel;
    
    /**
     * Method that initializes the Outdoor Activity Selection "window"
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	try {
			this.rankedUWWeatherList();
	    	this.rankedNWSWeatherList();
	    	this.locationLabelOutput();
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    

    /**
     * This method adds the text for Weather Underground to the fxml text box
     * @throws SecurityException 
     * @throws NoSuchMethodException 
     * @throws InvocationTargetException 
     * @throws IllegalArgumentException 
     * @throws IllegalAccessException 
     */
    public void rankedUWWeatherList() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
    	
    	//takes the location info and calls the ranked forecast output method to give the information for the weather
    	rankedUWWeatherTextFlow.getChildren().add(MainControllerFX.rankedForecastOutput(MainControllerFX.locationCoordinates,"WU"));
    }
    

    /**
     * This method adds the text for NWS to the fxml text box
     * @throws SecurityException 
     * @throws NoSuchMethodException 
     * @throws InvocationTargetException 
     * @throws IllegalArgumentException 
     * @throws IllegalAccessException 
     */
    public void rankedNWSWeatherList() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
    	//takes the location info and calls the ranked forecast output method to give the information for the weather
    	rankedNWSWeatherTextFlow.getChildren().add(MainControllerFX.rankedForecastOutput(MainControllerFX.locationCoordinates,"NWS"));  	
    }
    
    /**
     * This method adds the selected location the the fxml label
     */
    public void locationLabelOutput() {
    	locationLabel.setText(MainControllerFX.outputLocation);
    }
}

