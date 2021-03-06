package application;

/**
 * This class is the JavaFX Controller for the Main App page. 
 * It controls the ListViews, Buttons, etc. and the associated methods that makes
 * ActionEvents of those objects complete the designated tasks. 
 */

//import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
//import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
//import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.text.Text;
//import javafx.scene.control.TextField;
//import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
//import javafx.stage.FileChooser.ExtensionFilter;

/**
 * JavaFX class required for JavaFx functionality to run.
 */
public class MainControllerFX implements Initializable {

	static JSONInputOutput jIO = new JSONInputOutput();
	static WU_APIHandler callWU = new WU_APIHandler();
	static NWS_APIHandler callNWS = new NWS_APIHandler();
	static String outdoorActivity = "Sailing";
	// Creating an ArrayList of type String to maintain selected Location of type String
	static List<String> selectedLocationsString = new ArrayList<String>();
	// Creating the required string to make the weather API calls.
	static String locationCoordinates = new String();
	static String outputLocation = new String();
	public static ArrayList<String> outdoorActivityCandidateList = new ArrayList<String>();


	public static String getOutdoorActivity() {
		return outdoorActivity;
	}

	public static void setChosenOutdoorActivity(String chosenOutdoorActivity) {
		outdoorActivity = chosenOutdoorActivity;
	}

	@FXML
	private Button selectFileBtn;
	// Button "Select Locations File".

	@FXML
	private Button multiLocalSelectBtn;
	// Button "Selected Locations"

	@FXML
	private Button newLocsBtn;
	// Button "New" "Create New File with New Locations"

	@FXML 
	private Button selectAllBtn;
	// Button "Select All"

	@FXML
	private Button refreshList;
	// Button - green refresh button

	@FXML
	private ListView<String> jsonFileListview; 
	// ListView "Saved Locations List Files"
	@FXML
	private ListView<String> activitySelectListview;
	// ListView "Preset activity selection"

	@FXML
	private ListView<String> locationsListview; 
	// ListView "Locations"

	@FXML
	private Button chooseActivityBtn;
	// Button - go to activity selector

	@FXML
	ObservableList<String> jsonFilesList = FXCollections.observableArrayList(jIO.getFiles());
	// List for "Saved Locations List Files" ListView
	// Gets list of files in the user's sub-folder "SavedSearch"

	@FXML
	ObservableList<String> locationsList = FXCollections.observableArrayList();
	// List for "Locations" ListView
	// Lists the locations within the selected Locations List file.

	static String userSelectedFile = "";
	// Variable to hold the user's selected filename as a String.


	//    // Variable to hold the locations selected by the user in the 'locationsListview'
	//    ArrayList<Location> localsFromSelectedJSON = new ArrayList<Location>();           


	/**
	 * JavaFX required method to initialize the 'main' Weather-matic 3000 stage/window
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.chooseActivityBtn.setDisable(true);		
		this.setFilesList();
		addActivitiesToList();
	}

	/**
	 * Method to list the files saved it the 'SavedSearch' sub-folder.<p>
	 * Checks if an files exist, lists them if there are files. 
	 * If no files are found, will display a grayed-out message.<br>
	 * If files are found, allows for a single file selection.
	 * Automatically selects the first item in list; error-handling purposes.
	 */
	public void setFilesList() {
		jsonFilesList = FXCollections.observableArrayList(jIO.getFiles());
		if (jsonFilesList.size() != 0) {
			jsonFileListview.setItems(jsonFilesList); // sets list to Observable String contents
			jsonFileListview.getSelectionModel().setSelectionMode(SelectionMode.SINGLE); // set to single item selections
			jsonFileListview.getSelectionModel().select(0); // sets default selection to first item
		} 
		else {
			ObservableList<String> noContent = FXCollections.observableArrayList("No files found \nClick the NEW button"); 
			jsonFileListview.setItems(noContent); // deals with no existing files
			jsonFileListview.setDisable(true); // disables the ListView; error-handling purposes
			selectFileBtn.setDisable(true);     // disables selectFileBtn; error-handling purposes
		}
	}


	/**
	 * ActionEvent method for 'Refresh' button.<p>
	 * Calls the 'refreshList' method on action event.
	 * @param event - clicking on the button
	 */
	public void refreshBtn(ActionEvent event) {
		this.refreshList();
	}

	/**
	 * Method called on action event of 'Refresh' button. <p>
	 * Calls the 'setFilesList' method, to refresh the list of 
	 * files in the "Saved Locations List Files". <br>
	 * This allows for the user to go to the "New Location Setup" stage/window,
	 * create and add and new file, then return to the main screen and use their
	 * new file. 
	 */
	public void refreshList() {
		this.setFilesList();
		if (jsonFilesList.size() != 0) {
			jsonFileListview.setDisable(false); // enables the ListView; fixes issue with first list created when none existed.
			selectFileBtn.setDisable(false);     // enables selectFileBtn; fixes issue with first list created when none existed.
		}
	}


