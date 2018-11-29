package Classes;

import java.sql.Timestamp;

/**
 *
 * @author Marius
 */
public class Comment {

    private String commentid;
    private String moduleid;
    private String userid;
    private String username;
    private Timestamp commenttimestamp;
    private String commenttext;

    public String getCommentId() {
        return commentid;
    }
    public void setCommentId (String commentid) {
        this.commentid = commentid;
    }
    public String getModuleId() {
        return moduleid;
    }
    public void setModuleId (String moduleid) {
        this.moduleid = moduleid;
    }
    public String getUserId() {
        return userid;
    }
    public void setUserId (String userid) {
        this.userid = userid;
    }
    public String getUserName() {
        return username;
    }
    public void setUserName (String username) {
        this.username = username;
    }
    public Timestamp getCommentTime(){
       return commenttimestamp;
    }
    public void setCommentTime(Timestamp commenttimestamp){
       this.commenttimestamp= commenttimestamp;
    }
    public String getCommentText(){
        return commenttext;
    }
    public void setCommentText(String commenttext){
        this.commenttext = commenttext;
    }
    
}
