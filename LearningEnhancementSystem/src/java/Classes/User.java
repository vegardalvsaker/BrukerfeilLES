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
    private String userId;
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
  
    public String getUserId() {
        return userId;
    }
    public void setUserid (String userid) {
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
        return userIsTeacher;
    }

    public void setTeacher(boolean isTeacher) {
        this.userIsTeacher = isTeacher;
    }
    
    public boolean getUserIsTeacher(){
        return userIsTeacher;
    }

    public void setUserId(String userID) {
        this.userId = userID;
    }

    public void setUserIsTeacher(boolean userIsTeacher) {
        this.userIsTeacher = userIsTeacher;
    }
}
