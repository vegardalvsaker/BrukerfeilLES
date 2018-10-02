/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import Classes.Announcement;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
/**
 *
 * @author Marius
 */
public class AnnouncementDb extends Database {
    
    private static final String ORDER_ANNOUNCEMENT = "select * from Announcement order by ann_timestamp desc";
    private static final String ADD_ANNOUNCEMENT = "insert into Announcement values (default, ?, default, ?, ?)";
    
    public List<Announcement> getAnnouncement(){
        List<Announcement> announcements = new ArrayList<>();
        
        try (
            Connection conn = getConnection();
            Statement stmt = getStatement(conn);
            ResultSet announcementSet = stmt.executeQuery(ORDER_ANNOUNCEMENT);

          ){
            while(announcementSet.next()) {
                Announcement announcemen = new Announcement();
                announcemen.setId(announcementSet.getInt("ann_id"));
                announcemen.setBody(announcementSet.getString("ann_subject"));
                announcemen.setSubject(announcementSet.getString("ann_body"));
                announcemen.setTime(announcementSet.getTimestamp("ann_timestamp"));
                announcements.add(announcemen);
            }
            return announcements;
        }
        catch (SQLException ex) {
            System.out.println("Query error:" + ex);
        }
        return null;
    }
    
    public void skrivAnnouncement(PrintWriter out) {
       
                Connection conn = getConnection();
                Statement stmt = getStatement(conn);
                try {
                ResultSet rset = stmt.executeQuery(ORDER_ANNOUNCEMENT);
                
                out.println("<div class=\"jumbotron\">");
                out.println("<div class=\"container\">");
                out.println("<h1 class=\"display-4\">Announcements:</h1>");  
                out.println("<hr class=\"my-4\">");
                int rowCount = 0;
                while(rset.next()) {  
                
                int annoID = rset.getInt("ann_ID");
                String annoSubject = rset.getString("ann_subject");
                String  annoBody = rset.getString("ann_body");
                Timestamp annotime = rset.getTimestamp("ann_timestamp");
                     
                out.println("<h2>"+ annoID + ". " + annoSubject + "</h2>");
                out.println("<p>" + annoBody + "</p>");
                out.println("<small>" + annotime + "</small>");
                out.println("<hr class=\"my-4\">");    
                ++rowCount;
                
            } conn.close();
        } catch (SQLException ex){
                System.out.println("Some error with the database" + ex);
        }
        
    }
    
    
    public void addAnnouncement(int teacher_id,String ann_subject, String ann_body)  {
    
        try( Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(ADD_ANNOUNCEMENT);
                ) {
            
            ps.setInt(1,teacher_id);
            ps.setString(2,ann_subject);
            ps.setString(3,ann_body);
            ps.executeUpdate();
            
        }
        catch(SQLException ex)   {
            System.out.println(ex);
        }

     }

    
}
