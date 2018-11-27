package Database;

import Classes.Delivery;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Classes.Results;
/**
 *
 * @author Marius
 */
public class ResultsDb extends Database {
    private static final String RESULT_LONG = "select D.student_id, U.user_name, E.evaluation_id,\n" + 
                                              "E.evaluation_comment, D.delivery_id, M.module_name, L.learn_goal_points,\n" + 
                                              "S.score_points from Users U inner join Evaluation E on U.user_id = E.teacher_id\n" +
                                              "inner join Delivery D on D.delivery_id = E.delivery_id inner join Module M\n" + 
                                              "on M.module_id = D.module_id inner join Score S on S.evaluation_id = E.evaluation_id\n" +
                                              "inner join LearningGoal L on L.learn_goal_id = S.learn_goal_id where E.evaluation_id = ?;";
    
    private static final String RESULT_SHORT = "select D.student_id, U.user_name, E.evaluation_id, D.delivery_id, M.module_name from Evaluation E \n" +
                                                "inner join Delivery D on E.delivery_id = D.delivery_id inner join Module M on \n" +
                                                "D.module_id = M.module_id inner join Users U on E.teacher_id = U.user_id where student_id = ?";
    public ResultsDb() {
        init();
    }
    
    public List<Results> getSResults(String student_id){
        List<Results> result = new ArrayList<>();
        
        try (
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(RESULT_SHORT);){
            ps.setString(1,student_id);
            try (ResultSet rSet = ps.executeQuery();){
                while(rSet.next()){
                     Results res = new Results();
                     res.setStudentId(rSet.getString("student_id"));
                     res.setTeacherName(rSet.getString("user_name"));
                     res.setEvalId(rSet.getString("evaluation_id"));
                     res.setDelId(rSet.getString("delivery_id"));
                     res.setModuleName(rSet.getString("module_name"));
                     result.add(res);
                    }
                    return result;
                }
                }
                catch (SQLException ex) {
            System.out.println("Query error:" + ex);
        }
        return null;
    }
    
    public List<Results> getLResults(String eval_id){
        List<Results> result = new ArrayList<>();
        try (
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(RESULT_LONG);){
            ps.setString(1,eval_id);
            try (ResultSet rSet = ps.executeQuery();){
                while(rSet.next()){
                     Results res = new Results();
                     res.setStudentId(rSet.getString("student_id"));
                     res.setTeacherName(rSet.getString("user_name"));
                     res.setEvalId(rSet.getString("evaluation_id"));
                     res.setEvalComment(rSet.getString("evaluation_comment"));
                     res.setDelId(rSet.getString("delivery_id"));
                     res.setModuleName(rSet.getString("module_name"));
                     res.setLGPoints(rSet.getString("learn_goal_points"));
                     res.setScorePoints(rSet.getString("score_points"));
                     result.add(res);
                    }
                    return result;
                    }
                }
                catch (SQLException ex) {
            System.out.println("Query error:" + ex);
        }
        return null;
    }
       
}
