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
    private int replyid;
    private Timestamp replytimestamp;
    private String replytext;

    public int getReplyid(){
        return replyid;
    }
    public void setReplyid(int replyid){
        this.replyid = replyid;
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



