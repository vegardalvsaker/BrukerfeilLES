
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

    public int getCommentid() {
        return commentid;
    }
    public void getModuleid(){
        new Module().getModuleid();
    }
    public void getUserid(){
        new User().getUserId();
    }
    public Timestamp getTime(){
       return commenttimestamp;
    }
    
    public void setCommentid (int commentid) {
        this.commentid = commentid;
    }
    public void setModuleid (Module setModuleid, int moduleid){
        new Module().setId(moduleid);
    }
    public void setUserid (User setUserid, int userid){
       new User().setUserid(userid);
    }
   public void setTime(Timestamp commenttimestamp){
       this.commenttimestamp= commenttimestamp;
   }
}


