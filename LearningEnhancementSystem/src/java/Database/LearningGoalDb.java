/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Vegard
 */
public class LearningGoalDb extends Database {
    /**
     * Må helst refactore denne metoden. Få den heller til å returnere ei liste med learninggoals
     * for så at en annen metode printer dem.
     * @param id
     * @param out 
     */
    public void printLearningGoals(String id, PrintWriter out) {
        Connection conn = getConnection();
        Statement stmt = getStatement(conn);
        String goalQuery = "select goaltext, points from LearningGoal where module_id = " + id;
        String moduleInfoQuery = "select module_name, module_description from Module where module_id = " + id;
        try {
            
            ResultSet modulSet = stmt.executeQuery(moduleInfoQuery);
            while(modulSet.next()) {
                out.println("<h3>" +modulSet.getString("module_name")+ "</h3>");
                
                out.println("<p>" +modulSet.getString("module_description") + "</p>");
                
            }
            modulSet.close();
            ResultSet goalSet = stmt.executeQuery(goalQuery);
            out.println("<ul>");
            out.println("<h5>Learning goals:</h5>");
            while (goalSet.next()) {
                out.println("<li>");
                out.println(goalSet.getString("goaltext"));
                out.println("Points:" + goalSet.getString("points"));
                out.println("</li>");
            }
            out.println("</ul>");
            conn.close();
        }
        catch (SQLException ex) {
            out.println("Couldn't retrieve from database, because" + ex);
        }
    }
}
