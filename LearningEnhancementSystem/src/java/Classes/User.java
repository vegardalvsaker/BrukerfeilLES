package Classes;

public class User {
    private String userId;
    private String userName;
    private String userEmail;
    private boolean userIsTeacher;
    
     
  public User(String userID, String userName, String userEmail, boolean userIsTeacher) {
        this.userId = userID;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userIsTeacher = userIsTeacher;
        }


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

    public String toString()    {
        return userId + " " + userName + " " + userEmail + " " + userIsTeacher + " ";
    }
  
    public void setUserEmail (String useremail) {
        this.userEmail = useremail;
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

