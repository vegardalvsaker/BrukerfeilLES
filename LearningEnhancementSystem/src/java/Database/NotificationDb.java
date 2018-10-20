/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.util.ArrayList;
import Classes.Notification;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Vegard
 */
public class NotificationDb extends Database {
    private static final String SELECT_NOTIFICATION = "select * from Notification where user_id = ?";
    
    public NotificationDb () {
        init();
    }
    
    public ArrayList<Notification> getUsersNotification(String userId) {
        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(SELECT_NOTIFICATION);
                ) {
            ps.setString(1, userId);
            
            try (ResultSet rs = ps.executeQuery();){
                ArrayList<Notification> notifications = new ArrayList<>();
                while(rs.next()) {
                    Notification not = new Notification();
                    not.setUser_id(rs.getString("user_id"));
                    not.setNotificationContent(rs.getString("notification_content"));
                    not.setIsNotificationSeen(rs.getBoolean("notification_seen"));
                    not.setTimestamp(rs.getTimestamp("notification_timestamp"));
                    
                    notifications.add(not);
                }
                return notifications;
            }
            
        } catch (SQLException ex) {
            System.out.println("Method: getUsersNotification(), Error: " + ex);
        }
        return null;
    }
}
