package Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Classes.Score;
import javax.servlet.http.HttpServletRequest;

import static java.lang.System.out;
/**
 *
 * @author Gorm-Erik
 */
public class ResultsDb extends Database {
    
    
    public Score fetchResults(HttpServletRequest request)   {
        
    Score score = new Score();
    String results = "select * from Score";
    ModuleDb db = new ModuleDb();
    
    
    try (
            Connection connection = getConnection();
            PreparedStatement prepStatement = connection.prepareStatement(results);
            ResultSet rset = prepStatement.executeQuery();
            ){
             
        String module_id = request.getParameter("id");
        
        db.getModuleWithLearningGoals(module_id);
        score.setId(rset.getString("score_id"));
        score.setPoints(rset.getInt("score_points"));
        
    }
    catch(SQLException excep)   {
        out.println("SQLException in ResultsDb.printResults: " + excep);
    }
    
    return score;
    
    
    
  }
}
