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
    private static final String SELECT_DELIVERY_WITH_USER_NAME = "select u.user_name, d.delivery_id, d.delivery_timestamp, d.student_id, d.module_id,  d.worklist_id, d.delivery_content from Delivery d inner join Users u on d.student_id = u.user_id where d.delivery_id = ?";
    public DeliveryDb() {
        init();
    }
    
    public Delivery getDeliveryWithUser(String deliveryId) {
        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(SELECT_DELIVERY_WITH_USER_NAME);
                ) {
            ps.setString(1, deliveryId);
            try (
                    ResultSet rs = ps.executeQuery();
                    ) {
                rs.next();
         
                Delivery delivery = new Delivery();
                
                delivery.setDeliveryid(rs.getString("delivery_id"));
                delivery.setStudent_id(rs.getString("student_id"));
                delivery.setStudent_name(rs.getString("user_name"));
                delivery.setModule_id(rs.getString("module_id"));
                delivery.setDeliveryContent(rs.getString("delivery_content"));
                delivery.setWorklist_id(rs.getString("worklist_id"));
                delivery.setDelivery_timestamp(rs.getString("delivery_timestamp"));
                
                return delivery;
            }
        } catch (SQLException ex) {
            System.out.println("Method: getDeliveryWithUser(), error: " + ex);
        }
        return null;
    }
    
}
