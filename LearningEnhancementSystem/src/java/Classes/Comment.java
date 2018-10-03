
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
    private int comment_id;
    private int module_id;
    private int user_id;
    private Timestamp timestamp;

   
    
    public String getUser_id() {
        return user_id;
    }
    
    public String getModule_id() {
        return module_id;
    }
    
    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id (String comment_id) {
        this.comment_id = comment_id;
    }
    
    public Timestamp getTime(){
       return timestamp;
   }
   public void setTime(Timestamp timestamp){
       this.timestamp= timestamp;
   }
    
}


