/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.sql.Timestamp;

/**
 *
 * @author Marius
 */
public class CommentReply {
    private String replyid;
    private String commentid;
    private String userid;
    private String username;
    private Timestamp replytimestamp;
    private String replytext;

    public String getReplyId(){
        return replyid;
    }
    public void setReplyId(String replyid){
        this.replyid = replyid;
    }
    public String getCommentId(){
        return commentid;
    }
    public void setCommentId (String commentid){
        this.commentid = commentid;
    }
    public String getUserId(){
        return userid;
    }
    public void setUserId (String userid){
        this.userid = userid;
    }
    public String getUserName(){
        return username;
    }
    public void setUserName (String username){
        this.username = username;
    }
    public Timestamp getReplyTimestamp(){
       return replytimestamp;
    }
    public void setReplyTimestamp(Timestamp replytimestamp){
       this.replytimestamp= replytimestamp;
   }
    public String getReplyText(){
        return replytext;
    }
    public void setReplyText(String replytext){
        this.replytext = replytext;
    }
}



