/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Classes.Delivery;
import java.sql.*;
/**
 *
 * @author Vegard
 */
public class DeliveryDb extends Database{
    private static final String SELECT_DELIVERY = "select * from Delivery where (student_id, module_id) = (?,?)";
    public DeliveryDb() {
        init();
    }
    
    public Delivery getDelivery(String studentId, int moduleId) {
        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(SELECT_DELIVERY);
                ) {
            ps.setString(1, studentId);
            ps.setInt(2, moduleId);
            try (
                    ResultSet rs = ps.executeQuery();
                    ) {
                rs.next();
         
                Delivery delivery = new Delivery();
                
                delivery.setDeliveryid(rs.getString("delivery_id"));
                delivery.setStudent_id(rs.getString("student_id"));
                delivery.setModule_id(rs.getString("module_id"));
                delivery.setDeliveryContent(rs.getString("delivery_content"));
                delivery.setWorklist_id(rs.getString("worklist_id"));
                delivery.setDelivery_timestamp(rs.getString("delivery_timestamp"));
                
                return delivery;
            }
        } catch (SQLException ex) {
            System.out.println("Method: getDelivery(), error: " + ex);
        }
        return null;
    }
    
}
