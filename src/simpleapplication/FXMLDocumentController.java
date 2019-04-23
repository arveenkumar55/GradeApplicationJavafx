/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleapplication;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Arveen Maheshwari
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private ComboBox select_student_id;
    @FXML
    private ComboBox courses;
    @FXML
    private ComboBox q_marks;
    @FXML
    private ComboBox p_marks;
    @FXML
    private ComboBox final_project_marks;
    @FXML
    private ComboBox mid_marks;
    @FXML
    private ComboBox final_exam_marks;
    @FXML
    private ComboBox attendence_marks;
    @FXML
    private TextField name; 
    @FXML
    private TextField term; 
    @FXML
    private TextField teacher_name; 
    @FXML
    private Label result ;     
    database conn = new database();
    grade Grade =new grade();
    Alert alert = new Alert(AlertType.ERROR);
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println(Grade.student_id);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("Look, an Error Dialog");
        alert.setContentText(null);
        if(Grade.student_id.isEmpty()) {
        alert.setContentText("Please select student ID");
        }
        else if(Grade.course.isEmpty()) {
        alert.setContentText("Please select Course");
        }
        else if(Grade.quiz_marks.isEmpty()) {
        alert.setContentText("Please select Quiz marks");
        }
        else if(Grade.program_marks.isEmpty()) {
        alert.setContentText("Please select program marks");
        }
        else if(Grade.mid_marks.isEmpty()) {
        alert.setContentText("Please select mid marks");
        }
        else if(Grade.final_exam_marks.isEmpty()) {
        alert.setContentText("Please select final exam marks");
        }
        else if(Grade.final_project_marks.isEmpty()) {
        alert.setContentText("Please select final project marks");
        }
        else if(Grade.attendence_marks.isEmpty()) {
        alert.setContentText("Please select attendence marks");
        }
        
        else {
         int data = Integer.valueOf(Grade.attendence_marks)+Integer.valueOf(Grade.final_exam_marks)+Integer.valueOf(Grade.final_project_marks)+Integer.valueOf(Grade.mid_marks)+Integer.valueOf(Grade.program_marks)+Integer.valueOf(Grade.quiz_marks);
         if(data>=90)
             Grade.Grade="A";
         if(data>=80)
             Grade.Grade="B";
         if(data>=70)
             Grade.Grade="C";
         if(data>=60)
             Grade.Grade="D";
         else
             Grade.Grade="F";
         result.setText("Final grade is "+Grade.Grade);
         result.setVisible(true);
         String query_data="'"+Grade.student_id+"','"+Grade.Name+"','"+Grade.course+"','"+Grade.term+"','"+Grade.t_name+"','"+Grade.quiz_marks+"','"+Grade.program_marks+"','"+Grade.mid_marks+"','"+Grade.final_exam_marks+"','"+Grade.final_project_marks+"','"+Grade.attendence_marks+"','"+Grade.Grade+"')";
         String query = "INSERT INTO student_grade_table(s_id, name,c_name,term,instcr_name,quiz_marks,program_marks,midterm_exam,final_exam,final_project,attendence,final_grade) VALUES (";
         String final_query = query+query_data;
         System.out.println(final_query);
         int flag=conn.insertData(final_query);
         if(flag==1) {
             alert.setTitle("Success");
             alert.setHeaderText("Final grade is "+Grade.Grade);
             alert.setContentText("Data Entered successfully in database");
         }
        }
       // label.setText("Hello World!");
       
       alert.showAndWait();

       
 }
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        ResultSet result= conn.getData("SELECT * FROM student");
        ResultSet result1= conn.getData("SELECT * FROM Course");
        try {
            while (result.next()) {
                //int id = result.getInt("Contact_ID");
                String s_id = result.getString("s_id");
                System.out.println(s_id);
               select_student_id.getItems().add(s_id);
                
            }
               while (result1.next()) {
                //int id = result.getInt("Contact_ID");
                String c_name = result1.getString("c_name");
                System.out.println(c_name);
                courses.getItems().add(c_name);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
           select_student_id.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println(newValue);
                Grade.student_id=newValue;
                ResultSet result= conn.getData("SELECT * FROM student where s_id='"+newValue+"'");
                try {
                    while (result.next()) {
                        //int id = result.getInt("Contact_ID");
                        String f_name = result.getString("f_name");
                        String l_name = result.getString("l_name");

                        name.setText(f_name+" "+l_name);
                        Grade.Name=f_name+" "+l_name;
                    }   } catch (Exception ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
           courses.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println(newValue);
                Grade.course=newValue;//To change body of generated methods, choose Tools | Templates.
                 ResultSet result= conn.getData("SELECT * FROM Course where c_name='"+newValue+"'");
                try {
                    while (result.next()) {
                        //int id = result.getInt("Contact_ID");
                        String term_data = result.getString("term");
                        term.setText(term_data);
                        Grade.term= term_data;
                        String t_id  = result.getString("t_id");
                       ResultSet result1= conn.getData("SELECT * FROM instructor where ID='"+t_id+"'");
                try {
                    while (result1.next()) {
                        //int id = result.getInt("Contact_ID");
                        String f_name = result1.getString("f_name");
                        String l_name  = result1.getString("l_name");
                        teacher_name.setText(f_name+" "+l_name);
                        Grade.t_name=f_name+" "+l_name;
                        
                    }   } catch (Exception ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
                    }   } catch (Exception ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
            attendence_marks.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println(newValue);
                Grade.attendence_marks=newValue;//To change body of generated methods, choose Tools | Templates.
            }
        });
            q_marks.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println(newValue);
                Grade.quiz_marks=newValue;//To change body of generated methods, choose Tools | Templates.
            }
        });
           p_marks.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println(newValue);
                Grade.program_marks=newValue;//To change body of generated methods, choose Tools | Templates.
            }
        });
           final_project_marks.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println(newValue);
                Grade.final_project_marks=newValue;//To change body of generated methods, choose Tools | Templates.
            }
        });
            mid_marks.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println(newValue);
                Grade.mid_marks=newValue;//To change body of generated methods, choose Tools | Templates.
            }
        });
            final_exam_marks.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println(newValue);
                Grade.final_exam_marks=newValue;//To change body of generated methods, choose Tools | Templates.
            }
        });
             
          for (int i =0; i<26;i++) {
              if(i<6) {
              attendence_marks.getItems().add(String.valueOf(i));

              }
              if(i<16) {
              q_marks.getItems().add(String.valueOf(i));
              p_marks.getItems().add(String.valueOf(i));
              final_project_marks.getItems().add(String.valueOf(i));
                  
              }
              mid_marks.getItems().add(String.valueOf(i));
              final_exam_marks.getItems().add(String.valueOf(i));
          }
    }    
    
}
