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
    private boolean userIsTeacher;

    
  /* public User(String userID, String userName, String userEmail) {
        this.userId = userID;
        this.userName = userName;
        this.userEmail = userEmail;
    }

*/
  public User() {
        
    }
  
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
    
    public boolean getUserIsTeacher(){
        return userIsTeacher;
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

    public void setUserIsTeacher(boolean userIsTeacher) {
        this.userIsTeacher = userIsTeacher;
    }
}
