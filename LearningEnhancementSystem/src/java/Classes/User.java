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
    private int userId;
    private String userName;
    private String userEmail;
    private boolean isTeacher;
    
  /* public User(String userID, String userName, String userEmail) {
        this.userId = userID;
        this.userName = userName;
        this.userEmail = userEmail;
    }
*/
    public int getUserId() {
        return userId;
    }
    public void setUserid (int userid) {
        this.userId = userid;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName (String userName) {
        this.userName = userName;
    }
    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail (String useremail) {
        this.userEmail = useremail;
    }
    public boolean isTeacher() {
        return isTeacher;
    }

    public void setTeacher(boolean isTeacher) {
        this.isTeacher = isTeacher;
    }
    
}
