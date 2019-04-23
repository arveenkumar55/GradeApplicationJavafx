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
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Arveen Maheshwari
 */
public class User_dataController implements Initializable {

    @FXML
    private TextField first_name;
    
    @FXML
    private TextField last_name;
    /**
     * Initializes the controller class.
     * 
     */
    database conn = new database();

    Alert alert = new Alert(Alert.AlertType.ERROR);

    @FXML
     private void saveData(ActionEvent event) { 
        alert.setTitle("Error Dialog");
        alert.setHeaderText("Look, an Error Dialog"); 
        if(first_name.getText().toString().isEmpty()) {
        alert.setContentText("Please select firstName");

        }
        else if(last_name.getText().toString().isEmpty()) {
        alert.setContentText("Please select LastName");

        }
        else {
            try {
                String ID = String.valueOf(first_name.getText().toString().charAt(0))+String.valueOf(last_name.getText().toString().charAt(0))+""+((int)(Math.random()*9000)+1000);
                
                String sql_query_1= "INSERT INTO student(f_name,l_name,date_entered,s_id)VALUES (";
                String sql_query_2= "'"+first_name.getText().toString()+"','"+last_name.getText().toString()+"','01/03/2019','"+ID+"')";
               String final_query= sql_query_1+sql_query_2;
               System.out.println(final_query);
               int flag=conn.insertData(final_query);
                if(flag==1) {
                alert.setHeaderText("generated ID is " +ID );
                alert.setContentText("Data saved successfully");
                
                }
                Stage stage =new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
                
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
        alert.showAndWait();
     }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
