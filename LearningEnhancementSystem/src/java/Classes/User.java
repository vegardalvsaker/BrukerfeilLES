package Classes;

public class User {
    private String userID;
    private String userName;
    private String userEmail;
    private boolean isTeacher;
    
    public User(String userID, String userName, String userEmail, boolean isTeacher) {
        
        this.userID = userID;
        this.userName = userName;
        this.userEmail = userEmail;
        this.isTeacher = isTeacher;
    }
    public User() {
        
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setIsTeacher(boolean isTeacher) {
        this.isTeacher = isTeacher;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }
    public boolean getIsTeacher()   {
        return isTeacher;
    }
    public String toString()    {
        return userID + " " + userName + " " + userEmail + " " + isTeacher + " ";
    }
}