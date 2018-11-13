/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Classes.LearningGoal;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
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
        String goalQuery = "select learn_goal_text, learn_goal_points from LearningGoal where module_id = " + id;
        String moduleInfoQuery = "select module_name, module_desc from Module where module_id = " + id;
        try {
            
            ResultSet modulSet = stmt.executeQuery(moduleInfoQuery);
            while(modulSet.next()) {
                out.println("<h3>" +modulSet.getString("module_name")+ "</h3>");
                
                out.println("<p>" +modulSet.getString("module_desc") + "</p>");
                out.println("<br>");
                //out.println("<p>" + modulSet.getString("module_content"));
                
            }
            modulSet.close();
            ResultSet goalSet = stmt.executeQuery(goalQuery);
            out.println("<ul>");
            out.println("<h5>Learning goals:</h5>");
            while (goalSet.next()) {
                out.println("<li>");
                out.println(goalSet.getString("learn_goal_text"));
                out.println("Points:" + goalSet.getString("learn_goal_points"));
                out.println("</li>");
            }
            out.println("</ul>");
            conn.close();
        }
        catch (SQLException ex) {
            out.println("Couldn't retrieve from database, because" + ex);
        }
    }
    
    public boolean addLearningGoals(PrintWriter out, String learnGoalText, String learnGoalPoints, String moduleName)  {
        
        init();
        
        String getModuleID = "select module_id from Module where module_name = ?";
        String addLearnGoal = "insert into LearningGoal values (default, ?, ?, ?)";
        
        
        
        try( Connection connection = getConnection();
                
             PreparedStatement pStatement = connection.prepareStatement(getModuleID); 
             PreparedStatement prepStatement = connection.prepareStatement(addLearnGoal);
                ) {
            
                pStatement.setString(1, moduleName);
                
                
                ResultSet rset = pStatement.executeQuery();
                
                if (rset.next())    {
                String moduleID = rset.getString("module_id");
                prepStatement.setString(3, moduleID);
                }
                
                prepStatement.setString(1, learnGoalText);
                prepStatement.setString(2, learnGoalPoints);
               
                prepStatement.executeUpdate();
                
                return true;
        }
        catch(SQLException ex)  {
            out.println("Error in function addLearningGoals(): " + ex);
        }
        return false;
    }
    
    public ArrayList<LearningGoal> writeLearningGoals(PrintWriter out, String moduleID)    {
        
        String getLearngoals = "select * from LearningGoal";
        
        try( Connection connection = getConnection();
             PreparedStatement prepStatement = connection.prepareStatement(getLearngoals);
             ResultSet rset = prepStatement.executeQuery();
            )   {
            
                ArrayList<LearningGoal> lgList = new ArrayList<>();
                
                while(rset.next())  {
                    
                    LearningGoal lgoal = new LearningGoal();
                    
                    lgoal.setLearn_goal_id(rset.getString("learn_goal_id"));
                    lgoal.setText(rset.getString("learn_goal_text"));
                    lgoal.setPoints(rset.getInt("learn_goal_points"));
                    lgoal.setModuleID(rset.getString("module_id"));
                    
                    lgList.add(lgoal);
                }
                
                
            return lgList;
        }
        catch(SQLException e)   {
            out.println("Error in function writeLearningGoals(): " + e);
        }
        return null; 
            
    }
    
    public boolean editLearnGoals(PrintWriter out, HttpServletRequest request, String learnGoalText, int learnGoalPoints, String learnGoalID)    {
        
        String editLearnGoals = "update LearningGoal set learn_goal_text = ?, learn_goal_points = ? where learn_goal_id = ? ";
        
         try( Connection connection = getConnection();
             PreparedStatement prepStatement = connection.prepareStatement(editLearnGoals);
             
            )   {
             
             prepStatement.setString(1, learnGoalText);
             prepStatement.setInt(2, learnGoalPoints);
             prepStatement.setString(3, learnGoalID);
             
             prepStatement.executeQuery();
             return true;
         }
          catch(SQLException ex) {
             out.println("SQL Exception in function editLearnGoals(): " + ex);
         }
         
         return false;
    }
    
    
}
