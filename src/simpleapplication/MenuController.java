/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleapplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Arveen Maheshwari
 */
public class MenuController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    public void redirectUserScreen(ActionEvent event) {
        try {
                Stage stage =new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("user_data.fxml"));
                
                Scene scene = new Scene(root);
                
                stage.setScene(scene);
                stage.show();
                final Node source = (Node)event.getSource();
                final Stage stage_close = (Stage) source.getScene().getWindow();
                stage_close.close();
            } catch (IOException ex) {
                Logger.getLogger(User_dataController.class.getName()).log(Level.SEVERE, null, ex);
            }

    }
     @FXML
    public void redirectGradeApplication(ActionEvent event) {
        try {
                Stage stage =new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                
                Scene scene = new Scene(root);
                
                stage.setScene(scene);
                stage.show();
                final Node source = (Node)event.getSource();
                final Stage stage_close = (Stage) source.getScene().getWindow();
                stage_close.close();
            } catch (IOException ex) {
                Logger.getLogger(User_dataController.class.getName()).log(Level.SEVERE, null, ex);
            }

    }  
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
