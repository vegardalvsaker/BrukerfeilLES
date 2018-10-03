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
    
    private static final String SLCT_ALL_EVALUATIONS = "select * from Evaluation";
    private static final String SLCT_EVALUATION_WITH_DELIVERY = "select e.evaluation_id, u.teacher_id, d.delivary_id, e.evaluation_comment, e.evaluation_isPublished = ?";
    private static final String SLCT_EVALUATION = "select * from Evaluation where evalution_isPublished = false";
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
}