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
public class AnnouncementC {
    
    private String annId;
    private String annSubject;
    private String annBody;
    private Timestamp annTimestamp;
    private String annUserId;
    private String annUserName;
    
    public String getAnnId() {
        return annId;
    }
    public void setAnnId(String id){
        this.annId = annId;
    }
    public String getAnnSubject() {
        return annSubject;
    }
    public void setAnnSubject(String annSubject) {
        this.annSubject = annSubject;
    }
    public String getAnnBody() {
        return annBody;
    }
    public void setAnnBody(String annBody) {
        this.annBody = annBody;
    }
    public Timestamp getAnnTime() {
        return annTimestamp;
    }
    public void setAnnTime(Timestamp annTimestamp){
       this.annTimestamp = annTimestamp;
    }
    public String getAnnUserId() {
       return annUserId;
    }
    public void setAnnUserId(String annUserId) {
       this.annUserId = annUserId;
    }
    public String getAnnUserName() {
       return annUserName;
    }
    public void setAnnUserName(String annUserName) {
       this.annUserName = annUserName;
    }  
}