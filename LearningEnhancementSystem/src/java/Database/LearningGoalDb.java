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
import java.util.List;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @author Vegard
 */
public class LearningGoalDb extends Database {
   
    private static final String SLCT_LEARNGOAL = "select * from LearningGoal";
    
     public List<LearningGoal> getGoals(){
        List<LearningGoal> goals = new ArrayList<>();
        
        try (
            Connection conn = getConnection();
            Statement stmt = getStatement(conn);
            ResultSet lSet = stmt.executeQuery(SLCT_LEARNGOAL);
          ){
            while(lSet.next()) {
                LearningGoal lgoals = new LearningGoal();
                lgoals.setLearnGoalId(lSet.getString("learn_goal_id"));
                lgoals.setLearnGoalText(lSet.getString("learn_goal_text"));
                lgoals.setLearnGoalPoints(lSet.getString("learn_goal_points"));
                lgoals.setModuleId(lSet.getString("module_id"));
                goals.add(lgoals);
            }
            return goals;
        }
        catch (SQLException ex) {
            System.out.println("Query error:" + ex);
        }
        return null;
    }
    
    
    
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
    
    public boolean addLearningGoals(PrintWriter out, String learnGoalText, int learnGoalPoints, String moduleID)  {
        
        String addLearnGoal = "insert into LearningGoal values (default, ?, ?, ?";
        
        try( Connection connection = getConnection();
             PreparedStatement prepStatement = connection.prepareStatement(addLearnGoal);
            
                ) {
            
                prepStatement.setString(1, learnGoalText);
                prepStatement.setInt(2, learnGoalPoints);
                prepStatement.setString(3, moduleID);
                
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
                    
                    lgoal.setLearnGoalId(rset.getString("learn_goal_id"));
                    lgoal.setLearnGoalText(rset.getString("learn_goal_text"));
                    lgoal.setLearnGoalPoints(rset.getString("learn_goal_points"));
                    lgoal.setModuleId(rset.getString("module_id"));
                    
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
