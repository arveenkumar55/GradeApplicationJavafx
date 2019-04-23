/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Arveen Maheshwari
 */
public class SimpleApplication extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {        // start function
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /** 
     * @param args the command line arguments
     */
    public static void main(String[] args) {  // Main function
        launch(args);
    }
    
}
