/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleapplication;



import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
 
/**
 * This program demonstrates how to use UCanAccess JDBC driver to read/write
 * a Microsoft Access database.
 * @author www.codejava.net
 *
 */
public class database {                         // class to connect with database
    String databaseURL = "jdbc:ucanaccess://students.accdb";
    Statement statement;
    Connection connection;
    database (){
         try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            connection = DriverManager.getConnection(databaseURL);
            statement = connection.createStatement();
        } catch (Exception ex) {
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    int insertData(String sql) {                        //generic insert function
        try {
            int result = statement.executeUpdate(sql);
            System.out.println(result);
            return result;  
        } catch (SQLException ex) {
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

    }
    ResultSet getData(String sql) {         //  generic function to retrieve data from database
        System.out.println(sql);
        ResultSet result=null;
        try {
             result = statement.executeQuery(sql);
           
        } catch (SQLException ex) {
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result; 
    }
 
}
