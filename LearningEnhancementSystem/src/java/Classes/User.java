/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author Vegard
 */
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
