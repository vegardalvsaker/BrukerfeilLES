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
public class Announcement {
    
    private int id;
    private String subject;
    private String body;
    private Timestamp timestamp;
    
    public int getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }
    
    public String getBody() {
        return body;
    }
    public Timestamp getTime() {
        return timestamp;
    }
    public void setId(int id){
        this.id = id;
    }
   
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public void setBody(String body) {
        this.body = body;
    }
    public void setTime(Timestamp timestamp){
       this.timestamp= timestamp;
   }
}