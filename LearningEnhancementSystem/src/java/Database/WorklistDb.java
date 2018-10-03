/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Classes.Evaluation;
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
     * This method retrieves all of the deliveries in the database, create an object of each record and is then
     * added to a list of deliveries
     * @return a list of delivery-objects.
     */
    public List<Worklist> getDelivery(){
        List<Worklist> worklist = new ArrayList<>();
        
        try (
            Connection conn = getConnection();
            Statement stmt = getStatement(conn);
            DeliverySet deliverySet = stmt.executeQuery(SLCT_ALL_DELIVERY);
          ){
            while(DeliverySet.next()) {
                Delivery delivery = new Evaluation();
                delivery.setId(deliverySet.getInt("delivery_id"));
                delivery.setStudentName(deliverySet.getString("student_id"));
                delivery.setDelivaryContent(deliverySet.getString("delivery_content"));
                delivery.setDesc(deliverySet.getString("delivery_timestamp"));
                
            }
            return Delivery;
        }
        catch (SQLException ex) {
            System.out.println("Query error:" + ex);
        }
        return null;
    }
    