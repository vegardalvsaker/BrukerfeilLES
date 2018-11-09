package Classes;

import java.sql.Timestamp;
/**
 *
 * @author Vegard
 */
public class Notification {
    
    private String notificationId;
    private String userId;
    private String notificationContent;
    private boolean isNotificationSeen;
    private Timestamp timestamp;

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

