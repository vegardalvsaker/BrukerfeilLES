/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Classes.Delivery;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Vegard
 */
public class DeliveryDb extends Database{
    private static final String SELECT_DELIVERY = "select * from Delivery where (student_id, module_no) = (?,?)";
    private static final String SELECT_ALL_DELIVERIES = "select * from Delivery where worklist_id = ? and delivery_isEvaluated = 0";
    public DeliveryDb() {
        init();
    }
    
    public Delivery getDelivery() {
        
        return null;
    }
    
    public ArrayList<Delivery> getUnevaluatedDeliveriesWithinWorklist(String worklistId) {
        ArrayList<Delivery> deliveries = new ArrayList<>();
        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(SELECT_ALL_DELIVERIES)) {
            ps.setString(1, worklistId);
            try (ResultSet rset = ps.executeQuery();) {
                while (rset.next()) {
                    String delId = rset.getString("delivery_id");
                    String studentId = rset.getString("student_id");
                    String moduleId = rset.getString("module_id");
                    String desc = rset.getString("delivery_content");
                    String workId = rset.getString("worklist_id");
                    String timestamp = rset.getString("delivery_timestamp");
                    boolean isEvaluated = rset.getBoolean("delivery_isEvaluated");
                                        
                    Delivery del = new Delivery(delId, studentId, moduleId, desc, workId, timestamp, isEvaluated);
                    deliveries.add(del);

                }
                return deliveries;
            }
        } catch (SQLException ex) {
            System.out.print("Method: getUnevaluatedDeliveriesWithinWorklist(), error:" + ex);
            return null;
        }
        
    }
    
}
