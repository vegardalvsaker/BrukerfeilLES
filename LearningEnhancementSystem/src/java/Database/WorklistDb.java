/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

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
     * This method retrieves all of the modules in the database, create an object of each record and is then
     * added to a list of modules
     * @return a list of module-objects.
     */
    public List<Evaluation> getEvaluation(){
        List<Evaluation> evaluation = new ArrayList<>();
        
        try (
            Connection conn = getConnection();
            Statement stmt = getStatement(conn);
            ResultSet modulSet = stmt.executeQuery(SLCT_ALL_EVALUATIONS);
          ){
            while(modulSet.next()) {
                Module modul = new Module();
                modul.setId(modulSet.getInt("module_id"));
                modul.setName(modulSet.getString("module_name"));
                modul.setContent(modulSet.getString("module_content"));
                modul.setDesc(modulSet.getString("module_desc"));
                modul.setPublished(modulSet.getBoolean("module_isPublished"));
                moduler.add(modul);
            }
            return moduler;
        }
        catch (SQLException ex) {
            System.out.println("Query error:" + ex);
        }
        return null;
    }
    