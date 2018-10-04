
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.sql.Timestamp;
/**
 *
 * @author Frozk
 */
public class Comment {
    private int commentid;
    private Timestamp commenttimestamp;
    private String commenttext;

    public int getCommentId() {
        return commentid;
    }
    public void setCommentId (int commentid) {
        this.commentid = commentid;
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