package application;

/**
 * Main/Run method for the application. <p>
 * Running this method starts the application. <br>
 * It calls the main stage/window.
 */
	
import javafx.application.Application;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
//import javafx.geometry.Orientation;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Run_Weathermatic3000 extends Application {

	@Override
	public void start(Stage primaryStage) {
	    try {
	        Parent root = FXMLLoader.load(getClass().getResource("/application/Main_Weather.fxml")); // add this line
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setTitle("Weather-matic 3000");
            primaryStage.setScene(scene);
            primaryStage.show();
            
		} catch(Exception e) {
		    System.out.println("Something went wrong loading the main program stage.");
			//e.printStackTrace();
		    }
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