	/**
	 * ActionEvent method for "Select Locations File"
	 * Calls the 'selectionLocationsFile' method on action event.
	 * @param event - clicking on the button
	 */
	public void selectLocationsFileBtn(ActionEvent event) {
		this.selectionLocationsFile();
	}


	/**
	 * Method called on action event of "Select Locations File" button. <p>
	 * If the "Saved Locations List Files" is NOT empty, the button is getting
	 * the user selected file and saving it the String variable. 
	 * This also Enables both the "Selected Locations" and "Select All" button; error handling purposes
	 * The user's selected file is passed the 'listLocationFromFile' method to obtain
	 * the locations saved within the selected file. <p>
	 * "Select Locations File" button disabled if no files in list.
	 */
	public void selectionLocationsFile() {
		if (jsonFilesList.size() != 0) {
			String selectedFile = jsonFileListview.getSelectionModel().getSelectedItem();
			try {
				this.listLocationsFromFile(selectedFile);
			} catch (NullPointerException e) {

			}
		} else {selectFileBtn.disableProperty();}
		this.chooseActivityBtn.setDisable(false);
	}

	/**
	 * Helper method to obtain the list of location within a selected JSON file.<p>
	 * Gets the locations from the user selected JSON, parses them with the
	 * 'selectedFileLocationList' method in the UserInterface class.<br>
	 * Set the ListView items to the returned string of locations from JSON.<br>
	 * Sets the selection mode to Multiple item selection.<br>
	 * Assigns the select file name to the global variable 'userSelectedFile'
	 * @param selectJsonFile (String) Name of file selected by user.
	 */
	public void listLocationsFromFile(String selectJsonFile) {
		ObservableList<String> locationsList = FXCollections.observableArrayList(selectedFileLocationList(selectJsonFile));
		locationsListview.setItems(locationsList);
		locationsListview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); 
		locationsListview.getSelectionModel().select(0);
		userSelectedFile = selectJsonFile;
	}

	/**
	 * Helper method to take in the user selected filename and return an ArrayList of Type String,
	 * of all the "Display Names" of the Locations within that file. For Displaying in the "Locations List"
	 * @param filename (String) - The user selected filename
	 * @return ArrayList - String - Of the "Display Names" of the Locations
	 */
	public ArrayList<String> selectedFileLocationList (String filename) {
		ArrayList<String> locationList = new ArrayList<String>();
		ArrayList<Location> locsArray = jIO.fileReader(filename);
		if (locsArray == null) {
			return null;
		}
		for (Location location : locsArray) {
			locationList.add(location.getDisplayName());
		}
		return locationList;
	}

	/**
	 * ActionEvent method for "Selected Locations" button. <p>
	 * Calls the 'selectedLocations' method.
	 * @param event - clicking on the button
	 * @throws IOException 
	 */
	public void selectedLocationsBtn(ActionEvent event) throws IOException {
		selectedLocations();
	}

	/**
	 * Method called on action event of "Selected Locations" button. <p>
	 * Gets all the user selected Locations using multiple selection functionality.
	 * Passes the 'selectedLocations' to the method 'locationSelection'
	 * @throws IOException 
	 */
	public void selectedLocations() throws IOException {
		selectedLocationsString = locationsListview.getSelectionModel().getSelectedItems(); 
		locationSelection(selectedLocationsString);
		for (int i = 0; i < selectedLocationsString.size(); i++) {
			outputLocation = selectedLocationsString.get(i);
			setNewStage("/application/WeatherOutput.fxml","Weather Information Output");
		}
	}

	/**
	 * ActionEvent method for "Select All" button. <p>
	 * Calls 'selectAll' method.
	 * @param event
	 * @throws IOException 
	 */
	public void selectAllBtnAction(ActionEvent event) throws IOException {
		this.selectAll();
	}

	/**
	 * As JavaFX ObservableList would not take an ArrayList of object type Location,
	 * both an ArrayList of type String and type Location were required.<br>
	 * Type String - for display purposes.<br>
	 * Type Location - for functionality purposes.
	 * @throws IOException 
	 */
	public void selectAll() throws IOException {
		// Creating an ArrayList of type Location with All of the locations in the users selected file.
		ArrayList<Location> locationsFromFile = jIO.fileReader(userSelectedFile);

		// For-Loop to get the "Display Name" of the locations and add them
		// to the String ArrayList.
		for (Location local : locationsFromFile) {
			String localAsString = local.getDisplayName();
			selectedLocationsString = new ArrayList<String>();
			selectedLocationsString.add(localAsString);
			// Passing the List of type String to the 'locationSelection' method.
			locationSelection(selectedLocationsString);
			outputLocation = selectedLocationsString.get(0);
			setNewStage("/application/WeatherOutput.fxml","Weather Information Output");

		}
	}

	/**
	 * As the JavaFX ListView would not maintain an array of type Location, an
	 * array of type String was also maintained for display purposes. To use the returned
	 * string from the user multiple selection option, this method takes in the selected
	 * locations in the List of type String and compares the location names with an ArrayList
	 * of type Location.<p>
	 * By comparing the String name selected, with the "Display Name" maintained in the Location 
	 * objects, the proper Location objects are selected. <p>
	 * Once the correct Location objects are selected, the necessary coordinates are obtained
	 * to make the calls to the weather APIs.
	 * @param selectedLocations (List type String) - Of the user selected locations.
	 */
	public void locationSelection(List<String> selectedLocations) {
		// Obtaining all the Location objects in the selected file and storing in an ArrayList
		ArrayList<Location> locationsFromFile = jIO.fileReader(userSelectedFile);

		// Iterating through the String List to get to location "name" for comparison.
		for (int i = 0; i < selectedLocations.size(); i++) {
			String locSelected = selectedLocations.get(i);

			// Iterating through the Location ArrayList to get the "DisplayName" for comparison
			for (int j = 0; j < locationsFromFile.size(); j++) {
				String locInFile = locationsFromFile.get(j).getDisplayName();

				// If the names in both lists are the same, the code below is run.
				if (locInFile.equals(locSelected)) {

					// Getting the latitude and longitude from the Location object and storing to String variable  
					String latitude = locationsFromFile.get(j).getLatitude();
					String longitude = locationsFromFile.get(j).getLongitude();
					locationCoordinates = latitude + "," + longitude;

				}
			}
		}
	}




	/**
	 * Method that makes the API calls depending on the API type given as one of the arguments </p>
	 * and returns the output as a text variable
	 * @param coordinates
	 * @param aPIType
	 * @return Text
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public static Text rankedForecastOutput(String coordinates, String APIType) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Text weatherInfoOutput = new Text();
		if (APIType == "WU") {
			try {
				String jsonRecd = callWU.makeAPICall(coordinates); // makes API to WUnderground
				
				if (jsonRecd == null) {
				    System.out.println("There was an issue calling the WUnderground forecast for <" + coordinates + ">.");
				} else {

				ArrayList<DailyForecast> wUndergroundForecasts = callWU.parseWUndergroundJSONForecast(jsonRecd); // parse the Weather Underground JSON response string into the DailyForecast Class
				RankForecast rankedList = new RankForecast(wUndergroundForecasts, outdoorActivity);
				weatherInfoOutput = rankedList.rankListPrint();
				}
			} catch (IOException e) {
				System.out.println("There was an issue calling the WUnderground forecast for <" + coordinates + ">.");
				//e.printStackTrace();
			}
		}
		else {
			ArrayList<DailyForecast> NWSForecasts = callNWS.getNWSForecast(coordinates);

			if (NWSForecasts == null) {
				System.out.println("There was an issue calling the National Weather Service forecast for <" + coordinates + ">.");
			} else {

				// PRINTS Ranked List
				RankForecast rankedList = new RankForecast(NWSForecasts, outdoorActivity);
				weatherInfoOutput = rankedList.rankListPrint();

			}
		}

		return weatherInfoOutput;
	}

	/**
	 * "New" Button Action Method. Opens the "New Location Setup" stage/window
	 * @param event
	 * @throws Exception
	 */
	public void ButtonNewLocsAction(ActionEvent event) throws Exception {
		setNewStage("/application/NewLocation.fxml","New Location Setup");
	}


	/**
	 * "New" Button Action Method. Opens the "Outdoor Activity Selection" stage/window
	 * @param event
	 * @throws Exception
	 */
	public void ButtonSelectActivityAction(ActionEvent event) throws Exception {
		setNewStage("/application/ChooseActivity.fxml","Outdoor Activity Selection");
		multiLocalSelectBtn.setDisable(false); // enables button "Selected Locations"
		selectAllBtn.setDisable(false); // enables button "Select All"
	}

	/**
	 * Helper method that takes in the location 'resource' and title and creates a new stage/window
	 * @param resource
	 * @param title
	 * @throws IOException
	 */
	public void setNewStage(String resource, String title) throws IOException {
		// Create a new Stage object
		Stage primaryStage = new Stage();
		// Copied from the Main_Java (boilerplate) 
		Parent root = FXMLLoader.load(getClass().getResource(resource)); 
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setTitle(title); // Set the title of the stage/window.
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private static void addActivitiesToList() {
		outdoorActivityCandidateList.add("Sailing");
		outdoorActivityCandidateList.add("Hiking");
		outdoorActivityCandidateList.add("Climbing");
		outdoorActivityCandidateList.add("Skiing");
		outdoorActivityCandidateList.add("Biking");
	}
}
