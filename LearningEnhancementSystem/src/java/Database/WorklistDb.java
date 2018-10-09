/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Classes.Evaluation;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Classes.Worklist;
import java.sql.*;

/**
 *
 * @author Espen-PC
 */
public class WorklistDb extends Database {
    
    private static final String SLCT_ALL_DELIVERABELS = "select * from Delivery";
    private static final String SLCT_EVALUATION_WITH_DELIVERY = "select d.delivery_id, u.student_id, m.module_id, d.delivery_content, w.worklist_id, d.delivery_timestamp";
    /**
     * This method retrieves all of the deliveries in the database, create an object of each record and is then
     * added to a list of deliveries
     * @return a list of delivery-objects.
     */ 
    
    /**
     *
     * @param conn
     * @param tableDelivery
     */
    public static void SortDatabaseViaMysql(Connection conn, String tableDelivery) {

    try {
        Statement stmt = conn.createStatement();
        String cmd = "SELECT * FROM " + tableDelivery + " order by delivery_timestamp";


        ResultSet rs = stmt.executeQuery(cmd);
    } catch (Exception e) {
         System.out.println(e);
    }
    
}

    public void SortDatabaseViaMysql(PrintWriter out) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     /**
     * Redundant*
     * @param out 
     */
    public void skrivDeliveries(PrintWriter out) {
    Connection conn = getConnection();
    Statement stmt = getStatement(conn);

     

     System.out.println("The SQL query is: " + SLCT_ALL_DELIVERABELS); // Echo For debugging

     System.out.println();

     try {
            ResultSet rset = stmt.executeQuery(SLCT_ALL_DELIVERABELS);

            // Step 4: Process the ResultSet by scrolling the cursor forward via next().
            //  For each row, retrieve the contents of the cells with getXxx(columnName).
            out.println("To be evaluated:" +"<br>");
            int rowCount = 0;
            while(rset.next()) {   // Move the cursor to the next row, return false if no more row
                String delID = rset.getString("delivery_id");
                String  sID = rset.getString("student_id");
                String delContent = rset.getString("delivery_content");

      
             out.println("<br href=\"OneDel?id="+ delID+"\">" +delID +": " + sID + ", " + delContent +"</br>");
                //if (userIsAdmin) {
                
            //}

                ++rowCount;
             }  
             out.println("Total number of records = " + rowCount);

             conn.close();
     }     
     catch (SQLException ex) {
            out.println("Database error: " +ex);
     }
      //stmt.close(); 
    }

    private void deleteUI(PrintWriter out, String moduleID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

