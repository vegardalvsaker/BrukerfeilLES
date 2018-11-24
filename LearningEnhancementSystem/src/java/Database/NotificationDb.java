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
    private static final String SELECT_NOTIFICATION = "select * from Notification where user_id = ? order by notification_timestamp desc";
    private static final String UPDATE_NOTIFICATION = "update Notification set notification_seen = 1 where notification_id = ?";
    private static final String INSERT_NOTIFICATION = "insert into values (default, ?, ?, default, default)";
   
    public NotificationDb () {
        init();
    }
    
    public void sendNotificationsToAll(String notification) {
        UserDb uDb = new UserDb();
        
        ArrayList<String> allIds = uDb.getAllUserIds();
        if (allIds.isEmpty()) {
            return;
        }
        
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_NOTIFICATION);) {
            for (String id : allIds) {
                ps.setString(1, id);
                ps.setString(2, notification);
                ps.executeUpdate();
            }
            
        } catch (SQLException ex) {
            System.out.println("Method: sendNotificationsToAll(), error: " + ex);
        }
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
                    not.setNotificationId(rs.getString("notification_id"));
                    not.setUserId(rs.getString("user_id"));
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
    
    public boolean setNotifcationIsSeen(String notificationId) {
        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(UPDATE_NOTIFICATION);
                ) {
            ps.setString(1, notificationId);
            if (ps.executeUpdate() == 1) {
                    return true;
                } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Method: setNotifcationIsSeen(), Error: " + ex);
            return false;
        }
    }
}
