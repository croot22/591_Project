package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.swing.Action;

import org.json.JSONArray;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

public class NewSearchController implements Initializable {
    private Location location = new Location();
    private UserInterface UIBackEnd = new UserInterface();
    private AlertBox alertBox = new AlertBox();
    private JSONInputOutput jIO = new JSONInputOutput();
    private MainContollerFX mainCon = new MainContollerFX();
    //private JSONArray locationResponse;

    @FXML
    private TextField searchTxtBox;
    // User input TextField for entering location search term
    
    @FXML
    private TextField newFileName;
    
    @FXML
    private ListView<String> searchResultsListview;
    // ListView that displaying candidate results from the location search
    // Allows single selection
    
    @FXML
    private ListView<String> newSelectedLocations;
    // ListView of locations selected by user to be added to Location List File
    
    @FXML
    private Button addToListBtn;
    // Button that adds user selected location candidate to the Selected Locations
    // Disabled until a Search entry has been made
    
    @FXML
    private Button addToFile;
    // Button to add all the Selected Locations to a File, the File being the one
    // selected in the ChoiceBox, drop-down menu
    // Disabled until the first use of the 'addToListBtn'
    
    
    @FXML
    private ChoiceBox<String> fileListChoiceBox;
    // Drop-down menu of all json files in the SavedSearches sub-folder
    // Defaults to the first file in the list of files.
    
    @FXML
    private CheckBox overWrite;
    Boolean overWriteExisting = false;
    
    
    //String selectedCandidate = ""; // to take in the users select location from candidate list

    // Instance variable to hold the user selectedFile to save location to from the ChoiceBox
    // allowing for use by other methods     
    String selectedFile = ""; 

    
    // ArrayList<String> localCandidatesArray = new ArrayList<String>();

    
    // Instance variable to build the selected locations of type Location in an array,
    // until the user as selected all locations to be written to file    
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
            // System.out.println("Nothing was entered, please enter something");
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
        // Array of Type String, just for display purposes.

        
        ArrayList<String> localCandidatesArray = new ArrayList<String>(); 
        localCandidatesArray = location.getLocationCandidates(searchTerm);
        
