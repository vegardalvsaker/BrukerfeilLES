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
 * @author Filip
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
                            out.println("<h3>Her kan du skrive inn linken til youtube-videoen<h3>");
                            out.println("<form action=\"OneModule?id="+ moduleid+"\" method=\"POST\">");
                            out.println("<input type=\"text\" name=\"link\">");
                            out.println("<input type=\"submit\" class=\"btn btn-outline-danger\" value=\"Upload\">");
                            out.println("</form>");
                        } else {
                            out.println("<h3>Modulen godkjennes av lærer eller hjelpelærer</h3>");
                            out.println("<form action=\"OneModule?id="+ moduleid+"\" method=\"POST\">");
                            out.println("<p>Trykk på knappen for å gi beskjed om at du ønsker modulgodkjenning</p><br>");
                            out.println("<input type=\"submit\" class=\"btn btn-outline-danger\" value=\"Send\">");
                            out.println("</form>");
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