/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Classes.Delivery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author Filip 
 */
public class DeliveryDb extends Database{
    private static final String ADD_DELIVERY= "insert into Announcement values (default, ?, ?, ?, ?, default)";
    public DeliveryDb() {
        init();
    }
    
    public Delivery getDelivery() {
        
        return null;
    }
    
    public void addDelivery(int delivery_id, int student_id, int module_id, String delivery_content, int worklist_id)  {
    
        try( Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(ADD_DELIVERY);
                ) {
            
            ps.setInt (1, delivery_id);
            ps.setInt (2, student_id);
            ps.setInt (3, module_id);
            ps.setString (4, delivery_content);
            ps.setInt (5, worklist_id);
            ps.executeUpdate();
            
            }
            catch(SQLException ex)   {
            System.out.println(ex);
            }
        }
    
}
