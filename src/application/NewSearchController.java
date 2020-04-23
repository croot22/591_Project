package application;

/**
 * This class is the JavaFX Controller for the New Search page of the application.
 * It controls the ListViews, Buttons, etc. and the associated methods that makes
 * ActionEvents of those objects complete the designated tasks. 
 */

import java.net.URL;
import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
import java.util.ResourceBundle;
import javax.swing.Action;
//import org.json.JSONArray;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
//import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
//import javafx.scene.control.DialogPane;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * JavaFX class required for JavaFx functionality to run.
 */
public class NewSearchController implements Initializable {
    private Location location = new Location();
    //private UserInterface UIBackEnd = new UserInterface();
    //private AlertBox alertBox = new AlertBox();
    private JSONInputOutput jIO = new JSONInputOutput();
    private MainContollerFX mainCon = new MainContollerFX();
    //private JSONArray locationResponse;


    @FXML
    private TextField searchTxtBox;
    // TextField "Location Search"
    // User input TextField for entering location search term
    
    @FXML
    private TextField newFileName;
    // TextField "Create a New File"
    // User can input a new filename to be created and then used.
    
    @FXML
    private ListView<String> searchResultsListview;
    // ListView "Location Search" results
    // ListView that displaying candidate results from the location search.
    // Allows single selection.
    
    @FXML
    private ListView<String> newSelectedLocations;
    // ListView "Selected Locations"
    // ListView of locations selected by user to be added to Location List File.
    
    @FXML
    private Button addToListBtn;
    // Button "Add to Selected"
    // Button that adds user selected location candidate to the Selected Locations
    // Disabled until a Search entry has been made
    
    @FXML
    private Button addToFile;
    // Button "Add to File"
    // Button to add all the Selected Locations to a File, the File being the one
    // selected in the ChoiceBox, drop-down menu
    // Disabled until the first use of the 'addToListBtn'
    
    
    @FXML
    private ChoiceBox<String> fileListChoiceBox;
    // ChoiceBox "Overwrite Existing"
    // Drop-down menu of all JSON files in the SavedSearches sub-folder
    // Defaults to the first file in the list of files.
    
    @FXML
    private CheckBox overWrite;
    Boolean overWriteExisting = false;
    // sets the overWriteExisting to false, this is a variable passed to the fileWriter constructor.
    
    // Instance variable to hold the user selectedFile to save location to from the ChoiceBox
    // allowing for use by other methods     
    String selectedFile = ""; 
    
    // Instance variable to build the selected locations of type Location in an array,
    // until the user as selected all locations to be written to file.    
    ArrayList<Location> selectedLocalsArray = new ArrayList<Location>();

    // Instance variable to build the list of selected locations for display purposes.
    ArrayList<String> selectedStringArray = new ArrayList<String>();
    
    /**
     * Method that initializes the New Search Stage "window"
     * Starts with 'addToListBtn' and 'addToFile' button disabled to avoid breaking program
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        addToListBtn.setDisable(true);
        addToFile.setDisable(true);
    }
    
    /**
     * Action Method - Works upon user pressing 'Enter' when Search Textbox is selected.
     * Calls the 'listPotentialSearchResult' based on the user input search term.
     * <p>Error Handling: <br>
     * Pressing enter when search box is blank, an AlertBox indicating nothing was entered.
     * @param entered - Pressing [Enter]
     */
    public void searchTextBoxAction(ActionEvent entered) {
        String userSearchEntry = searchTxtBox.getText();
        // System.out.println("User Search Entry:  " + userSearchEntry);

        if (userSearchEntry.equals("")) {
            // Generates an AlertBox if the user enters nothing; error handling purposes (and coder amusement)
            AlertBox.display("Alert", "You did not enter a search term... \n  Are you messing with me??");
        } else {
            this.listPotentialSearchResult(userSearchEntry);
        }
    }
    
    /**
     * Method to populate the Search Results ListView. Takes in the users input search term and
     * calls the Location class's 'getLocationCandidates' to populate an ArrayList of type String for 
     * display purposes.
     * <p>Error Handling: <br>
     * If 'getLocationCandidates' returns Null, an AlertBox indicates such and 
     * asks user to try something else
     * 
     * @param searchTerm (String) - User input term for location search.
     */
    public void listPotentialSearchResult (String searchTerm) {
        ArrayList<String> localCandidatesArray = new ArrayList<String>(); 
        localCandidatesArray = location.getLocationCandidates(searchTerm);
        
        // Check for a null return
        if (localCandidatesArray == null) {
            // AlertBox called when the search results did not return a result; error handling purposes.
            AlertBox.display("Alert", "No locations could be found for that entry! "
                    + "\n             Please try something else.");
        } else {
        // populating the ListView with potential location candidates
        ObservableList<String> returnedSearchResults = FXCollections.observableArrayList(localCandidatesArray);
        searchResultsListview.setItems(returnedSearchResults); // set displayed list with results.
        searchResultsListview.getSelectionModel().setSelectionMode(SelectionMode.SINGLE); // set select mode to single select.
        searchResultsListview.getSelectionModel().select(0); // default selects the first item of list; error handling purposes.
        addToListBtn.setDisable(false); // Enables the "Add to Selected" button
        }
    }
    
    
    /**
     * ActionEvent method for "Add to Selected" button.<p>
     * Calls the 'addLocationToSelectedList' method.    
     * @param event - clicking the button
     */
    public void addToListButton(ActionEvent event) {
        this.addLocationToSelectedList();

    }
    
