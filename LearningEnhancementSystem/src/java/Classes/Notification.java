/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.sql.Timestamp;
/**
 *
 * @author Vegard
 */
public class Notification {
    
    private String user_id;
    private String notificationContent;
    private boolean isNotificationSeen;
    private Timestamp timestamp;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getNotificationContent() {
        return notificationContent;
    }

    public void setNotificationContent(String notificationContent) {
        this.notificationContent = notificationContent;
    }

    public boolean isIsNotificationSeen() {
        return isNotificationSeen;
    }

    public void setIsNotificationSeen(boolean isNotificationSeen) {
        this.isNotificationSeen = isNotificationSeen;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
    
    
}
