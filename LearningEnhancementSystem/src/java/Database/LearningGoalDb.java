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
import Classes.LearningGoal;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @author Vegard
 */
public class LearningGoalDb extends Database {

    private static final String getModuleID = "select module_id from Module where module_name = ?";
    private static final String addLearnGoal = "insert into LearningGoal values (default, ?, ?, ?)";
    private static final String getLearngoals = "select * from LearningGoal";
    private static final String editLearnGoals = "update LearningGoal set learn_goal_text = ?, learn_goal_points = ? where learn_goal_id = ? "; 


    public String getModuleID(PrintWriter out, String moduleName)  {


        try(
                Connection connection = getConnection();
                PreparedStatement prepStatement = connection.prepareStatement(getModuleID);
                ){

                prepStatement.setString(1, moduleName);

                try(ResultSet rset = prepStatement.executeQuery();) {

                while(rset.next())  {

                    String moduleID = rset.getString("module_id");
                    return moduleID;

                 }

                }
        }
        catch(SQLException exc) {
            out.println("Error in getModuleID(): " + exc);
        }

        return null;

    }

    public boolean addLearningGoals(PrintWriter out, String learnGoalText, String learnGoalPoints, String moduleName)  {
        try(
             Connection connection = getConnection();
             PreparedStatement prepStatement = connection.prepareStatement(addLearnGoal);

                ) {

                String moduleID = getModuleID(out, moduleName);

                prepStatement.setString(1, learnGoalText);
                prepStatement.setString(2, learnGoalPoints);
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

    public boolean editLearnGoals(PrintWriter out, HttpServletRequest request, String learnGoalText, String learnGoalPoints, String learnGoalID)    {


         try( Connection connection = getConnection();
             PreparedStatement prepStatement = connection.prepareStatement(editLearnGoals);

            )   {

                 prepStatement.setString(1, learnGoalText);
                 prepStatement.setString(2, learnGoalPoints);
                 prepStatement.setString(3, learnGoalID);
                 prepStatement.executeUpdate();

             return true;

         }
          catch(SQLException ex) {
             out.println("SQL Exception in function editLearnGoals(): " + ex);
         }

         return false;
    }

}
