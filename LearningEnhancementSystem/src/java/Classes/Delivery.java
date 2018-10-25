/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.sql.Timestamp;

/**
 *
 * @author Filip
 */
public class Delivery {
    private int delivery_id;
    private int student_id;
    private int module_id;
    private String delivery_content;
    private int worklist_id;
    private Timestamp delivery_timestamp;
    
    private String deliveryid;
    private String deliverycontent;
    private Timestamp deliverytimestamp;
    private boolean isevaluated;
    
    public String getDeliveryId(){
        return deliveryid;
    }
    
    public void setDeliveryId(String deliveryid){
        this.deliveryid = deliveryid;
    }
    
    public String getDeliveryContent(){
        return deliverycontent;
    }
    
    public void setDeliveryContent(String deliverycontent){
        this.deliverycontent = deliverycontent;
    }
    
    public Timestamp getDeliveryTimestamp(){
        return deliverytimestamp;
    }
    
    public void setDeliveryTimestamp(Timestamp deliverytimestamp){
        this.deliverytimestamp = deliverytimestamp;
    }
    
    public boolean getIsEvaluated(){
        return isevaluated;
    }
    
    public void setIsEvaluated(boolean isevaluated){
        this.isevaluated = isevaluated;
    }
}


