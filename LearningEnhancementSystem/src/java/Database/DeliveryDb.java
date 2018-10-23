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
 * @author Marius
 */
public class DeliveryDb extends Database{
    
    private static final String ADD_DELIVERY = "insert into Delivery values (default, ?, ?, ?, ?, default, default";
    private static final String GET_DELIVERY_FORM ="select * from Module where module_id = ?";

    public void getDeliveryForm(String moduleid, PrintWriter out) {
            try(
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(GET_DELIVERY_FORM);
                ){  
                ps.setString(1, moduleid);
                try (ResultSet rs = ps.executeQuery();) {
                    while (rs.next()) {
                        String desc = rs.getString("module_desc");
                        String content = rs.getString("module_content");
                        Boolean inInterview = rs.getBoolean("module_inInterview");
                        out.println("<h2>"+ moduleid + "</h2>");
                        out.println("<p>" + desc + "</p>");
                        out.println("<p>" + content + "</p>");
                    
                        if (inInterview.equals(0)||(inInterview.equals(false))){
                            out.println("<h1>Du trenger bare å laste opp en link<h1>");
                        } else {
                            out.println("<h1>Modulen godkjennes av lærer eller hjelpelærer</h1>");
                    }
                }
            }
        }
        catch (SQLException ex) {
            System.out.println("Query error:" + ex);
        }
    }
    
    public void addDelivery(String studentid, String moduleid, String content, String worklistid) {
        try( Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(ADD_DELIVERY);
                ) {
            
            ps.setString(1,studentid);
            ps.setString(2,moduleid);
            ps.setString(3,content);
            ps.setString(4,worklistid);
            ps.executeUpdate();
            
        }
        catch(SQLException ex)   {
            System.out.println(ex);
        }
    }
}