/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.*;
import Classes.Evaluation;
import Classes.Score;
/**
 *
 * @author Vegard
 */
public class EvaluationDb extends Database{
    private static final String ADD_EVALUATION = "insert into Evaluation values (default, ?, ?, '', false)";
    private static final String SELECT_ONE = "select evaluation_id from Evaluation where delivery_id = ?";
    private static final String SELECT_EVALUATION_WITH_SCORE = "select * from Evaluation e inner join Score s on e.evaluation_id = s.evaluation_id where e.delivery_id = ?";
    private static final String UPDATE_ISPUBLISHED = "update Evaluation set evaluation_isPublished = ? where evaluation_id = ?";
    private static final String DELETE_EVALUATION = "delete from Evaluation where evaluation_id = ?";
    private static final String UPDATE_COMMENT = "update Evaluation set evaluation_comment = ? where delivery_id = ?";
   
    public EvaluationDb(){
        init();
    }
    
    public boolean addEvaluation(String teacher_id, String delivery_id){
        if (evaluationExists(delivery_id)) {
            return false;
        } else {
            try (
                    Connection conn = getConnection();
                    PreparedStatement ps = conn.prepareStatement(ADD_EVALUATION);
                    ) {

                ps.setString(1, teacher_id);
                ps.setString(2, delivery_id);
                ps.executeUpdate();
                return true;

            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    return false;
    }
    
    public boolean evaluationExists(String delivery_id) {
        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(SELECT_ONE);
                ) {
                
            ps.setString(1, delivery_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
    } catch (SQLException ex) {
        System.out.println("Some error with the database" + ex);
    }
        return false;
}
    /**
     * Adds a comment to an evaluation
     * @param deliveryid
     * @param comment
     * @return 
     */
    public boolean addComment(String deliveryid, String comment) {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_COMMENT);) {
            ps.setString(1, comment);
            ps.setString(2, deliveryid);
            ps.executeUpdate();
            return true;
        } 
        catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }
    
    public String getEvaluationId(String deliveryId) {
         try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(SELECT_ONE);
                ) {
                
            ps.setString(1, deliveryId);
            ResultSet rs = ps.executeQuery();
            rs.first();
            return rs.getString("evaluation_id");
            
    } catch (SQLException ex) {
        System.out.println("Method: getEvaluationId(), error" + ex);
    }
        return null;
    }
    

    public Evaluation getEvaluationWithScore(String deliveryId) {
        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(SELECT_EVALUATION_WITH_SCORE);
                ) {
                
            ps.setString(1, deliveryId);
            try (ResultSet rset = ps.executeQuery()) {
                rset.next();
                
                Evaluation eval = new Evaluation();
                eval.setEvaluationid(rset.getString("evaluation_id"));
                eval.setComment(rset.getString("evaluation_comment"));
                eval.setIsPublished(rset.getBoolean("evaluation_isPublished"));
                eval.setDeliveryid(rset.getString("delivery_id"));
                
                Score s1 = new Score();
                s1.setEvaluation_id(rset.getString("evaluation_id"));
                s1.setId(rset.getString("score_id"));
                s1.setLearn_goal_id(rset.getString("learn_goal_id"));
                s1.setPoints(rset.getInt("score_points"));
                eval.addScoreToList(s1);
                while (rset.next()) {
                    Score s2 = new Score();
                    s2.setEvaluation_id("evaluation_id");
                    s2.setId(rset.getString("score_id"));
                    s2.setLearn_goal_id(rset.getString("learn_goal_id"));
                    s2.setPoints(rset.getInt("score_points"));
                    eval.addScoreToList(s2);
                }
                return eval;
            }
        }
        catch (SQLException ex) {
            System.out.println("method: getEvaluationWithScore" + ex);
        }
        
        return null;
    }
    
    public void publish(boolean publish, String evaluationid) {
        try (
               Connection conn = getConnection();
               PreparedStatement ps = conn.prepareStatement(UPDATE_ISPUBLISHED);
               ) {

           ps.setBoolean(1, publish);
           ps.setString(2, evaluationid);
           ps.executeUpdate();
            
        }catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void deleteEvaluation(String evaluationid) {
        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(DELETE_EVALUATION)) {
            
                ps.setString(1, evaluationid);
                ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}