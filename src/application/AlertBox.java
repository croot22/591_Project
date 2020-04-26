package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Class to use the AlertBox functionality of JavaFX
 * Has a single method to allow for easy incorporation into any
 * part of the application.
 */

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.*;



public class AlertBox {
    


     /**
     * Display method allows for easy incorporation of an AlertBox throughout
     * the application. It takes in two parameters, which gives the AlertBox
     * a title and a message.<p>
     * The AlertBox is configured to require the user to deal with it so at to
     * better ensure the message is being delivered. The other application windows 
     * are effectively disabled until the user clicks the button in the AlertBox or 
     * closes its window.
     * @param title (String) - The title message of the stage/window.
     * @param message (String) - The message of the stage/window.
     */
    public static void display(String title, String message) {
        Stage window = new Stage();
        
        window.initModality(Modality.APPLICATION_MODAL); // prevents other scenes from being accessed until this one is closed
        window.setTitle(title);
        window.setMinWidth(250);
        
        Label label = new Label();
        label.setText(message);
        
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());
        
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER); 
        
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait(); // requires the user to deal with the alert box
        
    }

}
