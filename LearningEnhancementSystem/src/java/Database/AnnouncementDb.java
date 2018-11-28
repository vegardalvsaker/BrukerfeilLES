/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Classes.Announcement;
import java.sql.PreparedStatement;
/**
 *
 * @author Marius
 */
public class AnnouncementDb extends Database {
    
    private static final String ORDER_ANNOUNCEMENT = "select ann_id, ann_subject, ann_body, user_name, ann_timestamp from Announcement A inner join Users U where A.teacher_id = U.user_id order by ann_timestamp desc";
    private static final String ADD_ANNOUNCEMENT = "insert into Announcement values (default, ?, default, ?, ?)";
    private static final String DEL_ANNOUNCEMENT = "delete from Announcement where ann_id = ?";
    
    public AnnouncementDb() {
        init();
    }
    
    public List<Announcement> getAnnouncement(){
        List<Announcement> announcements = new ArrayList<>();
        
        try (
            Connection conn = getConnection();
            Statement stmt = getStatement(conn);
            ResultSet announcementSet = stmt.executeQuery(ORDER_ANNOUNCEMENT);

          ){
            while(announcementSet.next()) {
                Announcement announcemen = new Announcement();
                announcemen.setAnnId(announcementSet.getString("ann_id"));
                announcemen.setAnnSubject(announcementSet.getString("ann_subject"));
                announcemen.setAnnBody(announcementSet.getString("ann_body"));
                announcemen.setAnnUserName(announcementSet.getString("user_name"));
                announcemen.setAnnTime(announcementSet.getTimestamp("ann_timestamp"));
                announcements.add(announcemen);
            }
            return announcements;
        }
        catch (SQLException ex) {
            System.out.println("Query error:" + ex);
        }
        return null;
    }
    public void addAnnouncement(String teacher_id,String ann_subject, String ann_body)  {
    
        try( Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(ADD_ANNOUNCEMENT);
                ) {
            
            ps.setString(1,teacher_id);
            ps.setString(2,ann_subject);
            ps.setString(3,ann_body);
            ps.executeUpdate();
            
        }
        catch(SQLException ex)   {
            System.out.println(ex);
        }

     }
   public void deleteAnnouncement(String Announcementid){
            try (
               Connection conn = getConnection();
               PreparedStatement ps = conn.prepareStatement(DEL_ANNOUNCEMENT)) {
               
               ps.setString(1, Announcementid);
               ps.executeUpdate();
 
       } catch (SQLException ex){
           System.out.println(ex);
       }
   }
}
