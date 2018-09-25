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
/**
 *
 * @author Marius
 */
public class AnnouncementDb extends Database {
    
    static final String SLCT_ANNOUNCEMENT = "select * from Announcement";
    
    public List<Announcement> getAnnouncement(){
        List<Announcement> announcements = new ArrayList<>();
        
        try (
            Connection conn = getConnection();
            Statement stmt = getStatement(conn);
            ResultSet announcementSet = stmt.executeQuery(SLCT_ANNOUNCEMENT);
          ){
            while(announcementSet.next()) {
                Announcement announcemen = new Announcement();
                announcemen.setAnNumber(announcementSet.getInt("announcement_id"));
                announcemen.setTitle(announcementSet.getString("announcement_title"));
                announcemen.setDescription(announcementSet.getString("announcement_description"));
                announcemen.setAuthor(announcementSet.getString("announcement_author"));
                announcemen.setDate(announcementSet.getString("announcement_date"));
                announcemen.add(announcements);
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

     

     System.out.println("The SQL query is: " + SLCT_ANNOUNCEMENT); // Echo For debugging

     System.out.println();

     try {
            ResultSet rset = stmt.executeQuery(SLCT_ANNOUNCEMENT);

            // Step 4: Process the ResultSet by scrolling the cursor forward via next().
            //  For each row, retrieve the contents of the cells with getXxx(columnName).
            //out.println("The records selected are:" +"<br>");
             out.println("<div class=\"jumbotron\">");
                out.println("<div class=\"container\">");
                out.println("<h1 class=\"display-4\">Announcements:</h1>");  
                out.println("<hr class=\"my-4\">");
            int rowCount = 0;
            while(rset.next()) {   // Move the cursor to the next row, return false if no more row
              //  int annoID = rset.getInt("announcement_ID");
                String annoTitle = rset.getString("announcement_title");
                String  annoDescription = rset.getString("announcement_description");
                String annoAuthor = rset.getString("announcement_author");
            //    String annoDate = rset.getString("announcement_date");

               // out.println( annoID + annoTitle+"\">" +annoDescription +": " + annoAuthor +"</a>");
              //  out.println(annoDate);
               
                out.println("<h2>" + annoTitle + "</h2>");
                out.println("<p>" + annoDescription + "</p>");
                out.println("<p>" + annoAuthor + "</p>");
                out.println("<hr class=\"my-4\">");
                //if (userIsAdmin) {
                //deleteUI(out, annoID);
            //}

                ++rowCount;
             }  
             //out.println("Total number of records = " + rowCount);

             conn.close(); 
     }     
     catch (SQLException ex) {
            out.println("Database error: " +ex);
     }
      //stmt.close(); 
    }
}
