/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.sql.Timestamp;
import Classes.Comment;

/**
 *
 * @author Marius
 */
public class CommentReply {
    private int replyid;
    private Timestamp commenttimestamp;

    public int getReplyid(){
        return replyid;
    }
    public void getCommentid(){
        new Comment().getCommentid();
    }
    public void getModuleid() {
        new Module().getModuleid();
    }
    public void getUserid() {
        new User().getUserId();
    }
    public Timestamp getTime(){
       return commenttimestamp;
    }
    
    public void setCommentid (Comment setCommentid, int commentid) {
        new Comment().setCommentid(commentid);
    }
    public void setModuleid (Module setModuleid, int moduleid){
        new Module().setId(moduleid);
    }
    public void setUserid (User setUserid, int userid){
       new User().setId(userid);
    }
   public void setTime(Timestamp commenttimestamp){
       this.commenttimestamp= commenttimestamp;
   }
}



