/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.*;
/**
 *
 * @author Vegard
 */
public class ScoreDb extends Database{
    private static final String INSERT_SCORE = "insert into Score values (default, ?, ?, ?)";
    private static final String DELETE_SCORE = "delete from Score where score_id = ?";
    
    public ScoreDb() {
        init();
    }
    public void giveScore(String evaluationId, String learnGoalId, String points) {
        try (
             Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SCORE);
            ){
            ps.setString(1, learnGoalId);
            ps.setString(2, points);
            ps.setString(3, evaluationId);
            ps.executeUpdate();
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public void deleteScore(String scoreid) {
        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(DELETE_SCORE)) {
            ps.setString(1, scoreid);
            ps.executeUpdate();
        } 
        catch (SQLException ex) {
            System.out.println(ex);
    }
}
}
