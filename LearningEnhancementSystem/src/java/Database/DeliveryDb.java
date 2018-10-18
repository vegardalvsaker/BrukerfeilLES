/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Classes.Delivery;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Vegard
 */
public class DeliveryDb extends Database{
    
    private static final String SELECT_DELIVERY = "select m.module_id, m.module_name, d.delivery_content, m.module_inInterview from Module m inner join Delivery d where m.module_id = d.module_id and m.module_id = ?";
    
    public List<Delivery> getDelivery(){
        List<Delivery> delivery = new ArrayList<>();
        
        try(
            Connection conn = getConnection();
            Statement stmt = getStatement(conn);
            ResultSet rset = stmt.executeQuery(SELECT_DELIVERY);
        ){
          while (rset.next()){
              Delivery deliv = new Delivery();
              deliv.setDeliveryId(rset.getString("delivery_id"));
              deliv.setDeliveryContent(rset.getString("delivery_content"));
              delivery.add(deliv);
          }
               return delivery;
               }
        catch (SQLException ex) {
            System.out.println("Query error:" + ex);
        }
        return null;
    }

    public void getDelivery(String moduleid, PrintWriter out) {
        try(
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECT_DELIVERY);
                ){  
            ps.setString(1, moduleid);
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    String id = rs.getString("delivery_id");
                    String content = rs.getString("delivery_content");
                   out.println("<h2>"+ id + "</h2>");
                out.println("<p>" + content + "</p>");
                //out.println("<small>" + annotime + "</small>");
            }
        }
    }
    catch (SQLException e) {
            
        }
   
    }
}