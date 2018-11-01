package Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.http.HttpServletRequest;
import static java.lang.System.out;
import Classes.Results;
/**
 *
 * @author Gorm-Erik
 */
public class ResultsDb extends Database {
    
    
    public Results getResults(HttpServletRequest request)   {
        
    
    String SQLresults = "select m.module_id, m.module_name, l.learn_goal_id, l.learn_goal_text, s.score_id, s.score_points\n" +
                     "from Module m\n" +
                     "inner join LearningGoal l on m.module_id = l.module_id\n" +
                     "inner join Score s on l.learn_goal_id = s.learn_goal_id";
    
    
        Results results = new Results();
        ModuleDb db = new ModuleDb();
    
    try (
            Connection connection = getConnection();
            PreparedStatement prepStatement = connection.prepareStatement(SQLresults);
            ResultSet rset = prepStatement.executeQuery();
            ){
         
        
        String module_id = request.getParameter("id");
        
        
        while (rset.next()) {
        results.setModuleID(rset.getInt("module_id"));
        results.setLearngoalID(rset.getInt("learn_goal_id"));
        results.setModuleName(rset.getString("module_name"));
        results.setLearngoal(rset.getString("learn_goal_text"));
        results.setScoreID(rset.getInt("score_id"));
        results.setScorePoints(rset.getInt("score_points"));
        }
        return results; 
       
    }
    catch(SQLException excep)   {
        out.println("SQLException in ResultsDb.printResults: " + excep);
    }
    
    return results;
    
    
    
  }
}