    /**
     * Method called by action event of "Add to Selected" <p>
     * into the selected location list. <br>
     * Calls the 'fileChoiceBox' method that populates the list of json files the locations 
     * can be saved to by the user. <br>
     * Enables the 'addToFile' button (Error handling purposes)
     * Gets the user select location for the candidates of search results and adds it to
     * an ArrayList of type String for display purposes. <br>
     * Selected location is all passed to Location method 'parseAddress' to create Location instances
     * and add that Location instance to an ArrayList of Locations selected by the user.<p>
     * Displays all the user selected location is the Selected Locations ListView.     * 
     */
    public void addLocationToSelectedList() {
        String selectedLocal = searchResultsListview.getSelectionModel().getSelectedItem();
        this.fileChoiceBox(); // set the list in "Save to File" drop down.
        addToFile.setDisable(false); // enables the "Add to File" button.
        
        // Display purposes
        selectedStringArray.add(selectedLocal); // ArrayList of Type String
        
        // ArrayList of Locations to be used to write to JSON file
        location.parseAddress(selectedLocal);
        Location local = new Location();
        local.clone(location); // cloning this instance of location to be held in ArrayList
        this.addLocation(local);
        
        // Displays locations in the ListView "Selected Locations"
        ObservableList<String> addedLocations = FXCollections.observableArrayList(selectedStringArray);
        newSelectedLocations.setItems(addedLocations);
    }

    /**
     * Helper method to add the user selected location to the Location ArrayList
     * @param selectedLocation (Location) - Selected Location
     */
    public void addLocation(Location selectedLocation) {
        selectedLocalsArray.add(selectedLocation);
    }
    

    /**
     * Method to populate the "Save to File" drop-down menu,
     * for user to select the file to Save the locations. <p>
     * If there are no files, the ChoiceBox will indicate such. <br>
     * If there are files, they will be listed and the first one will be selected by
     * default for error handling purposes. 
     * 
     */
    public void fileChoiceBox() {
        ObservableList<String> jsonFilesList = FXCollections.observableArrayList(jIO.getFiles());

        // If there are existing files in the SaveSearch sub-folder, they are listed for single selection
        if (jsonFilesList.size() != 0) {
            fileListChoiceBox.setItems(jsonFilesList);
            fileListChoiceBox.setValue(jsonFilesList.get(0)); // Default selection of first item; error handling purposes
        } 
        // If no files are found in SavedSearch sub-folder, No files found is displayed and list selection is disabled
        else {
            ObservableList<String> noContent = FXCollections.observableArrayList("No files found");
            fileListChoiceBox.setItems(noContent);
        }
    }
    
    /**
     * Method to get the user selected file from "Save to File" drop-down
     * @return (String) - The name of the file selected by the user.
     */
    public String choiceBoxSelection() {
        String selectedFile = fileListChoiceBox.getValue();
        return selectedFile;
    }
    

    /**
     * ActionEvent method for "Add to File" button. <p>
     * Calls the 'addToFileBtn'.
     * @param event - Clicking the button.
     */
    public void addToFileBtn(Action event) {
        this.addToFile();
    } 
    
    
    /**
     * Method called by the "Add to File" button <p>
     * Method get the file name the user want the locations saved,
     * then uses the 'fileWriter' method and passes in the 'selectedLocalsArray', 
     * the filename, and the current setting for the overWrite checkbox (to either appends or overwrites)
     * AlertBox used to give the user confirmation of the action.
     */
    public void addToFile() {
        String fileSelected = this.choiceBoxSelection();
        jIO.fileWriter(selectedLocalsArray, fileSelected, overWriteExisting);
        AlertBox.display("ADDED! - Congrats", "You added your Selected Locations to file!!"
                + "\nYou will now be returned to the Main window.\n\n"
                + "***Refresh the 'Saved Locations List Files' if you created a new file***.");
        
        // This sets it up to automatically close the New Search Window, once the user has
        // added new locations to a file. Effectively returning them to the Main window.
        Stage tempStage = (Stage) addToFile.getScene().getWindow();
        tempStage.close();
    }
    

    /**
     * ActionEvent Method for "Create a New File" TextBox <p>
     * Handles a blank entry with an AlertBox,
     * otherwise calls the 'writeNewFile' method.
     * @param event - pressing [Enter]
     */
    public void newFile(ActionEvent event) {
        String userNewFileName = newFileName.getText();
        if (userNewFileName.equals("")) {
            AlertBox.display("Alert", "You did not enter a search term... \n  Are you messing with me??");
        } else {
            this.writeNewFile(userNewFileName);
        }
    }
    
    /**
     * Method called by the "Create a New File" Textbox <p>
     * Uses the 'fileWriter' method from the JSONInputOutput class.
     * Creates a new file with user input name, with an empty ArrayList.
     * Uses 'fileChoiceBox' to update the drop-down list.
     * @param newFileName
     */
    public void writeNewFile(String newFileName) {
        ArrayList<Location> locations = new ArrayList<Location>();
        String completeFileName = newFileName + ".json";
        jIO.fileWriter(locations, completeFileName);
        this.fileChoiceBox(); // updates ChoiceBox list with new file
    }
    
    
    /**
     * ActionEvent method for "Overwrite Existing" check box.
     * Set global variable 'overWriteExisting' to "true"
     * @param event
     */
    public void overWrite(ActionEvent event) {
        overWriteExisting = true;
    }
    
}