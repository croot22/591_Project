package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

public class NewSearchController implements Initializable {
    private Location location = new Location();
    private UserInterface UIBackEnd = new UserInterface();

    @FXML
    private TextField searchTxtBox;
    
    @FXML
    private TextField searchLocationEntry;
    
    @FXML
    private ListView<String> searchResultsListview;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        
    }
    
    public void searchTextBoxAction(ActionEvent entered) {
        String userSearchEntry = searchTxtBox.getText();
        System.out.println(userSearchEntry);
        
        this.listPotentialSearchResult(userSearchEntry);
        
        // UIBackEnd.getLocation(userSearchEntry); // Runs Search to Console
        
        // ********Results go to console*********
    }
    
    public void listPotentialSearchResult (String searchTerm) {
        // Obtain HashMap of potential location candidates.        
        HashMap<Integer, String> localCandidatesHash = new HashMap<Integer, String>();
        localCandidatesHash = location.getLocationCandidates(searchTerm);
        
        // Iterate through HashMap to obtain location String and put them into an ArrayList
        ArrayList<String> localCandidatesArray = new ArrayList<String>();
        for (Integer key : localCandidatesHash.keySet()) {
            String locationOption = localCandidatesHash.get(key);
            System.out.println(locationOption);
            localCandidatesArray.add(locationOption);
        }
        
        // Display ArrayList of candidate locations to ListView
        ObservableList<String> returnedSearchResults = FXCollections.observableArrayList(localCandidatesArray);
        searchResultsListview.setItems(returnedSearchResults);
        searchResultsListview.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

}