        // Check for a null return
        if (localCandidatesArray == null) {
            //System.out.println("Could not find good results!?!?");
            AlertBox.display("Alert", "No locations could be found for that entry! "
                    + "\n             Please try something else.");
        } else {
        // populating the ListView with potential location candidates
        ObservableList<String> returnedSearchResults = FXCollections.observableArrayList(localCandidatesArray);
        searchResultsListview.setItems(returnedSearchResults);
        // setting the selection mode to single selection
        searchResultsListview.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        searchResultsListview.getSelectionModel().select(0);
        // Enabling the 'addToListBtn' as there are now values in the ListView (Error handling purposes)
        addToListBtn.setDisable(false);
        }
    }
    
    
    /**
     * Action Method - Runs upon user clicking the 'addToListButton' <br>
     * Calls the 'addLocationToSelectedList' method that puts the user selected candidate location
     * into the selected location list. <br>
     * Calls the 'fileChoiceBox' method that populates the list of json files the locations 
     * can be saved to by the user. <br>
     * Enables the 'addToFile' button (Error handling purposes)
     * @param event - Button Action
     */
    public void addToListButton(ActionEvent event) {
        this.addLocationToSelectedList();
        this.fileChoiceBox();
        addToFile.setDisable(false);
    }
    
    
    /**
     * Method called by action of 'addToListButton' <br>
     * Gets the user select location for the candidates of search results and adds it to
     * an ArrayList of type String for display purposes. <br>
     * Selected location is all passed to Location method 'parseAddress' to create Location instances
     * and add that Location instance to an ArrayList of Locations selected by the user.<p>
     * Displays all the user selected location is the Selected Locations ListView.     * 
     */
    public void addLocationToSelectedList() {
        String selectedLocal = searchResultsListview.getSelectionModel().getSelectedItem();
        //System.out.println("You Selected: " + selectedLocal);
        //System.out.println("Click, Click!");
        
        // Display purposes
        selectedStringArray.add(selectedLocal); // ArrayList of Type String
        System.out.println("String Array");
        for (String l : selectedStringArray) {
            System.out.println(l);
        }
        
        // ArrayList of Locations to be used to write to json file
        //Location local = new Location();
        location.parseAddress(selectedLocal);
        Location local = new Location();
        local.clone(location);
        this.addLocation(local);
        
        //selectedLocalsArray.add(location); // ArrayList of Type Location
        System.out.println(location.getDisplayName());
        
        // TESTING PRINT
        System.out.println("\n\nselectedLocalsArray\n");
        for (Location loc : selectedLocalsArray) {
            System.out.println(loc.getDisplayName());
            System.out.println("Overwrite status: " + overWriteExisting);
        }
        
        // Displays locations in the ListView
        ObservableList<String> addedLocations = FXCollections.observableArrayList(selectedStringArray);
        newSelectedLocations.setItems(addedLocations);
    }
    
    public void addLocation(Location selectedLocation) {
        selectedLocalsArray.add(selectedLocation);
    }
    

    
    public void fileChoiceBox() {
        ObservableList<String> jsonFilesList = FXCollections.observableArrayList(jIO.getFiles());
        
        // If there are existing files in the SaveSearch sub-folder, they are listed for single selection
        if (jsonFilesList.size() != 0) {
            fileListChoiceBox.setItems(jsonFilesList);
            
            fileListChoiceBox.setValue(jsonFilesList.get(0)); // sets default to first in the list
            
            //String selectedFileToAddTo = fileListChoiceBox.getSelectionModel().getSelectedItem();
            //System.out.println(selectedFileToAddTo);
            
        } 
        // If no files are found in SavedSearch sub-folder, No files found is displayed and list selection is disabled
        else {
            ObservableList<String> noContent = FXCollections.observableArrayList("No files found");
            fileListChoiceBox.setItems(noContent);
            //fileListChoiceBox.setDisable(true); // disables the ListView so no selection can be made
            //selectFileBtn.setDisable(true);     // disables selectFileBtn so that is cannot be 'clicked'
        }
        
        
    }
    
    public String choiceBoxSelection() {
        String selectedFile = fileListChoiceBox.getValue();
        return selectedFile;
    }
    
    public void addToFile() {
        String fileSelected = this.choiceBoxSelection();
        jIO.fileWriter(selectedLocalsArray, fileSelected, overWriteExisting);
        AlertBox.display("ADDED! - Congrats", "You added your Selected Locations to file!!"
                + "\nYou can now close this window and Refresh the Saved Locations List Files.");
        
        
        for (Location local : selectedLocalsArray) {
            System.out.println(local.getDisplayName());
            
        }
    }
    
    public void addToFileBtn(Action event) {
        this.addToFile();
        System.out.println("Click! Adding to File :)");


    }
    
    public void newFile(ActionEvent event) {
        String userNewFileName = newFileName.getText();
        // System.out.println("User Search Entry:  " + userSearchEntry);

        if (userNewFileName.equals("")) {
            // System.out.println("Nothing was entered, please enter something");
            AlertBox.display("Alert", "You did not enter a search term... \n  Are you messing with me??");
        } else {
            this.writeNewFile(userNewFileName);
        }
    }
    
    public void writeNewFile(String newFileName) {
        ArrayList<Location> locations = new ArrayList<Location>();
        String completeFileName = newFileName + ".json";
        jIO.fileWriter(locations, completeFileName);
        System.out.println("New File [Enter]");
        this.fileChoiceBox(); // updates choicebox list with new file
        //mainCon.setFilesList(); // updates main window files list
        
        
    }
    
    public void overWrite(ActionEvent event) {
        overWriteExisting = true;
    }
    
}
